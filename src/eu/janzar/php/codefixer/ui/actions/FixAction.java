/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/NetBeansModuleDevelopment-files/actionListener.java to edit this template
 */
package eu.janzar.php.codefixer.ui.actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JOptionPane;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.loaders.DataObject;
import org.openide.loaders.DataObjectNotFoundException;
import org.openide.util.Exceptions;
import org.openide.util.Lookup;
import org.openide.util.NbBundle;
import org.openide.util.NbBundle.Messages;
import org.openide.util.Utilities;
import eu.janzar.php.codefixer.commands.PhpCodefixer;
import eu.janzar.php.codefixer.options.PhpCodefixerOptions;
import eu.janzar.php.codefixer.preferences.PhpCodefixerPreferences;

@ActionID(
        category = "PHP",
        id = "eu.janzar.php.codefixer.ui.actions.FixAction"
)
@ActionRegistration(
        displayName = "#CTL_FixAction"
)
@Messages("CTL_FixAction=PHP Codefixer - fix")
@ActionReferences({
    @ActionReference(path = "Loaders/folder/any/Actions", position = 1690),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 1690),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 590),
    @ActionReference(path = "Projects/org-netbeans-modules-php-project/Actions", position = 1090)
})
public class FixAction extends AbstractAction implements ActionListener {
    protected static final Logger LOGGER = Logger.getLogger(FixAction.class.getName());
    @Override
    public void actionPerformed(ActionEvent e) {
        PhpModule phpModule = PhpModule.Factory.inferPhpModule();
        if (phpModule == null) {
            return;
        }
        try {
            //JOptionPane.showMessageDialog(null, "Test");
            runCommand(phpModule, getOptions());
            
        } catch (InvalidPhpExecutableException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
    }
    
    @NbBundle.Messages("FixAction.name=Fix")
    protected String getName() {
        return Bundle.FixAction_name();
    }
    
    protected void runCommand(PhpModule phpModule, List<String> options) throws InvalidPhpExecutableException {
        Collection<? extends FileObject> targetFiles = getTargetFiles();
        for (FileObject target : targetFiles) {
            if (target.isFolder()) {
                Enumeration<? extends FileObject> children = target.getChildren(true);
                while (children.hasMoreElements()) {
                    if (isModifiedFile(children.nextElement())) {
                        return;
                    }
                }
            } else {
                if (isModifiedFile(target)) {
                    return;
                }
            }
            runCommand(phpModule, options, target);
        }
    }
    
    
    public void runCommand(PhpModule phpModule, List<String> options, FileObject targetFile) throws InvalidPhpExecutableException {
        List<String> params = getAllParams(targetFile, options);
        if (!params.isEmpty()) {
            Future<Integer> result = PhpCodefixer.getDefault().fix(phpModule, params.toArray(new String[0]));
            if (result != null) {
                try {
                    result.get();
                } catch (InterruptedException | ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }
    
    protected Collection<? extends FileObject> getTargetFiles() {
        Lookup context = Utilities.actionsGlobalContext();
        return context.lookupAll(FileObject.class);
    }

    protected List<String> getAllParams(FileObject target, List<String> options) {
        if (target == null) {
            return Collections.emptyList();
        }
        String targetPath = null;
        try {
            targetPath = FileUtil.toFile(target).getCanonicalPath();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        if (StringUtils.isEmpty(targetPath)) {
            return Collections.emptyList();
        }

        List<String> params = new ArrayList<>(Collections.singletonList(targetPath));
        params.addAll(options);
        return params;
    }
    
    
    @NbBundle.Messages({
        "# {0} - file name",
        "FixAction.message.modified.file=There is a modified file({0})."
    })
    protected boolean isModifiedFile(FileObject target) {
        try {
            DataObject dataObject = DataObject.find(target);
            if (!target.isFolder() && dataObject.isModified()) {
                // show message
                NotifyDescriptor descriptor = new NotifyDescriptor.Message(
                        Bundle.FixAction_message_modified_file(target.getNameExt()),
                        NotifyDescriptor.ERROR_MESSAGE
                );
                DialogDisplayer.getDefault().notifyLater(descriptor);
                return true;
            }
        }catch (DataObjectNotFoundException ex) {
            LOGGER.log(Level.WARNING, null, ex);
        }
        return false;
    }
    
     protected List<String> getOptions() {
        PhpModule phpModule = PhpModule.Factory.inferPhpModule();
        List<String> options = new ArrayList<>();
        boolean isDryRun = isDryRun();
        boolean isVerbose;
        boolean isDiff;
        boolean isDiffFormatUdiff;

        /*
        if (phpModule == null || PhpCodefixerPreferences.useGlobal(phpModule)) {
            // use global
            PhpCodefixerOptions instance = PhpCodefixerOptions.getInstance();
            options.addAll(instance.getAllOptions());
            isVerbose = instance.isVerbose();
            isDiff = instance.isDiff();
            isDiffFormatUdiff = instance.isDiffFormatUdiff();
        } else {
            // use project
            options.addAll(PhpCodefixerPreferences.getAllOptions(phpModule));
            isVerbose = PhpCodefixerPreferences.isVerbose(phpModule);
            isDiff = PhpCodefixerPreferences.isDiff(phpModule);
            isDiffFormatUdiff = PhpCodefixerPreferences.isDiffFormatUdiff(phpModule);
        }

        if (isDryRun) {
            if (isVerbose) {
                options.add(VERBOSE_PARAM);
                if (isDiff) {
                    options.add(DIFF_PARAM);
                    if (isDiffFormatUdiff) {
                        options.add(DIFF_FORMAT_UDIFF_PARAM);
                    }
                }
            }
        }
        */

        return options;
    }
     
    protected boolean isDryRun() {
        String name = getName();
        if (StringUtils.isEmpty(name)) {
            return false;
        }
        return name.contains("--dry-run"); // NOI18N
    }
    
}

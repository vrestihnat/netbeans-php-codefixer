/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eu.janzar.php.codefixer.ui.actions;

import eu.janzar.php.codefixer.commands.PhpCodefixer;
import static eu.janzar.php.codefixer.ui.actions.FixAction.LOGGER;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

@ActionID(
        category = "PHP",
        id = "eu.janzar.php.codefixer.ui.actions.TestAction"
)
@ActionRegistration(
        displayName = "#CTL_TestAction"
)
@NbBundle.Messages("CTL_TestAction=Codesniffer")
@ActionReferences({
    @ActionReference(path = "Loaders/folder/any/Actions", position = 1692),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 1692),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 592),
    @ActionReference(path = "Projects/org-netbeans-modules-php-project/Actions", position = 1092)
})
public class TestAction extends FixAction {
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
    @NbBundle.Messages("TestAction.name=Test")
    @Override
    protected String getName() {
        return Bundle.TestAction_name();
    }
    
    @Override
    public void runCommand(PhpModule phpModule, List<String> options, FileObject targetFile) throws InvalidPhpExecutableException {
        List<String> params = getAllParams(targetFile, options);
        if (!params.isEmpty()) {
            Future<Integer> result = PhpCodefixer.getDefault().testRun(phpModule, params.toArray(new String[0]));
            if (result != null) {
                try {
                    result.get();
                } catch (InterruptedException | ExecutionException ex) {
                    Exceptions.printStackTrace(ex);
                }
            }
        }
    }
    
}

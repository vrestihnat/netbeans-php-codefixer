/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eu.janzar.php.codefixer.ui.actions;

import eu.janzar.php.codefixer.commands.PhpCodefixer;
import eu.janzar.php.codefixer.options.PhpCodefixerOptions;
import static eu.janzar.php.codefixer.ui.actions.FixAction.LOGGER;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionReferences;
import org.openide.awt.ActionRegistration;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;

@ActionID(
        category = "PHP",
        id = "eu.janzar.php.codefixer.ui.actions.FixDryAction"
)
@ActionRegistration(
        displayName = "#CTL_FixActionDryRun"
)
@NbBundle.Messages("CTL_FixActionDryRun=Codefixer - DryRun")
@ActionReferences({
    @ActionReference(path = "Loaders/folder/any/Actions", position = 1691),
    @ActionReference(path = "Loaders/text/x-php5/Actions", position = 1691),
    @ActionReference(path = "Editors/text/x-php5/Popup", position = 591),
    @ActionReference(path = "Projects/org-netbeans-modules-php-project/Actions", position = 1091)
})
public class FixDryAction extends FixAction {

    @NbBundle.Messages("FixActionDryRun.name=DryRun")
    @Override
    protected String getName() {
        return Bundle.TestAction_name();
    }

    @Override
    protected List<String> getAllParams(FileObject target, List<String> options) {
        if (target == null) {
            return Collections.emptyList();
        }
        String targetPath = null;
        String targetDryRunPath = null;
        try {
            targetPath = FileUtil.toFile(target).getCanonicalPath();
            Path copied = Paths.get(target.getPath().replaceAll(target.getName()+"."+target.getExt(), "") + target.getName() + ".fixed." + target.getExt());
            Path originalPath = FileUtil.toPath(target);
            Files.copy(originalPath, copied, StandardCopyOption.REPLACE_EXISTING);
            targetDryRunPath = copied.toAbsolutePath().toString();
        } catch (IOException ex) {
            Exceptions.printStackTrace(ex);
        }
        if (StringUtils.isEmpty(targetPath)) {
            return Collections.emptyList();
        }

        List<String> params = new ArrayList<>(Collections.singletonList(targetDryRunPath));
        params.addAll(options);
        return params;
    }

}

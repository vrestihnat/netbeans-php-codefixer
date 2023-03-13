package eu.janzar.php.codefixer;

import static java.util.logging.Level.INFO;
import static java.util.logging.Level.WARNING;
import java.util.logging.Logger;
import org.openide.modules.ModuleInstall;
import eu.janzar.php.codefixer.options.PhpCodefixerOptions;

public class Installer extends ModuleInstall {

    private static final Logger LOGGER = Logger.getLogger(Installer.class.getName());

    @Override
    public void restored() {
        PhpCodefixerOptions options = PhpCodefixerOptions.getInstance();
        
        String phpCodeFixerPath = options.getPhpCodeFixerPath();
    }

}

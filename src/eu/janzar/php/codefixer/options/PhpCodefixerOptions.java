/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eu.janzar.php.codefixer.options;



import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.StringUtils;
import org.openide.util.NbPreferences;


public class PhpCodefixerOptions {

    private static final PhpCodefixerOptions INSTANCE = new PhpCodefixerOptions();
    private static final String PREFERENCES_PATH = "phpcbf"; // NOI18N
    private static final String PHP_CS_FIXER_PATH = "php-code-fixer.path"; // NOI18N
    private static final String PHP_CS_FIXER_VERSION = "php-code-fixer.version"; // NOI18N
    private static final String RUN_ON_SAVE = "run.on.save"; // NOI18N

    private static final String USE_RULES = "use.rules"; // NOI18N
    private static final String RULES = "rules"; // NOI18N

    // common
    private static final String USE_CUSTOM = "use.custom"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N
    private static final String DIFF = "diff"; // NOI18N
    private static final String SHOW_OUTPUT_WINDOW = "show.output.window"; // NOI18N
    
    public static final int LATEST_VERSION = 3;

    private PhpCodefixerOptions() {
    }

    public static PhpCodefixerOptions getInstance() {
        return INSTANCE;
    }

    public String getPhpCodeFixerPath() {
        String phpCodefixerPath = getPreferences().get(PHP_CS_FIXER_PATH, null);

        // check whether file exists
        if (!StringUtils.isEmpty(phpCodefixerPath)) {
            File file = new File(phpCodefixerPath);
            if (!file.exists()) {
                phpCodefixerPath = ""; // NOI18N
                setPhpCodeFixerPath(phpCodefixerPath);
            }
        }

        return phpCodefixerPath;
    }

    public void setPhpCodeFixerPath(String path) {
        getPreferences().put(PHP_CS_FIXER_PATH, path);
    }
    
    public boolean showOutputWindow() {
        return getPreferences().getBoolean(SHOW_OUTPUT_WINDOW, true);
    }

    public void setShowOutputWindow(boolean show) {
        getPreferences().putBoolean(SHOW_OUTPUT_WINDOW, show);
    }
    
    
    private Preferences getPreferences() {
        return NbPreferences.forModule(PhpCodefixerOptions.class).node(PREFERENCES_PATH);
    }

}

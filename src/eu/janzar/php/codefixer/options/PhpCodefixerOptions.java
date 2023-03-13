/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package eu.janzar.php.codefixer.options;

import eu.janzar.php.codefixer.commands.PhpCodefixer;
import eu.janzar.php.codefixer.commands.PhpStan;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.FileUtils;
import org.netbeans.modules.php.api.util.StringUtils;
import org.openide.util.NbPreferences;

public class PhpCodefixerOptions {

    private static final PhpCodefixerOptions INSTANCE = new PhpCodefixerOptions();
    private static final String PREFERENCES_PATH = "phpcbf"; // NOI18N
    private static final String PHP_CODEFIXER_PATH = "php-codefixer.fixerpath"; // NOI18N
    private static final String PHP_CODESNIFFER_PATH = "php-codefixer.snifferpath"; // NOI18N
    private static final String PHP_STAN_PATH = "php-codefixer.stanpath"; // NOI18N
    private static final String EXTENSIONS = "php-codefixer.extensions"; // NOI18N
    private static final String ENCODING = "php-codefixer.encoding"; // NOI18N
    private static final String STANCONFIG = "php-codefixer.stanconfig"; // NOI18N
    private static final String STANAUTOLOAD = "php-codefixer.stanautoload"; // NOI18N
    private static final String STANCUSTOM = "php-codefixer.stancustom"; // NOI18N
    private static final String FIXERCUSTOM = "php-codefixer.fixercustom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N
    private static final String STANLEVEL = "php-codefixer.stanlevel"; // NOI18N

    private static final String RUN_ON_SAVE = "run.on.save"; // NOI18N

    private static final String RULES = "php-codefixer.rules"; // NOI18N

    // common
    private static final String USE_CUSTOM = "use.custom"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N

    private static final String DIFF = "diff"; // NOI18N

    public static final int LATEST_VERSION = 3;

    private PhpCodefixerOptions() {
    }

    public static PhpCodefixerOptions getInstance() {
        return INSTANCE;
    }

    public String getPhpCodeFixerPath() {
        String phpCodefixerPath = getPreferences().get(PHP_CODEFIXER_PATH, null);

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
        getPreferences().put(PHP_CODEFIXER_PATH, path);
    }

    public String getPhpCodeSnifferPath() {
        String phpCodesnifferPath = getPreferences().get(PHP_CODESNIFFER_PATH, null);

        // check whether file exists
        if (!StringUtils.isEmpty(phpCodesnifferPath)) {
            File file = new File(phpCodesnifferPath);
            if (!file.exists()) {
                phpCodesnifferPath = ""; // NOI18N
                setPhpCodeSnifferPath(phpCodesnifferPath);
            }
        }

        return phpCodesnifferPath;
    }

    public void setPhpCodeSnifferPath(String path) {
        getPreferences().put(PHP_CODESNIFFER_PATH, path);
    }

    public String getPhpStanPath() {
        String phpStanPath = getPreferences().get(PHP_STAN_PATH, null);

        // check whether file exists
        if (!StringUtils.isEmpty(phpStanPath)) {
            File file = new File(phpStanPath);
            if (!file.exists()) {
                phpStanPath = ""; // NOI18N
                setPhpStanPath(phpStanPath);
            }
        }

        return phpStanPath;
    }

    public void setPhpStanPath(String path) {
        getPreferences().put(PHP_STAN_PATH, path);
    }

    public void setExtensions(String extensions) {
        getPreferences().put(EXTENSIONS, extensions);
    }

    public String getExtensions() {
        return getPreferences().get(EXTENSIONS, "php,phtml,phpt"); // NOI18N
    }

    public void setEncoding(String extensions) {
        getPreferences().put(ENCODING, extensions);
    }

    public String getEncoding() {
        return getPreferences().get(ENCODING, "utf-8"); // NOI18N
    }

    public void setStanConfiguration(String extensions) {
        getPreferences().put(STANCONFIG, extensions);
    }

    public String getStanConfiguration() {
        return getPreferences().get(STANCONFIG, "phpstan.neon.dist"); // NOI18N
    }

    public void setStanAutoload(String extensions) {
        getPreferences().put(STANAUTOLOAD, extensions);
    }

    public String getStanAutoload() {
        return getPreferences().get(STANAUTOLOAD, "vendor/autoload.php"); // NOI18N
    }

    public void setStanCustom(String custom) {
        getPreferences().put(STANCUSTOM, custom);
    }

    public String getStanCustom() {
        return getPreferences().get(STANCUSTOM, ""); // NOI18N
    }

    public void setFixerCustom(String custom) {
        getPreferences().put(FIXERCUSTOM, custom);
    }

    public String getFixerCustom() {
        return getPreferences().get(FIXERCUSTOM, ""); // NOI18N
    }

    public void setRules(String custom) {
        getPreferences().put(RULES, custom);
    }

    public String getRules() {
        return getPreferences().get(RULES, "ruleset.xml"); // NOI18N
    }

    public void setVerbose(Boolean verbose) {
        getPreferences().putBoolean(VERBOSE, verbose);
    }

    public Boolean getVerbose() {
        return getPreferences().getBoolean(VERBOSE, false); // NOI18N
    }

    public void setLevel(int extensions) {
        getPreferences().putInt(STANLEVEL, extensions);
    }

    public int getLevel() {
        return getPreferences().getInt(STANLEVEL, 5); // NOI18N
    }

    public List<String> getAllOptions(PhpModule phpModule) {
        List<String> all = new ArrayList<>();
        if (!getRules().isEmpty()) {
            all.add(String.format(PhpCodefixer.RULES_PARAM, phpModule.getProjectDirectory().getPath().replaceAll(" ","\\ ")+"/"+getRules()));
        }

        if (!getEncoding().isEmpty()) {
            all.add(String.format(PhpCodefixer.ENCODING_PARAM, getEncoding()));
        }

        if (!getExtensions().isEmpty()) {
            all.add(String.format(PhpCodefixer.EXTENSIONS_PARAM, getExtensions()));
        }

        if (!getFixerCustom().isEmpty()) {
            all.addAll(StringUtils.explode(getFixerCustom(), " ")); // NOI18N
        }

        return all;
    }

    public List<String> getAllStanOptions(PhpModule phpModule) {
        List<String> all = new ArrayList<>();
        if (getLevel() != 0) {
            all.add(String.format(PhpStan.LEVEL_PARAM, getLevel()));
        }
        if (!getStanConfiguration().isEmpty()) {
            all.add(String.format(PhpStan.CONFIG_PARAM, phpModule.getProjectDirectory().getPath().replaceAll(" ","\\ ")+"/"+getStanConfiguration()));
        }
        if (!getStanAutoload().isEmpty()) {
            all.add(String.format(PhpStan.AUTOLOAD_PARAM, phpModule.getProjectDirectory().getPath().replaceAll(" ","\\ ")+"/"+getStanAutoload()));
        }

        if (!getStanCustom().isEmpty()) {
            all.addAll(StringUtils.explode(getStanCustom(), " ")); // NOI18N
        }

        return all;
    }

    private Preferences getPreferences() {
        return NbPreferences.forModule(PhpCodefixerOptions.class).node(PREFERENCES_PATH);
    }

}

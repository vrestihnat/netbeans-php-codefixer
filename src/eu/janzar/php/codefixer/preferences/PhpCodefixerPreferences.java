/*
 * Copyright 2019 junichi11.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package eu.janzar.php.codefixer.preferences;

import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import org.netbeans.modules.php.api.util.StringUtils;
import eu.janzar.php.codefixer.commands.PhpCodefixer;
import eu.janzar.php.codefixer.commands.PhpStan;

public final class PhpCodefixerPreferences {

    private static final String USE_GLOBAL = "use-global"; // NOI18N
    private static final String USE_PROJECT = "use-project"; // NOI18N
    private static final String RUN_ON_SAVE = "run-on-save"; // NOI18N
    private static final String LEVEL = "level"; // NOI18N
    private static final String CONFIG = "config"; // NOI18N
    private static final String FIXERS = "fixers"; // NOI18N
    private static final String ENCODING = "encoding"; // NOI18N
    private static final String EXTENSIONS = "extensions"; // NOI18N
    private static final String AUTOLOAD = "autoload"; // NOI18N
    private static final String RULES = "rules"; // NOI18N
    private static final String CUSTOM = "custom"; // NOI18N
    private static final String STANCUSTOM = "stan-custom"; // NOI18N
    private static final String VERBOSE = "verbose"; // NOI18N

    private PhpCodefixerPreferences() {
    }

    public static boolean useGlobal(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_GLOBAL, true);
    }

    public static void setGlobal(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_GLOBAL, use);
    }

    public static boolean useProject(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(USE_PROJECT, false);
    }

    public static void setProject(PhpModule phpModule, boolean use) {
        getPreferences(phpModule).putBoolean(USE_PROJECT, use);
    }

    public static int getLevel(PhpModule phpModule) {
        return getPreferences(phpModule).getInt(LEVEL, 0); // NOI18N
    }

    public static void setLevel(PhpModule phpModule, int level) {
        getPreferences(phpModule).putInt(LEVEL, level);
    }

    public static String getConfig(PhpModule phpModule) {
        return getPreferences(phpModule).get(CONFIG, ""); // NOI18N
    }

    public static void setConfig(PhpModule phpModule, String config) {
        getPreferences(phpModule).put(CONFIG, config);
    }

    public static String getFixers(PhpModule phpModule) {
        return getPreferences(phpModule).get(FIXERS, ""); // NOI18N
    }

    public static void setFixers(PhpModule phpModule, String fixers) {
        getPreferences(phpModule).put(FIXERS, fixers);
    }

    public static String getRules(PhpModule phpModule) {
        return getPreferences(phpModule).get(RULES, ""); // NOI18N
    }

    public static void setRules(PhpModule phpModule, String rules) {
        getPreferences(phpModule).put(RULES, rules);
    }

    public static String getCustom(PhpModule phpModule) {
        return getPreferences(phpModule).get(CUSTOM, ""); // NOI18N
    }

    public static void setCustom(PhpModule phpModule, String custom) {
        getPreferences(phpModule).put(CUSTOM, custom);
    }
    public static String getStanCustom(PhpModule phpModule) {
        return getPreferences(phpModule).get(STANCUSTOM, ""); // NOI18N
    }

    public static void setStanCustom(PhpModule phpModule, String custom) {
        getPreferences(phpModule).put(STANCUSTOM, custom);
    }

    public static boolean isRunOnSave(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(RUN_ON_SAVE, false);
    }

    public static void setRunOnSave(PhpModule phpModule, boolean isRunOnSave) {
        getPreferences(phpModule).putBoolean(RUN_ON_SAVE, isRunOnSave);
    }

    public static boolean isVerbose(PhpModule phpModule) {
        return getPreferences(phpModule).getBoolean(VERBOSE, false);
    }

    public static void setVerbose(PhpModule phpModule, boolean isVerbose) {
        getPreferences(phpModule).putBoolean(VERBOSE, isVerbose);
    }

    public static String getEncoding(PhpModule phpModule) {
        return getPreferences(phpModule).get(ENCODING, ""); // NOI18N
    }

    public static void setEncoding(PhpModule phpModule, String encoding) {
        getPreferences(phpModule).put(ENCODING, encoding);
    }

    public static String getExtensions(PhpModule phpModule) {
        return getPreferences(phpModule).get(EXTENSIONS, ""); // NOI18N
    }

    public static void setExtensions(PhpModule phpModule, String config) {
        getPreferences(phpModule).put(EXTENSIONS, config);
    }

    public static String getAutoload(PhpModule phpModule) {
        return getPreferences(phpModule).get(AUTOLOAD, ""); // NOI18N
    }

    public static void setAutoload(PhpModule phpModule, String config) {
        getPreferences(phpModule).put(AUTOLOAD, config);
    }

    public static List<String> getAllOptions(PhpModule phpModule) {
        List<String> all = new ArrayList<>();
        if (!getRules(phpModule).isEmpty()) {
            all.add(String.format(PhpCodefixer.RULES_PARAM, getRules(phpModule)));
        }

        if (!getExtensions(phpModule).isEmpty()) {
            all.add(String.format(PhpCodefixer.EXTENSIONS_PARAM, getConfig(phpModule)));
        }
        
        if (!getEncoding(phpModule).isEmpty()) {
            all.add(String.format(PhpCodefixer.ENCODING_PARAM, getConfig(phpModule)));
        }

        if (!getCustom(phpModule).isEmpty()) {
            all.addAll(StringUtils.explode(getCustom(phpModule), " ")); // NOI18N
        }
        return all;
    }
    
    public static List<String> getAllStanOptions(PhpModule phpModule) {
        List<String> all = new ArrayList<>();
        if (getLevel(phpModule) != 0) {
            all.add(String.format(PhpStan.LEVEL_PARAM, getLevel(phpModule)));
        }
        
        if (!getConfig(phpModule).isEmpty()) {
            all.add(String.format(PhpStan.CONFIG_PARAM, getConfig(phpModule)));
        }
        
        if (!getAutoload(phpModule).isEmpty()) {
            all.add(String.format(PhpStan.AUTOLOAD_PARAM, getConfig(phpModule)));
        }

        if (!getStanCustom(phpModule).isEmpty()) {
            all.addAll(StringUtils.explode(getCustom(phpModule), " ")); // NOI18N
        }
        return all;
    }
    

    private static Preferences getPreferences(PhpModule phpModule) {
        return phpModule.getPreferences(PhpCodefixerPreferences.class, true);
    }
}

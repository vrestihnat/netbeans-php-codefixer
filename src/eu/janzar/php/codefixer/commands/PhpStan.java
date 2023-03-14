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
package eu.janzar.php.codefixer.commands;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.netbeans.api.extexecution.ExecutionDescriptor;
import org.netbeans.api.extexecution.base.input.InputProcessor;
import org.netbeans.api.extexecution.base.input.InputProcessors;
import org.netbeans.api.extexecution.base.input.LineProcessor;
import org.netbeans.modules.php.api.executable.InvalidPhpExecutableException;
import org.netbeans.modules.php.api.executable.PhpExecutable;
import org.netbeans.modules.php.api.executable.PhpExecutableValidator;
import org.netbeans.modules.php.api.phpmodule.PhpModule;
import eu.janzar.php.codefixer.options.PhpCodefixerOptions;
import eu.janzar.php.codefixer.options.PhpQaOptionsPanelController;
import org.openide.filesystems.FileObject;
import org.openide.filesystems.FileUtil;
import org.openide.util.Exceptions;
import org.openide.util.NbBundle;
import org.openide.windows.InputOutput;

/**
 * @see https://github.com/FriendsOfPHP/PHP-CS-Fixer
 * @author junichi11
 */
public final class PhpStan {

    // PHP CS Fixer
    public static final String NAME = "phpstan"; // NOI18N
    public static final String NAME_LONG = NAME + ".phar"; // NOI18N
    public static final String DOWNLOAD_URL = ""; // NOI18N
    private final String phpstanPath;
    private boolean useSilentDescriptor;
    // commands
    private static final String FIX_COMMAND = "analyse"; // NOI18N
    //parameters
    public static final String VERBOSE_PARAM = "--verbose"; // NOI18N
    public static final String CONFIG_PARAM = "--configuration=%s"; // NOI18N
    public static final String LEVEL_PARAM = "--level=%s"; // NOI18N
    public static final String AUTOLOAD_PARAM = "--autoload-file=%s"; // NOI18N
    private static final List<String> DEFAULT_PARAMS = Arrays.asList(
    "--no-progress",
    "--no-ansi"
    ); // NOI18N

    private static final ExecutionDescriptor DEFAULT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .optionsPath(PhpQaOptionsPanelController.getOptionsPath())
            .controllable(true)
            .frontWindow(true)
            .frontWindowOnError(true)
            .inputVisible(false)
            .showProgress(true);

    private static final ExecutionDescriptor NO_OUTPUT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .inputOutput(InputOutput.NULL);
    private static final Logger LOGGER = Logger.getLogger(PhpStan.class.getName());

    private PhpStan(String phpstanPath) {
        this(phpstanPath, false);
    }

    private PhpStan(String phpstanPath, boolean useSilentDescriptor) {
        this.phpstanPath = phpstanPath;
        this.useSilentDescriptor = useSilentDescriptor;
    }

    public static PhpStan getDefault() throws InvalidPhpExecutableException {
        String phpstanPath = PhpCodefixerOptions.getInstance().getPhpStanPath();
//        String phpstanPath = "c://_jzaruba//php8.1.16//phpstan.phar --configuration=\"C://Users//Zaruba jan//Documents//NetBeansProjects//eshop//phpstan.neon.dist\" --autoload-file=\"c://Users//Zaruba jan//Documents//NetBeansProjects//eshop//vendor//autoload.php\" --level=5   ";
        return newInstance(phpstanPath);
    }

    public static PhpStan newInstance(String scriptPath) throws InvalidPhpExecutableException {
        return newInstance(scriptPath, false);
    }

    public static PhpStan newInstance(String scriptPath, boolean useSilentDescriptor) throws InvalidPhpExecutableException {
        String warning = validate(scriptPath);
        if (warning != null) {
            LOGGER.log(Level.WARNING, "PHP Stan path is not set."); // NOI18N
            throw new InvalidPhpExecutableException(warning);
        }
        return new PhpStan(scriptPath, useSilentDescriptor);
    }

    @NbBundle.Messages("PhpStan.script.label=PHP Stan")
    private static String validate(String phpcsfixerPath) {
        return PhpExecutableValidator.validateCommand(phpcsfixerPath, Bundle.PhpStan_script_label());
    }

    @NbBundle.Messages({
        "# {0} - command name",
        "PhpStan.run=PHP Stan ({0})"
    })
    public Future<Integer> run(PhpModule phpModule, String... params) {
        return runCommand(phpModule, FIX_COMMAND, Bundle.PhpStan_run(FIX_COMMAND), Arrays.asList(params));
    }


    private Future<Integer> runCommand(PhpModule phpModule, String command, String title) {
        return runCommand(phpModule, command, title, Collections.<String>emptyList());
    }

    private Future<Integer> runCommand(PhpModule phpModule, String command, String title, List<String> params) {
        PhpExecutable phpcsfixer = getPhpExecutable(phpModule, title);
        if (phpcsfixer == null) {
            return null;
        }
        return phpcsfixer
                .additionalParameters(mergeParameters(command, DEFAULT_PARAMS, params))
                .run(getDescriptor(phpModule));
    }

    private List<String> mergeParameters(String command, List<String> defaultParams, List<String> params) {
        List<String> allParams = new ArrayList<>(defaultParams.size() + params.size() + 1);
        allParams.add(command);
        allParams.addAll(params);
        allParams.addAll(defaultParams);
        return allParams;
    }

    private PhpExecutable getPhpExecutable(PhpModule phpModule, String title) {
        FileObject sourceDirectory = null;
        if (phpModule != null) {
            sourceDirectory = phpModule.getSourceDirectory();
        }
        PhpExecutable phpcsfixer = new PhpExecutable(phpstanPath)
                //.optionsSubcategory(PhpCodefixerOptionsPanelController.OPTIONS_SUBPATH)
                .displayName(title);
        if (sourceDirectory != null) {
            phpcsfixer.workDir(FileUtil.toFile(sourceDirectory));
        }
        return phpcsfixer;
    }

    private ExecutionDescriptor getDescriptor(PhpModule phpModule) {
        ExecutionDescriptor descriptor;
        descriptor = DEFAULT_EXECUTION_DESCRIPTOR;
        descriptor = descriptor.postExecution(() -> {});
        if (phpModule != null) {
            final FileObject sourceDirectory = phpModule.getSourceDirectory();
            if (sourceDirectory != null) {
                descriptor = descriptor.postExecution(() -> {
                    // refresh sources after running command
                    sourceDirectory.refresh();
                });
            }
        }
        return descriptor;
    }

    /**
     * Get InputProcessorFactory.
     *
     * @param lineProcessor
     * @return InputProcessorFactory
     */
    private ExecutionDescriptor.InputProcessorFactory2 getOutputProcessorFactory(final LineProcessor lineProcessor) {
        return (InputProcessor defaultProcessor) -> InputProcessors.ansiStripping(InputProcessors.bridge(lineProcessor));
    }

    private static class DefaultLineProcessor implements LineProcessor {

        private final StringBuilder result = new StringBuilder();
        private final List<String> list = new ArrayList<>();

        @Override
        public void processLine(String line) {
            list.add(line);
            if (!list.isEmpty()) {
                result.append("\n"); // NOI18N
            }
            result.append(line);
        }

        @Override
        public void reset() {
        }

        @Override
        public void close() {
        }

        public String getResult() {
            return result.toString();
        }

        public List<String> asLines() {
            return list;
        }
    }
}

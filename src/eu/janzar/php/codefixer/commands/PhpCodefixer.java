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

public final class PhpCodefixer {

    // PHP Code Fixer
    public static final String NAME = "phpcbf"; // NOI18N
    public static final String NAME_LONG = NAME + ".phar"; // NOI18N
    public static final String DOWNLOAD_URL = ""; // NOI18N
    private final String phpcodefixerPath;
    private final String phpcodesnifferPath;
    private boolean isDryRun;
    private boolean useSilentDescriptor;
   //parameters
    public static final String VERBOSE_PARAM = "-vvv"; // NOI18N
    public static final String EXTENSIONS_PARAM = "--extensions=%s"; // NOI18N
    public static final String ENCODING_PARAM = "--encoding=%s"; // NOI18N
    public static final String RULES_PARAM = "--standard=%s"; // NOI18N
    private static final List<String> DEFAULT_PARAMS = Arrays.asList(
            "-nspq", // NOI18N
            "--colors"); // NOI18N

    private static final ExecutionDescriptor DEFAULT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .optionsPath(PhpQaOptionsPanelController.getOptionsPath())
            .controllable(true)
            .frontWindow(true)
            .frontWindowOnError(true)
            .inputVisible(false)
            .showProgress(true);

    private static final ExecutionDescriptor NO_OUTPUT_EXECUTION_DESCRIPTOR = new ExecutionDescriptor()
            .inputOutput(InputOutput.NULL);
    private static final Logger LOGGER = Logger.getLogger(PhpCodefixer.class.getName());

    
    private PhpCodefixer(String phpcsfixerPath, String phpcssnifferPath) {
        this(phpcsfixerPath,phpcssnifferPath, false);
    }

    private PhpCodefixer(String phpcsfixerPath, String phpcssnifferPath, boolean useSilentDescriptor) {
        this.phpcodefixerPath = phpcsfixerPath;
        this.phpcodesnifferPath = phpcssnifferPath;
        this.useSilentDescriptor = useSilentDescriptor;
        this.isDryRun = false;
    }

    public static PhpCodefixer getDefault() throws InvalidPhpExecutableException {
        String phpcodefixerPath = PhpCodefixerOptions.getInstance().getPhpCodeFixerPath();
        //String phpcodefixerPath = "c://_jzaruba//php8.1.16//phpcbf.phar --standard=\"C://Users//Zaruba jan//Documents//NetBeansProjects//eshop//ruleset.xml\"  --extensions=\"php,phtml,phpt\" --encoding=utf-8  ";
        //String phpsnifferPath = "c://_jzaruba//php8.1.16//phpcs.phar --standard=\"C://Users//Zaruba jan//Documents//NetBeansProjects//eshop//ruleset.xml\"  --extensions=\"php,phtml,phpt\" --encoding=utf-8  ";
        String phpsnifferPath = PhpCodefixerOptions.getInstance().getPhpCodeSnifferPath();
        return newInstance(phpcodefixerPath, phpsnifferPath);
    }

    public static PhpCodefixer newInstance(String scriptPath, String scriptPath2) throws InvalidPhpExecutableException {
        return newInstance(scriptPath, scriptPath2, false);
    }

    public static PhpCodefixer newInstance(String scriptPath, String scriptPath2, boolean useSilentDescriptor) throws InvalidPhpExecutableException {
        String warning = validate(scriptPath);
        if (warning != null) {
            LOGGER.log(Level.WARNING, "PHP CS Fixer path is not set."); // NOI18N
            throw new InvalidPhpExecutableException(warning);
        }
        
        warning = validate(scriptPath2);
        if (warning != null) {
            LOGGER.log(Level.WARNING, "PHP CS Sniffer path is not set."); // NOI18N
            throw new InvalidPhpExecutableException(warning);
        }
        
        return new PhpCodefixer(scriptPath, scriptPath2,useSilentDescriptor);
    }

    @NbBundle.Messages("PhpCsFixer.script.label=PHP Code Fixer")
    private static String validate(String phpcsfixerPath) {
        return PhpExecutableValidator.validateCommand(phpcsfixerPath, Bundle.PhpCsFixer_script_label());
    }

    @NbBundle.Messages({
        "PhpCodefixer.run=PHP Code Fixer"
    })
    public Future<Integer> fix(PhpModule phpModule, String... params) {
        
        
        return runCommand(phpModule, "", Bundle.PhpCodefixer_run(), Arrays.asList(params));
    }
    
    @NbBundle.Messages({
        "PhpCodefixer.runtest=PHP Code Sniffer"
    })
    public Future<Integer> testRun(PhpModule phpModule, String... params) {
        isDryRun = true;
        return runCommand(phpModule, "", Bundle.PhpCodefixer_runtest(), Arrays.asList(params));
    }


    private Future<Integer> runCommand(PhpModule phpModule, String command, String title) {
        return runCommand(phpModule, command, title, Collections.<String>emptyList());
    }

    private Future<Integer> runCommand(PhpModule phpModule, String command, String title, List<String> params) {
        PhpExecutable phpcodefixer = getPhpExecutable(phpModule, title);
        if (phpcodefixer == null) {
            return null;
        }
        return phpcodefixer
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
        String path = phpcodefixerPath;
        if (isDryRun){
            path = phpcodesnifferPath;
        }
        
        if (phpModule != null) {
            sourceDirectory = phpModule.getSourceDirectory();
        }
        PhpExecutable phpcodefixer = new PhpExecutable(path)
                //.optionsSubcategory(PhpCodefixerOptionsPanelController.OPTIONS_SUBPATH)
                .displayName(title);
        if (sourceDirectory != null) {
            phpcodefixer.workDir(FileUtil.toFile(sourceDirectory));
        }
        return phpcodefixer;
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

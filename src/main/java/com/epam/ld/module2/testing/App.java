package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.datasource.ConsoleInput;
import com.epam.ld.module2.testing.datasource.FileInput;
import com.epam.ld.module2.testing.datasource.Input;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.File;


/**
 * This is the main App class
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);
    private static Messenger messenger;
    private static ConsoleInput consoleInput;
    private static FileInput fileInput;

    /**
     * Instantiates a new App.
     *
     * @param messenger     the messenger
     * @param consoleInput the consoleInput
     * @param fileInput the fileinput
     */
    public App(Messenger messenger, ConsoleInput consoleInput, FileInput fileInput) {
        this.messenger = messenger;
        this.consoleInput = consoleInput;
        this.fileInput = fileInput;
    }

    /**
     * main method.
     *
     * @param args args we get from command
     */
    public static void main(String[] args) {

        App app = new App(new Messenger(new MailServer(), new TemplateEngine()), new ConsoleInput(),
                new FileInput());
        // this is to properly configure the logger
        BasicConfigurator.configure();

        // Get Input from file
        if (args.length > 0 && !(args[0].trim().isEmpty())) {
            app.getInputFromFile(args[0]);
            return;
        }

        // Get input from console
        app.getInputFromConsole();

    }

    /**
     * get input from file helper method.
     *
     * @param file
     */
    public void getInputFromFile(String file) {

        logger.info("The provided file path is: " + file);
        try {
            File file1 = new File(file);
            Input input = fileInput.getInput(file1);
            Template template = new Template(input.getTag(), input.getMessage());
            Client client = new Client(input.getAddress());
            messenger.sendMessage(client, template);

        } catch (IllegalArgumentException e) {
            logger.error("Caught Exception: " + e.getMessage());
        }
    }

    /**
     * get input from console helper method.
     */
    public void getInputFromConsole() {

        // I am using the consoleInput for better readability.
        Input input = consoleInput.getInput();
        Template template = new Template(input.getTag(), input.getMessage());
        Client client = new Client(input.getAddress());
        logger.info(messenger.sendMessage(client, template));

    }
}

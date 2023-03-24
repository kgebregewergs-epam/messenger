package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.datasource.ConsoleInput;
import com.epam.ld.module2.testing.datasource.FileInput;
import com.epam.ld.module2.testing.datasource.Input;
import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import java.io.*;

/**
 * This is the main App class
 */
public class App {

    private static final Logger logger = Logger.getLogger(App.class);
    private static final TemplateEngine templateEngine = new TemplateEngine();
    private static final MailServer mailSever = new MailServer();
    private static Messenger messenger = null;
    private static ConsoleInput consoleInput;


    /**
     *main method.
     *
     * @param args   args we get from command
     */
    public static void main(String[] args) {
        messenger = new Messenger(mailSever, templateEngine);

        // this is to properly configure the logger
        BasicConfigurator.configure();

        // Get Input from file
        if (args.length > 0 && !(args[0].trim().isEmpty())) {
            getInputFromFile(args[0]);
            return;
        }

        // Get input from console
        getInputFromConsole();

    }

    /**
     * get input from file helper method.
     *
     * @param file
     */
    private static void getInputFromFile(String file) {

        logger.info("The provided file path is: " + file);
        try {
            FileInput fileInput = new FileInput();
            Input input = fileInput.getInput(file);
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
    public static void getInputFromConsole() {

        // I am using the consoleInput for better readability.
        consoleInput = new ConsoleInput();
        Input input = consoleInput.getInput();
        Template template = new Template(input.getTag(), input.getMessage());
        Client client = new Client(input.getAddress());
        logger.info(messenger.sendMessage(client, template));

    }
}

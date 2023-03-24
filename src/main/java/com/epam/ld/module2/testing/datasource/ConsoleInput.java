package com.epam.ld.module2.testing.datasource;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.template.Template;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Then interface to get input from console.
 */
public class ConsoleInput {
    private static final Logger logger = Logger.getLogger(ConsoleInput.class);

    /**
     * get Input.
     *
     * @return an Input data
     */
    public Input getInput() {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String template, value, client;
        try {
            logger.info("Please enter a template: ");
            template = bufferedReader.readLine();
            logger.info("Please enter a value: ");
            value = bufferedReader.readLine();
            logger.info("Please enter the address: ");
            client = bufferedReader.readLine();
            Client client1 = new Client();
            client1.setAddresses(client);
            Template template1 = new Template();
            template1.setValue(value);
            template1.setTag(template);
            return new Input(template, value, client);
        } catch (IllegalArgumentException | IOException e) {
            logger.error("Caught Exception: " + e.getMessage());
        }
        finally {
            try {
                bufferedReader.close();
            } catch (IOException ioException) {
                logger.error("Caught Exception: " + ioException.getMessage());
            }

        }

        return new Input(null, null, null);
    }
}

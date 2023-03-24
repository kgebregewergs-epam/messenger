package com.epam.ld.module2.testing.datasource;

import org.apache.log4j.Logger;

import java.io.*;

/**
 * The class to get input from file.
 */
public class FileInput {
    private static final Logger logger = Logger.getLogger(FileInput.class);


    /**
     * getInput.
     *
     * @param file   file name
     * @return Input
     */
    public Input getInput(String file) {
        logger.info("The provided file path is: " + file);
        String template = null, value = null, client = null;
        BufferedReader bufferReader = null;
        try {
            File input = new File(file);
            bufferReader  = new BufferedReader(new FileReader(input));
            int inputSqs = 0;
            String currentVal;
            while ((currentVal = bufferReader.readLine()) != null) {
                if(inputSqs == 0) {
                    template = currentVal;
                    inputSqs++;
                }

                else if(inputSqs == 1) {
                    value = currentVal;
                    inputSqs++;
                }

                else if (inputSqs == 2) {
                    client = currentVal;
                    inputSqs = 0;
                }
            }

           return new Input(template, value, client);

        } catch (IOException e) {
            logger.error("Caught Exception: " + e.getMessage());

        }

        finally {
            try {
                bufferReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Input(null, null, null);
    }
}

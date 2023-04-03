package com.epam.ld.module2.testing.datasource;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

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
    public Input getInput(File file) {
        logger.info("The provided file path is: " + file);
        try {
            List<String> inputFromFile = Files.readAllLines(file.toPath());
            return new Input(inputFromFile.get(0), inputFromFile.get(1), inputFromFile.get(2));
        } catch (IOException e) {
            logger.info("Error reading from file: " + e.getMessage());
        }

        return new Input(null, null, null);
    }
}

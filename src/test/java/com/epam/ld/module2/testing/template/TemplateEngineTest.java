package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.datasource.ConsoleInput;
import com.epam.ld.module2.testing.datasource.FileInput;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class TemplateEngineTest {

    TemplateEngine templateEngine;
    Template template;
    Client client;
    ConsoleInput consoleInput;
    FileInput fileInput;

    @BeforeEach
    public void setUp() {
        templateEngine = new TemplateEngine();
        template = new Template();
        client = new Client();
        consoleInput = mock(ConsoleInput.class);
        fileInput = mock(FileInput.class);
    }


    @Test
    public void checkGenerateMessageWithValidTemplate() {

        template.setTag("${body}");
        template.setValue("Hi");

        // check the templateEngine
        String actual = templateEngine.generateMessage(template);
        String expected = "Hi: ${body}";
        assertEquals(actual, expected);
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenInvalidTagIsProvided() {

        template.setTag("${Invalid}");
        template.setValue("Hi");

        // set the client from the console input
        client.setAddresses("to be removed");

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
                    templateEngine.generateMessage(template);
                }
        );
        String expected = "The template must be valid";
        String actual = exception.getMessage();

        Assertions.assertEquals(expected, actual);
    }

    @Test()
    public void shouldThrowExceptionIfNonOfThePlaceHoldersAreNotProvided() {

        template.setTag(null);
        template.setValue(null);
        // set the client from the console input
        client.setAddresses(null);
        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            templateEngine.generateMessage(template);
        });
        String expected = "Provided with Invalid tag";
        String actual = exception.getMessage();
        Assertions.assertEquals(expected, actual);
    }
}

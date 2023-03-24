package com.epam.ld.module2.testing.template;

import com.epam.ld.module2.testing.Client;
import com.epam.ld.module2.testing.datasource.ConsoleInput;
import com.epam.ld.module2.testing.datasource.FileInput;
import com.epam.ld.module2.testing.datasource.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void checkGenerateMessageWithValidTemplateFromConsole() {
        when(consoleInput.getInput()).thenReturn(
                new Input("${body}", "Hi", "test@test")
        );

        // set the template from the console input
        template.setTag(consoleInput.getInput().getTag());
        template.setValue(consoleInput.getInput().getMessage());

        // set the client from the console input
        client.setAddresses(consoleInput.getInput().getAddress());

        // check the templateEngine
        String actual = templateEngine.generateMessage(template, client);
        String expected = "Hi: ${body}";
        assertEquals(actual, expected);
    }

    @Test
    public void checkGenerateMessageWithInValidTemplateFromConsole() {

        // Mock the console input
        when(consoleInput.getInput()).thenReturn(
                new Input("${Invalid}", "Hi", "test@test")
        );

        // set the template from the console input
        template.setTag(consoleInput.getInput().getTag());
        template.setValue(consoleInput.getInput().getMessage());

        // set the client from the console input
        client.setAddresses(consoleInput.getInput().getAddress());

        String result = templateEngine.generateMessage(template, client);

        Assertions.assertFalse("Hi: ${Invalid}: ".equals(result));
    }

    @Test()
    public void shouldThrowExceptionIfNonOfThePlaceHoldersAreNotProvidedFromConsole() {

        // mock the console input
        when(consoleInput.getInput()).thenReturn(
                new Input(null, null, null)
        );

        // set the template from the console input
        template.setTag(consoleInput.getInput().getTag());
        template.setValue(consoleInput.getInput().getMessage());

        // set the client from the console input
        client.setAddresses(consoleInput.getInput().getAddress());

        TemplateEngine templateEngine = new TemplateEngine();
        try {
            templateEngine.generateMessage(template, client);
            fail("My Method should throw Illegal Argument Exception");
        } catch (IllegalArgumentException illegalArgumentException) {
            System.out.println("Illegal Argument Exception: " + illegalArgumentException.getMessage());
        }
    }

    @Test
    public void checkGenerateMessageWithValidTemplateFromFile() {

        // Mock file input
        FileInput fileInput = mock(FileInput.class);
        when(fileInput.getInput(anyString())).thenReturn(
                new Input("${subject}", "This is important", "test@test")
        );

        // set the template from the file input
        Template template = new Template();
        template.setTag(fileInput.getInput(anyString()).getTag());
        template.setValue(fileInput.getInput(anyString()).getMessage());

        // set the client from the file input
        Client client = new Client();
        client.setAddresses(fileInput.getInput(anyString()).getAddress());

        TemplateEngine templateEngine = new TemplateEngine();
        String actual = templateEngine.generateMessage(template, client);
        String expected = "This is important: ${subject}";
        assertEquals(actual, expected);
    }

    @Test
    public void checkGenerateMessageWithInValidTemplateFromFile() {
        // Mock file input
        when(fileInput.getInput(anyString())).thenReturn(
                new Input("${hash}", "This is important", "test@test")
        );

        // set the template from the file input
        Template template = new Template();
        template.setTag(fileInput.getInput(anyString()).getTag());
        template.setValue(fileInput.getInput(anyString()).getMessage());

        // set the client from the file input
        client.setAddresses(fileInput.getInput(anyString()).getAddress());

        String result = templateEngine.generateMessage(template, client);
        Assertions.assertFalse("Hi: ${hash}: ".equals(result));
    }

    @Test
    public void shouldThrowExceptionIfNonOfThePlaceHoldersAreNotProvidedFromFile() throws IllegalArgumentException {

        // mock the file input
        when(fileInput.getInput(anyString())).thenReturn(
                new Input(null, null, null)
        );

        // set the template from the file input
        template.setTag(fileInput.getInput(anyString()).getTag());
        template.setValue(fileInput.getInput(anyString()).getMessage());

        // set the client from the file input
        client.setAddresses(fileInput.getInput(anyString()).getAddress());

        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> {
                    templateEngine.generateMessage(template, client);
                });

        String expectedMessage = "Provided with Invalid tag";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));

    }
}

package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessengerTest {

    @Spy
    TemplateEngine templateEngine;

    @Mock
    MailServer mailServer;

    @Mock
    Template template;

    @Mock
    Client client;

    @InjectMocks
    private Messenger messenger;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void checkSendMessageSuccessfully() {
        doReturn("Hi: ${body}").when(templateEngine).generateMessage(template);
        when(client.getAddresses()).thenReturn("test@Test");
        String result = messenger.sendMessage(client, template);
        assertTrue(result.contains("Hi: ${body}"));

    }

    @Test
    public void checkSendMessageShouldThrowNullPointerExceptionWhenClientIsNull() {
        doReturn(null).when(templateEngine).generateMessage(template);

        Exception exception = assertThrows(NullPointerException.class, () ->
        {
            messenger.sendMessage(null, template);
        });
        String expectedMessage = "The client must be not null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void checkSendMessageShouldThrowNullPointerExceptionWhenTemplateEngineIsNull() {
        doReturn(null).when(templateEngine).generateMessage(null);

        Exception exception = assertThrows(NullPointerException.class, () ->
        {
            messenger.sendMessage(client, null);
        });

        String expectedMessage = "The template must be not null";
        String actualMessage = exception.getMessage();
        System.out.println(actualMessage);
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

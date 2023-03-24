package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessengerTest {

    private TemplateEngine templateEngine;

    private  MailServer mailServer;

    private Template template;

    private Client client;
    private Messenger messenger;


    @BeforeEach
    public void setUp() {
        templateEngine = spy(TemplateEngine.class);
        mailServer = mock(MailServer.class);
        messenger = new Messenger(mailServer, templateEngine);
        template = mock(Template.class);
        client = mock (Client.class);
    }

    @Test
    public void checkSendMessageSuccessfully() {
        doReturn("Hi: ${body}").when(templateEngine).generateMessage(template, client);
        when(client.getAddresses()).thenReturn("test@Test");
        String result = messenger.sendMessage(client, template);
        assertTrue(result.contains("Hi: ${body}"));

    }

    @Test
    public  void checkSendMessageShouldThrowNullPointerExceptionWhenClientIsNull() {
        doReturn(null).when(templateEngine).generateMessage(template, null);

        Exception exception = assertThrows(NullPointerException.class, () ->
        {
            messenger.sendMessage(null, template);
        });
        String expectedMessage = "The client must be not null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public  void checkSendMessageShouldThrowNullPointerExceptionWhenTemplateEngineIsNull() {
        doReturn(null).when(templateEngine).generateMessage(null, client);

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

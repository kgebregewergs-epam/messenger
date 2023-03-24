package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Spy;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessengerTest {

    @Spy
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
    public  void checkSendMessageShouldFailWhenClientIsNull() {
        doReturn(null).when(templateEngine).generateMessage(template, null);
        String result = messenger.sendMessage(null, template);
        assertNull(result);
    }

    @Test
    public  void checkSendMessageShouldFailWhenTemplateEngineIsNull() {
        doReturn(null).when(templateEngine).generateMessage(null, client);
        String result = messenger.sendMessage(client, null);
        assertNull (result);
    }
}

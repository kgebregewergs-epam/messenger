package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MessengerTest {

    @Test
    public void checkSendMessageSuccessfully() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = spy(TemplateEngine.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Template template = mock(Template.class);
        Client client = mock (Client.class);
        doReturn("Hi: ${body}").when(templateEngine).generateMessage(template, client);
        String result = messenger.sendMessage(client, template);
        assertEquals("Hi: ${body}", result);

    }

    @Test
    public  void checkSendMessageShouldFailWhenClientIsNull() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = spy(TemplateEngine.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Template template = mock(Template.class);
        doReturn(null).when(templateEngine).generateMessage(template, null);
        String result = messenger.sendMessage(null, template);
        assertFalse(result != null);
    }

    @Test
    public  void checkSendMessageShouldFailWhenTemplateEngineIsNull() {
        MailServer mailServer = mock(MailServer.class);
        TemplateEngine templateEngine = spy(TemplateEngine.class);
        Messenger messenger = new Messenger(mailServer, templateEngine);
        Template template = mock(Template.class);
        Client client = mock (Client.class);
        doReturn(null).when(templateEngine).generateMessage(null, client);
        String result = messenger.sendMessage(client, null);
        assertFalse(result != null);
    }
}

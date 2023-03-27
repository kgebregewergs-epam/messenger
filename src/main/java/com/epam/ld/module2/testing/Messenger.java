package com.epam.ld.module2.testing;


import com.epam.ld.module2.testing.template.Template;
import com.epam.ld.module2.testing.template.TemplateEngine;

/**
 * The type Messenger.
 */
public class Messenger {
    private MailServer mailServer;
    private TemplateEngine templateEngine;

    /**
     * Instantiates a new Messenger.
     *
     * @param mailServer     the mail server
     * @param templateEngine the template engine
     */
    public Messenger(MailServer mailServer,
                     TemplateEngine templateEngine) {
        this.mailServer = mailServer;
        this.templateEngine = templateEngine;
    }

    /**
     * Send message.
     *
     * @param client   the client
     * @param template the template
     * @return String
     */
    public String sendMessage(Client client, Template template) {
        if (client == null) {
            throw new NullPointerException("The client must be not null");
        }

        if (template == null) {
            throw new NullPointerException("The template must be not null");
        }

        String messageContent =
                templateEngine.generateMessage(template);
        mailServer.send(client.getAddresses(), messageContent);

        return messageContent + " sending to: " + client.getAddresses();
    }
}
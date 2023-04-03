package com.epam.ld.module2.testing;


import org.apache.log4j.Logger;

/**
 * Mail server class.
 */
public class MailServer {

    private static final Logger logger = Logger.getLogger(MailServer.class.getName());
    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     * @return boolean
     */
    public Boolean send(String addresses, String messageContent) {
        if (addresses == null) {
            throw new NullPointerException("The addresses must be not null");
        }

        if (messageContent == null) {
            throw new NullPointerException("The message content must be not null");
        }


        logger.info("Sending the message Successfully message: " + messageContent);
        return true;
    }
}

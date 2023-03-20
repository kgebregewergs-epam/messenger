package com.epam.ld.module2.testing;

/**
 * Mail server class.
 */
public class MailServer {
    /**
     * Send notification.
     *
     * @param addresses  the addresses
     * @param messageContent the message content
     * @return boolean
     */
    public Boolean send(String addresses, String messageContent) {
        if (addresses == null && messageContent == null) {
            throw new NullPointerException("The addresses and message content must be not null");
        }

        if (addresses != null && messageContent != null) {
            System.out.format("Sending the message Successfully message:  ", messageContent);
            return true;
        }

        System.out.format("Unable to Send the message Successfully");
        return false;
    }
}

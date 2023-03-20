package com.epam.ld.module2.testing;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MailServerTest {

    @Test
    public void checkSuccessfulSendMessage() {
        MailServer mailServer = new MailServer();
        Boolean result  = mailServer.send("test@gmail.com", "just hi");
        assertTrue(result);
    }

    @Test
    public void checkUnSuccessfulSendingMessage() {
        MailServer mailServer = new MailServer();
        Boolean result  = mailServer.send("test@gmail.com", null);
        assertFalse(result);
    }

    @Test
    public void shouldThrowNullPointerExecution() throws NullPointerException {
        MailServer mailServer = new MailServer();
        try {
            Boolean result  = mailServer.send(null, null);
            fail();
        } catch (NullPointerException nullPointerException) {
            System.out.println("Null pointer Exception: " + nullPointerException.getMessage());
        }
    }
}

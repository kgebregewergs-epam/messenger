package com.epam.ld.module2.testing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import com.epam.ld.module2.testing.MessengerTestSuit.UnitTest;
import static org.junit.jupiter.api.Assertions.*;

@UnitTest
public class MailServerTest {

    MailServer mailServer;

    @BeforeEach
    public void setUp() {
        mailServer = new MailServer();
    }

    @Test
    public void ShouldSendSuccessfullyWhenPassedValidAddressAndTemplate() {
        Boolean result = mailServer.send("test@gmail.com", "just hi");
        assertTrue(result);
    }

    @ParameterizedTest
    @NullSource
    public void shouldThrowNullPointerExecutionWhenTheAddressIsNul(String address){

        Exception exception = assertThrows(NullPointerException.class, () ->
        {
            mailServer.send(address, "content");
        });

        String expectedMessage = "The addresses must be not null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldThrowNullPointerExecutionWhenTheMessageContentIsNul() {

        Exception exception = assertThrows(NullPointerException.class, () ->
        {
            mailServer.send("test@test.com", null);
        });

        String expectedMessage = "The message content must be not null";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }
}

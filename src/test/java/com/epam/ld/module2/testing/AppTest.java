package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.datasource.ConsoleInput;
import com.epam.ld.module2.testing.datasource.FileInput;
import com.epam.ld.module2.testing.datasource.Input;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class AppTest {

    @Mock
    Messenger messenger;


    @Mock
    ConsoleInput consoleInput;

    @Mock
    FileInput fileInput;

    @InjectMocks
    App app;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    public void checkSuccessfulSendingMessageFromConsoleInput() {

        Input input = mock(Input.class);
        when(consoleInput.getInput()).thenReturn(
                new Input("${body}", "This is important", "test@test")
        );
        when(input.getTag()).thenReturn("${body}");
        when(input.getMessage()).thenReturn("message");
        when(input.getAddress()).thenReturn("test@test.com");
        when(messenger.sendMessage(any(), any())).thenReturn("successful");
        app.getInputFromConsole();
        verify(messenger, times(1)).sendMessage(any(), any());
    }


    @Test
    public void checkSuccessfulSendingMessageFromFile() {

        Input input = mock(Input.class);
        when(fileInput.getInput(any())).thenReturn(
                new Input("${body}", "This is important", "test@test")
        );
        when(input.getTag()).thenReturn("${body}");
        when(input.getMessage()).thenReturn("message");
        when(input.getAddress()).thenReturn("test@test.com");
        when(messenger.sendMessage(any(), any())).thenReturn("successful");
        app.getInputFromFile(any());
        verify(messenger, times(1)).sendMessage(any(), any());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTheInputIsNull() {
        Input input = mock(Input.class);
        when(consoleInput.getInput()).thenReturn(
                new Input("${body}", "This is important", "test@test")
        );
        when(input.getTag()).thenReturn(null);
        when(input.getMessage()).thenReturn(null);
        when(input.getAddress()).thenReturn(null);
        when(messenger.sendMessage(any(), any())).thenThrow(new IllegalArgumentException("The Tag Must Not be Null"));

        Exception exception = Assertions.assertThrows(IllegalArgumentException.class, () -> {
            app.getInputFromConsole();
        });

        String expected = "The Tag Must Not be Null";
        String actual = exception.getMessage();

        Assertions.assertEquals(expected, actual);
    }
}

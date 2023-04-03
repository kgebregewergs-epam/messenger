package com.epam.ld.module2.testing;

import com.epam.ld.module2.testing.datasource.FileInput;
import com.epam.ld.module2.testing.datasource.Input;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertLinesMatch;
import com.epam.ld.module2.testing.MessengerTestSuit.UnitTest;

@UnitTest
public class FileInputTest {

    @TempDir
    static Path sharedTempDir;

    @Test
    public void checkYouGetAValidResultWhenYouUseSharedTemporaryDirectory() throws IOException {
        Path path = sharedTempDir.resolve("file.txt");
        List<String> content = Arrays.asList("${body}", "selam", "test@test.com");
        Files.write(path, content);

        Input result = new FileInput().getInput(path.toFile());
        Input expectedResult = new Input(content.get(0), content.get(1), content.get(2));

        assertAll(
                () -> assertTrue(Files.exists(path), "File should exist"),
                () -> assertEquals(result.getTag(), expectedResult.getTag()),
                () -> assertLinesMatch(content, Files.readAllLines(path))

        );
    }

}

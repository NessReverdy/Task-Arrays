package org.nessrev.task.service.reader;

import org.junit.jupiter.api.Test;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.reader.impl.FileReaderServiceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class FileReaderServiceImplTest {
  private final FileReaderServiceImpl fileReaderService =
    new FileReaderServiceImpl();


  @Test
  void readFile_shouldReturnFileContent_whenFileExists() throws Exception {
    Path file = Path.of("data/test.txt");
    Files.createDirectories(file.getParent());
    Files.write(file, List.of("line1", "line2", "line3"));

    List<String> result = fileReaderService.readFile("test.txt");

    assertEquals(List.of("line1", "line2", "line3"), result);

    Files.deleteIfExists(file);
  }

  @Test
  void readFile_shouldThrowException_whenFileNotExists() {
    String invalidFile = "not_existing_file.txt";

    assertThrows(CustomException.class, () -> {
      fileReaderService.readFile(invalidFile);
    });
  }

}

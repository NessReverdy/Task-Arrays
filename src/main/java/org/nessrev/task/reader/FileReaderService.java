package org.nessrev.task.reader;

import org.nessrev.task.exception.CustomException;

import java.util.List;

public interface FileReaderService {
  List<String> readFile(String filename) throws CustomException;
}

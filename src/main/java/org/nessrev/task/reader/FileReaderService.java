package org.nessrev.service.reader;

import org.nessrev.exceptions.CustomException;

import java.util.List;

public interface FileReaderService {
    List<String> readFile(String filename) throws CustomException;
}

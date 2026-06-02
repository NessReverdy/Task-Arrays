package org.nessrev.service.reader;

import org.nessrev.exceptions.FileReadException;

import java.util.List;

public interface FileReaderService {
    List<String> readFile(String filename) throws FileReadException;
}

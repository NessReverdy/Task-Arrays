package org.nessrev.task.reader.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.nessrev.task.exception.CustomException;
import org.nessrev.task.reader.FileReaderService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger logger = LogManager.getLogger(FileReaderServiceImpl.class);

    @Override
    public List<String> readFile(String fileName) throws CustomException {
        Path filePath = Path.of("data").resolve(fileName);
        logger.debug("Attempting to read file: {}", filePath);

        try {
            List<String> data = Files.readAllLines(filePath);
            logger.info("File successfully read. Lines count: {}", data.size());
            return data;

        } catch (IOException e) {
            logger.error("Error while reading file: {}", filePath, e);
            throw new CustomException("Failed to read file: " + filePath,e);
        }
    }
}
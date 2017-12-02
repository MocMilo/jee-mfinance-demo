package com.infoshareacademy.mfinance.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResourcesFileReader.class);
    private InputStream inputStream;
    private String resourceFileName;

    public ResourcesFileReader(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public String getFileAsString() {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            LOGGER.error("Failed to parse file as string.");
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOGGER.error("Failed to close input stream from ClassLoader resource.");
            }
        }
        return stringBuilder.toString();
    }
}

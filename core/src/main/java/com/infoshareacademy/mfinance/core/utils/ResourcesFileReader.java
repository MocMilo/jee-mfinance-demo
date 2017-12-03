package com.infoshareacademy.mfinance.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {
    private InputStream inputStream;
    private String resourceFileName;

    public ResourcesFileReader(String resourceFileName) {
        this.resourceFileName = resourceFileName;
    }

    public String getFileAsString() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return stringBuilder.toString();
    }
}

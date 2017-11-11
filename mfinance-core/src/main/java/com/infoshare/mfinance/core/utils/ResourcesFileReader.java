package com.infoshare.mfinance.core.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {

    private InputStream inputStream;

    public ResourcesFileReader(String resourceFileName) {

        inputStream = getClass().getClassLoader().getResourceAsStream(resourceFileName);
    }

    public String getFileAsString() throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder.toString();
    }
}

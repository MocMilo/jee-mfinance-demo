package com.infoshare.core.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {

    public String resourceFilePath;

    public ResourcesFileReader(String resourceFilePath) {
        this.resourceFilePath = resourceFilePath;
    }

    public String getFileAsString() throws IOException {

        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(this.resourceFilePath);
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuilder.append(line);
        }
        return stringBuilder
                .toString();
    }
}

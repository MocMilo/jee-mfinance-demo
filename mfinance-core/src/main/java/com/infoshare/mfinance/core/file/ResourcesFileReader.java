package com.infoshare.mfinance.core.file;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ResourcesFileReader {

    public String resourceFileName;
    private InputStream inputStream;

    public ResourcesFileReader(String resourceFileName) {
        this.resourceFileName = resourceFileName;
        inputStream = getClass().getClassLoader().getResourceAsStream(this.resourceFileName);
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

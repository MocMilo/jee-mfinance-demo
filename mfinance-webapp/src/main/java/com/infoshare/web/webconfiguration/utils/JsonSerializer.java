package com.infoshare.web.webconfiguration.utils;

import java.io.IOException;

import com.infoshare.web.webconfiguration.smtp.SmtpProperties;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JsonSerializer {

    private final String jsonString;

    public JsonSerializer(String jsonString)
    {
        this.jsonString = jsonString;
    }

    public SmtpProperties getProperties() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, SmtpProperties.class);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}

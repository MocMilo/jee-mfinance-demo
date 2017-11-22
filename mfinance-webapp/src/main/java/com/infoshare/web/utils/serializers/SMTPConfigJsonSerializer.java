package com.infoshare.web.utils.serializers;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoshare.web.model.webconfiguration.SMTPConfiguration;


public class SMTPConfigJsonSerializer {

    private final String jsonString;

    public SMTPConfigJsonSerializer(String jsonString)
    {
        this.jsonString = jsonString;
    }

    public SMTPConfiguration getProperties() throws IOException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(jsonString, SMTPConfiguration.class);
        } catch (IOException e) {
            throw new IOException();
        }
    }
}

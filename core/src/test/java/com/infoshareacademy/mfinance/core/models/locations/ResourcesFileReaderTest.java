package com.infoshareacademy.mfinance.core.models.locations;

import com.infoshareacademy.mfinance.core.utils.ResourcesFileReader;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;

public class ResourcesFileReaderTest {
    private String resourcesFilePath = "configuration/configuration-test.json";
    private ResourcesFileReader resourcesFileReader;

    @Before
    public void init() throws Exception {
        resourcesFileReader = new ResourcesFileReader(resourcesFilePath);
    }

    @Test
    public void shouldReturnFileContentAsNotEmptyString() throws Exception {
        String fileContent = resourcesFileReader.getFileAsString();
        assertThat(fileContent, not(equalTo(nullValue())));
        assertThat(fileContent, not(equalTo("")));
    }

    @Test(expected = Exception.class)
    public void shouldFailIfFileNotPresent() throws Exception {
        resourcesFilePath = "notPresentFile.test";
        new ResourcesFileReader(resourcesFilePath).getFileAsString();
    }
}
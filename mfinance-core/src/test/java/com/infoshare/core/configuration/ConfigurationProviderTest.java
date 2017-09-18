package com.infoshare.core.configuration;

import com.infoshare.core.models.configuration.Configuration;
import junit.framework.TestCase;


public class ConfigurationProviderTest extends TestCase {
    public void testGetConfiguration() throws Exception {

       //TODO implement the rest of Test cases

       ConfigurationProvider configurationProvider = new ConfigurationProvider();
       Configuration config =  configurationProvider.getConfiguration();
       System.out.println("folderbackup:"+ config.getCurrencyBackupFolderPath().getFolderPath());

    }

}
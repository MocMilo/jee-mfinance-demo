package com.infoshare.mfinance.core.providers.bossadata;

import com.infoshare.mfinance.core.providers.bossadata.ResourcesDataFilesProvider;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class ResourcesDataFilesProviderTest {

    ResourcesDataFilesProvider filesProvider = new ResourcesDataFilesProvider();

    @Test
    public void shouldCopyZipFilesFromAppResourcesToTempFolder(){

       boolean isSuccess = filesProvider.saveFilesInTemp();
       assertTrue(isSuccess);
    }
}
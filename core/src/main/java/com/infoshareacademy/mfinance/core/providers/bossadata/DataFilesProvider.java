package com.infoshareacademy.mfinance.core.providers.bossadata;

import java.io.IOException;

public interface DataFilesProvider {
     void saveDataFilesInTempFolders() throws IOException;
     void saveDataFilesInExplicitFolders() throws IOException;
}

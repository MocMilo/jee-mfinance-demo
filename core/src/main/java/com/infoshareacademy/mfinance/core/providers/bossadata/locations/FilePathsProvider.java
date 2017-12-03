package com.infoshareacademy.mfinance.core.providers.bossadata.locations;

import com.infoshareacademy.mfinance.core.models.configuration.Configuration;
import com.infoshareacademy.mfinance.core.models.locations.path.FilePath;
import com.infoshareacademy.mfinance.core.utils.TemporaryFoldersProviderUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FilePathsProvider {

    public List<FilePath> generateTempCurrencyFilePaths() {
        String folderPath = TemporaryFoldersProviderUtil
                .getCurrencyFolderPath()
                .getFolderPath();

        List<String> fileNames = getFileNameList(folderPath);
        List<FilePath> filePathList = generateFilePaths(folderPath, fileNames);
        return filePathList;
    }

    public List<FilePath> generateTempFundFilePaths() {
        String folderPath = TemporaryFoldersProviderUtil
                .getFundFolderPath()
                .getFolderPath();

        List<String> fileNames = getFileNameList(folderPath);
        List<FilePath> filePathList = generateFilePaths(folderPath, fileNames);
        return filePathList;
    }

    public List<FilePath> generateCurrencyFilePaths(Configuration configuration) {
        String folderPath = configuration.getCurrencyFolderPath().getFolderPath();
        List<String> fileNames = getFileNameList(folderPath);
        return generateFilePaths(folderPath, fileNames);
    }

    public List<FilePath> generateFundFilePaths(Configuration configuration) {
        String folderPath = configuration.getFundFolderPath().getFolderPath();
        List<String> fileNames = getFileNameList(folderPath);
        return generateFilePaths(folderPath, fileNames);
    }

    private List<String> getFileNameList(String folderPath) {
        List<String> fileNames = new ArrayList<>();
        File[] files = new File(folderPath).listFiles();
        if (files != null) {
            for (File file : files) {
                fileNames.add(file.getName());
            }
        }
        return fileNames;
    }

    private List<FilePath> generateFilePaths(String folderPath, List<String> fileList) {
        List<FilePath> filePaths = new ArrayList<>();
        for (String fileName : fileList) {
            filePaths.add(new FilePath(folderPath.concat(fileName)));
        }
        return filePaths;
    }
}

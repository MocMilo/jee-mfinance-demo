package com.infoshare.mfinance.core.providers.bossadata;

import com.infoshare.mfinance.core.models.configuration.Configuration;
import com.infoshare.mfinance.core.models.locations.path.FilePath;
import com.infoshare.mfinance.core.utils.TemporaryFoldersProviderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class FilePathsProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(FilePathsProvider.class);

    public List<FilePath> generateTempCurrencyFilePaths() {

        String folderPath = TemporaryFoldersProviderUtil
                .getCurrencyFolderPath()
                .getFolderPath();

        List<String> fileNames = getFileNameList(folderPath);

        List<FilePath> filePathList = generateFilePaths(folderPath, fileNames);

        for (FilePath item : filePathList) {
            LOGGER.trace("Provided file path:{}", item.getFilePath());
        }

        return filePathList;
    }

    public List<FilePath> generateTempFundFilePaths() {

        String folderPath = TemporaryFoldersProviderUtil
                .getFundFolderPath()
                .getFolderPath();

        List<String> fileNames = getFileNameList(folderPath);

        List<FilePath> filePathList = generateFilePaths(folderPath, fileNames);

        for (FilePath item : filePathList) {
            LOGGER.trace("Provided file path:{}", item.getFilePath());
        }

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
        for (File file : files) {
            if (file.isFile()) {
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

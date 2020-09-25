package com.epam.mentoring.files.task2.util;

import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Kaikenov Adilhan
 **/
public final class FileUtils {

    private static final int KILOBYTE = 1024;

    /**
     * The method collects files with their size in kilobytes.
     *
     * @param rootFile the root folder
     * @return map key file and value its size
     */
    public static Map<File, Long> collectFilesWithKilobytesSize(File rootFile) {
        Map<File, Long> filesWithKilobytesSize = FileUtils.collectFilesWithKilobytesSize(rootFile, new HashMap<>());

        return filesWithKilobytesSize
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
    }

    /**
     * The method counts the amount of the given symbol in the file names.
     *
     * @param rootFile the root folder
     * @param symbol symbol for search
     * @return list of files with max amount of the given symbol ib the file name
     */
    public static List<File> findFilesWithMaxNumOfSymbol(File rootFile, String symbol) {
        final List<File> result = new ArrayList<>();

        Map<File, Integer> filesWithCount = FileUtils.countLetterInFileNames(rootFile, new HashMap<>(), symbol);
        int maxCount = (Collections.max(filesWithCount.values()));
        for (Map.Entry<File, Integer> filesEntry : filesWithCount.entrySet()) {

            if (filesEntry.getValue().equals(maxCount)) {
                result.add(filesEntry.getKey());
            }
        }

        return result;
    }

    /**
     * The method calculates the average file size within the specified folder.
     *
     * @param rootFile the root folder
     * @return map key directory and value average file size inside
     */
    public static Map<File, Long> collectDirectoriesWithAverageSizeOfFiles(File rootFile) {
        return FileUtils.collectDirectoriesWithAverageSizeOfFiles(rootFile, new HashMap<>());
    }

    private static Map<File, Long> collectDirectoriesWithAverageSizeOfFiles(File rootFile, Map<File, Long> result) {
        int countFiles = 0;
        long totalSize = 0;

        if (rootFile.isDirectory()) {

            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File file : files) {

                    if (file.isDirectory()) {
                        FileUtils.collectDirectoriesWithAverageSizeOfFiles(file, result);
                    } else {
                        countFiles++;
                        totalSize += file.length();
                    }
                }

                long averageSize = 0L;
                if (countFiles > 0) {
                    averageSize = (totalSize / KILOBYTE) / countFiles;
                }

                result.put(rootFile, averageSize);
            }
        }
        return result;
    }

    private static Map<File, Long> collectFilesWithKilobytesSize(File rootFile, Map<File, Long> filesWithKilobytesSize) {
        if (rootFile.isDirectory()) {

            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File file : files) {

                    if (file.isDirectory()) {
                        FileUtils.collectFilesWithKilobytesSize(file, filesWithKilobytesSize);
                    }

                    long length = file.length() / KILOBYTE;

                    filesWithKilobytesSize.put(file, length);
                }
            }
        }
        return filesWithKilobytesSize;
    }

    private static Map<File, Integer> countLetterInFileNames(File rootFile, Map<File, Integer> filesWithCountLetter, String letter) {
        if (rootFile.isDirectory()) {
            File[] files = rootFile.listFiles();
            if (files != null) {
                for (File file : files) {

                    if (file.isDirectory()) {
                        FileUtils.countLetterInFileNames(file, filesWithCountLetter, letter);
                    }

                    int count = StringUtils.countMatches(file.getName(), letter);

                    filesWithCountLetter.put(file, count);
                }
            }
        }

        return filesWithCountLetter;
    }

}

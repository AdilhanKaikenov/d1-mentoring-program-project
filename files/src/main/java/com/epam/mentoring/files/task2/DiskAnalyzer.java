package com.epam.mentoring.files.task2;

import com.epam.mentoring.files.task2.util.FileUtils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.MessageFormat;
import java.util.*;

/**
 * @author Kaikenov Adilhan
 **/
public class DiskAnalyzer {

    private static final int COMMAND_LINE_ARG_NUM = 2;
    private static final int TOP_RESULT_NUMBER = 5;

    private static final String DISC_C_PATH = "C:";
    private static final String COMMAND_RESULT_TXT_FILE_NAME = "commandResult.txt";

    public static void main(String[] args) {

        if (args.length >= COMMAND_LINE_ARG_NUM) {
            final String rootPath = args[0];
            final String commandNumber = args[1];

            if (!rootPath.toUpperCase().startsWith(DISC_C_PATH)) {
                throw new RuntimeException("Incorrect path: the program only works for the 'C:' disk of your working machine.");
            }

            switch (commandNumber) {
                case "1":
                    doCommandOne(rootPath);
                    break;
                case "2":
                    doCommandTwo(rootPath);
                    break;
                case "3":
                    doCommandThree(rootPath);
                    break;
                case "4":
                    doCommandFour(rootPath);
                    break;
                default:
                    System.out.println("Invalid command");
                    break;
            }

        } else if (args.length == 0) {
            System.out.println("No command line arguments found.");
        } else {
            System.out.println("One argument is missed.");
        }
    }

    /**
     * SubTask#1: Find the name of the file with the maximum number of letters ‘s’ in the name, display the path to it.
     */
    private static void doCommandOne(String rootPath) {
        final File rootFile = new File(rootPath);

        final String targetSymbol = "s";
        final List<File> targetFiles = FileUtils.findFilesWithMaxNumOfSymbol(rootFile, targetSymbol);

        if (targetFiles.isEmpty()) {
            System.out.println("There is no file with the given letter.");
            return;
        }

        DiskAnalyzer.writeResultOfFirstCommand(targetSymbol, targetFiles);
    }

    /**
     * SubTask#2: Top-5 files with the largest size.
     */
    private static void doCommandTwo(String rootPath) {
        final File rootFile = new File(rootPath);

        Map<File, Long> filesWithKilobytesSize = FileUtils.collectFilesWithKilobytesSize(rootFile);

        DiskAnalyzer.writeResultOfSecondCommand(filesWithKilobytesSize);
    }

    /**
     * SubTask#3: Average file size in the specified directory or any of its subdirectories.
     */
    private static void doCommandThree(String rootPath) {
        final File rootFile = new File(rootPath);

        Map<File, Long> directoriesWithAverageSizeOfFiles = FileUtils.collectDirectoriesWithAverageSizeOfFiles(rootFile);

        DiskAnalyzer.writeResultOfThirdCommand(directoriesWithAverageSizeOfFiles);
    }

    /**
     * SubTask#4: Number of files and folders, broken down by the first letters of the alphabet.
     */
    private static void doCommandFour(String rootPath) {
        final File rootFile = new File(rootPath);

        if (rootFile.isDirectory()) {

            File[] files = rootFile.listFiles();
            if (files != null) {

                final Map<String, Integer> directoriesMapCount = new HashMap<>();
                final Map<String, Integer> filesMapCount = new HashMap<>();

                DiskAnalyzer.countFiles(files, directoriesMapCount, filesMapCount);

                Set<String> letters = new HashSet<>(directoriesMapCount.keySet());
                letters.addAll(filesMapCount.keySet());

                DiskAnalyzer.writeResultOfFourthCommand(rootFile, directoriesMapCount, filesMapCount, letters);
            }
        }
    }

    private static void countFiles(File[] files, Map<String, Integer> directoriesLetterMapCount, Map<String, Integer> filesLetterMapCount) {
        int directoryCount = 0;
        int fileCount = 0;

        for (File file : files) {
            String firstNameLetter = file.getName().toUpperCase().substring(0, 1);

            if (file.isDirectory()) {
                directoriesLetterMapCount.put(firstNameLetter, ++directoryCount);
            } else {
                filesLetterMapCount.put(firstNameLetter, ++fileCount);
            }
        }
    }

    private static void writeResultOfFirstCommand(String targetSymbol, List<File> targetFiles) {
        try (final Writer writer = new FileWriter(COMMAND_RESULT_TXT_FILE_NAME)) {

            writer.write(MessageFormat.format(
                    "The result of the files with the maximum amount of {0} symbols in the file name:", targetSymbol));
            for (File targetFile : targetFiles) {
                writer.write(System.lineSeparator());
                writer.write(targetFile.getPath());
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeResultOfSecondCommand(Map<File, Long> filesWithKilobytesSize) {
        try (final Writer writer = new FileWriter(COMMAND_RESULT_TXT_FILE_NAME)) {
            writer.write("Top 5 files with the largest size:");

            Iterator<Map.Entry<File, Long>> filesEntryIterator = filesWithKilobytesSize.entrySet().iterator();
            int count = 0;
            while (filesEntryIterator.hasNext() && ++count <= TOP_RESULT_NUMBER) {
                Map.Entry<File, Long> filesEntry = filesEntryIterator.next();
                writer.write(System.lineSeparator());
                writer.write(MessageFormat.format("{0}){1} ---> {2} Kilobytes",
                        count,
                        filesEntry.getKey().getPath(),
                        filesEntry.getValue()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeResultOfThirdCommand(Map<File, Long> directoriesWithAverageSizeOfFiles) {
        try (final Writer writer = new FileWriter(COMMAND_RESULT_TXT_FILE_NAME)) {
            writer.write("Average file size in the specified directory or any of its subdirectories:");

            for (Map.Entry<File, Long> filesEntry : directoriesWithAverageSizeOfFiles.entrySet()) {
                writer.write(System.lineSeparator());
                writer.write(MessageFormat.format("Directory {0} with average file size {1} Kilobytes",
                        filesEntry.getKey().getPath(),
                        filesEntry.getValue()));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void writeResultOfFourthCommand(File rootFile, Map<String, Integer> directoriesLetterMapCount, Map<String, Integer> filesLetterMapCount, Set<String> letters) {
        try (final Writer writer = new FileWriter(COMMAND_RESULT_TXT_FILE_NAME)) {
            writer.write(MessageFormat.format("Number of files and folders in directory {0}:", rootFile.getPath()));

            for (String letter : letters) {
                final Integer filesCount = filesLetterMapCount.get(letter);
                final Integer directoriesCount = directoriesLetterMapCount.get(letter);

                writer.write(System.lineSeparator());
                writer.write(MessageFormat.format("The letter {0} - starts {1} files and {2} folders",
                        letter,
                        filesCount == null ? 0 : filesCount,
                        directoriesCount == null ? 0 : directoriesCount));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}

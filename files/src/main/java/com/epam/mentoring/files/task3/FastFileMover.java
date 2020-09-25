package com.epam.mentoring.files.task3;

import com.epam.mentoring.files.task3.exceptions.FileMoveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

/**
 * @author Kaikenov Adilhan
 **/
public final class FastFileMover {

    private static final Logger log = LoggerFactory.getLogger(FastFileMover.class);

    private static final int KILOBYTE = 1024;

    /**
     * Version uses simple filestreams
     */
    public static void moveUsingFileStream(final String sourcePath, final String targetPath) throws FileNotFoundException {
        final File sourceFile = new File(sourcePath);

        if (!sourceFile.exists()) {
            log.error("File {} does not exist.", sourceFile.getPath());
            throw new FileNotFoundException("Could not find the file specified: " + sourceFile.getPath());
        }

        try (InputStream inputStream = new FileInputStream(sourceFile);
             OutputStream outputStream = new FileOutputStream(targetPath)) {

            int length;
            while ((length = inputStream.read()) > 0) {
                outputStream.write(length);
            }

        log.info("File is moved successful!");
        } catch (IOException e) {
            throw new FileMoveException("An error occurred while moving the file.", e);
        }

        if (sourceFile.delete()) {
            log.info("Source file is deleted.");
        } else {
            log.error("Failed to delete source file. Perhaps, there is permission problems.");
        }
    }

    /**
     * Version uses FileStreams with a buffer of 100 KB
     */
    public static void moveUsingBufferedFileStream(final String sourcePath, final String targetPath) throws FileNotFoundException {
        final File sourceFile = new File(sourcePath);

        if (!sourceFile.exists()) {
            log.error("File {} does not exist.", sourceFile.getPath());
            throw new FileNotFoundException("Could not find the file specified: " + sourceFile.getPath());
        }

        try (InputStream inputStream = new FileInputStream(sourceFile);
             OutputStream outputStream = new FileOutputStream(targetPath)) {

            byte[] buffer = new byte[100 * KILOBYTE];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            log.info("File is moved successful!");
        } catch (IOException e) {
            throw new FileMoveException("An error occurred while moving the file.", e);
        }

        if (sourceFile.delete()) {
            log.info("Source file is deleted.");
        } else {
            log.error("Failed to delete source file. Perhaps, there is permission problems.");
        }
    }

    /**
     * Version uses FileChannel
     *
     * It is supposed to be faster than using Streams.
     */
    public static void moveFileUsingChannel(final String sourcePath, final String targetPath) throws FileNotFoundException {
        if (!Files.exists(Paths.get(sourcePath))) {
            log.error("File {} does not exist.", sourcePath);
            throw new FileNotFoundException("Could not find the file specified: " + sourcePath);
        }

        try (FileChannel sourceChannel = new FileInputStream(sourcePath).getChannel();
             FileChannel destChannel = new FileOutputStream(targetPath).getChannel()) {

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

            log.info("File is moved successful!");
        } catch (IOException e) {
            throw new FileMoveException("An error occurred while moving the file.", e);
        }

        try {
            Files.delete(Paths.get(sourcePath));
            log.info("Source file is deleted.");
        } catch (IOException e) {
            log.error("Failed to delete source file. Perhaps, there is permission problems.", e);
        }
    }

    /**
     * Version uses NIO 2 File API
     */
    public static void moveFileUsingNIO2FileAPI(final String sourcePath, final String targetPath) throws FileNotFoundException {
        if (!Files.exists(Paths.get(sourcePath))) {
            log.error("File {} does not exist.", sourcePath);
            throw new FileNotFoundException("Could not find the file specified: " + sourcePath);
        }

        try {
            Files.move(Paths.get(sourcePath), Paths.get(targetPath), REPLACE_EXISTING);
            log.info("File is moved successful!");
        } catch (IOException e) {
            throw new FileMoveException("An error occurred while moving the file.", e);
        }
    }
}

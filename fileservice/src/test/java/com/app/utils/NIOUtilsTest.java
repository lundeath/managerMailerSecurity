package com.app.utils;

import org.junit.Test;
import org.powermock.core.classloader.annotations.PrepareForTest;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@PrepareForTest(NIOUtils.class)
public class NIOUtilsTest {

    @Test
    public void getHashedCodeFromFile() throws IOException, OutOfMemoryError, InterruptedException {
        System.out.println(Thread.currentThread().getName()+" started...");
        String hashedCodeFromFile = NIOUtils.getHashedCodeFromFile();
        System.out.println("("+Thread.currentThread().getName()+"): Code from file: " + hashedCodeFromFile);
    }

    @Test
    public void multipleThreadsToGetCode() throws InterruptedException, OutOfMemoryError {
        ExecutorService executor = Executors.newFixedThreadPool(3);
        System.out.println("File size: " + (float)new File("C:\\numbers.txt").length()/1000000000 + " GB\n\n\n");
        Runnable thread1 = () -> {
            try {
                getHashedCodeFromFile();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        };

        for (int i = 0; i < 3; i++) {
            executor.execute(thread1);
        }
        executor.shutdown();
        while(!executor.isTerminated()){

        }
        System.out.println("Finished work!");


    }

}
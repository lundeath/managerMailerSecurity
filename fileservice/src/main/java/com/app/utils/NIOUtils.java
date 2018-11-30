package com.app.utils;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class NIOUtils {
    public static String getHashedCodeFromFile() throws IOException, InterruptedException {
        RandomAccessFile aFile = new RandomAccessFile("C:\\numbers.txt", "rw");
        FileChannel inChannel = aFile.getChannel();
        StringBuilder code = new StringBuilder();
        //create buffer with capacity of 1024 bytes
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int bytesRead = inChannel.read(buffer); //read into buffer.
        int random = (int) (Math.random() * (bytesRead));

        while (bytesRead != -1) {
            buffer.flip();  //make buffer ready for read
            while (buffer.hasRemaining()) {
                try {
                    code.append(buffer.get());// read 1 byte at a time
                } catch (OutOfMemoryError e) {
                    Thread.sleep(1000);
                    System.out.println("(" + Thread.currentThread().getName() + "): Managed to read: " + code.toString().length() + " numbers.");
                    buffer.clear();
                    aFile.close();
                    return  Base64Cifre.encode(code.toString().substring(random, random + 6));
                }
            }
            buffer.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buffer);
        }
        aFile.close();
        return Base64Cifre.encode(code.toString().substring(random, random + 6));
    }

}

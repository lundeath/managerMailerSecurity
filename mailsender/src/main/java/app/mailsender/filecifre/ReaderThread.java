package app.mailsender.filecifre;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class ReaderThread implements Runnable {

    private Vector<Character> queue = null;


    ReaderThread(Vector<Character> blockingQueue) {
        this.queue = blockingQueue;
    }

    @Override
    public void run() {
        File file = new File("C:\\numbers.txt");
        FileReader inputStream = null;

        int c;
        try {
            inputStream = new FileReader(file);
            while ((c = inputStream.read()) != -1) {
                queue.addElement((char)c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
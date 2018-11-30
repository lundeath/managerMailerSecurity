package app.mailsender.filecifre;

import java.io.*;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;

public class WriterThread implements Runnable {
    private Vector<Character> queue = null;

    WriterThread(Vector<Character> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("C:\\numbers.txt");
            bw = new BufferedWriter(fw);

            for (int i : queue
            ) {
                bw.write(i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }

}
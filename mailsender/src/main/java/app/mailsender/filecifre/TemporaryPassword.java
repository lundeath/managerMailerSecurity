package app.mailsender.filecifre;

import java.util.Random;
import java.util.Vector;

/**
 * @author yurii.ukrainets
 */
public final class TemporaryPassword {

    /**
     * @param accuracy
     *          This value describes how many digits will be in password
     * @return temporary password
     */
    public String createPassword(int accuracy) {
        Vector<Character> vector = new Vector<>(1024);
        ReaderThread readerThread = new ReaderThread(vector);
        readerThread.run();
        int randomIndex = 0;

        if(vector.size() >= accuracy) {
            int maxRange = vector.size() - accuracy;
            int minRange = 1;
            int range = maxRange - minRange + 1;
            randomIndex =(int) (Math.random() * range) + minRange;
        } else throw new IllegalThreadStateException("File is empty");

        String tempPassword = getNumbersFromQueue(vector, randomIndex, accuracy);

        WriterThread writerThread = new WriterThread(vector);
        writerThread.run();

        return tempPassword;

    }

    private String getNumbersFromQueue(Vector<Character> vector, int index, int accuracy) {
        StringBuilder s = new StringBuilder();
        final String alphabet = "0123456789";
        final int length = alphabet.length();

        Random r = new Random();

        for (int i = index; i < index + accuracy; i++) {
            s.append(vector.get(i));
            vector.set(i, alphabet.charAt(r.nextInt(length)));
        }

        return s.toString();
    }

}

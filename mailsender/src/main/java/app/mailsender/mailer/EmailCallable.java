package app.mailsender.mailer;

import app.mailsender.domain.User;
import app.mailsender.filecifre.TemporaryPassword;
import app.mailsender.utils.MailText;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;

public class EmailCallable implements Callable<User> {
    private BlockingQueue<EmailData> blockingQueue;
    private JavaMailSender sender;
    private EmailData currentEmailData;

    public EmailCallable() {

    }

    public EmailCallable(JavaMailSender sender) throws InterruptedException {
        this.sender = sender;
    }

    public void setBlockingQueue(BlockingQueue<EmailData> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    public BlockingQueue<EmailData> getBlockingQueue() {
        return blockingQueue;
    }

    public EmailData getCurrentEmailData() {
        return currentEmailData;
    }

    @Override
    public User call() throws InterruptedException {
        currentEmailData = blockingQueue.take();
        String password = null;
        try {
            password = new TemporaryPassword().createPassword(10);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("Something went wrong with password generation");
            Thread.currentThread().interrupt();
        }
        try {
            if(currentEmailData.getSubject().equals("FORGOT")) {
                sendSimpleMessage(currentEmailData.getTo(),
                        MailText.SUBJECTS_FORGOT,
                        MailText.FORGOT(password));
                Thread.currentThread().interrupt();
            } else throw new IllegalArgumentException("Cannot recognize the subject!");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return new User(currentEmailData.getTo(),password,true);
    }

    private void sendSimpleMessage(String to, String subject, String text) throws InterruptedException {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        try {
            sender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

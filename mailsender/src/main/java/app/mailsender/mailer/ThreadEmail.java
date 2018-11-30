package app.mailsender.mailer;

import app.mailsender.utils.MailText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.concurrent.BlockingQueue;

public class ThreadEmail implements Runnable{
    private BlockingQueue<EmailData> queue;

    private JavaMailSender sender;

    public ThreadEmail() {
    }

    public ThreadEmail(BlockingQueue<EmailData> queue) {
        this.queue = queue;
    }

    public BlockingQueue<EmailData> getQueue() {
        return queue;
    }

    public void setQueue(BlockingQueue<EmailData> queue) {
        this.queue = queue;
    }

    public JavaMailSender getSender() {
        return sender;
    }

    public void setSender(JavaMailSender sender) {
        this.sender = sender;
    }

    @Override
    public void run() {
        EmailData take = null;
        try {
            take = queue.take();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assert take != null;
        if("FORGOT".equals(take.getSubject())) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(take.getTo());
            message.setSubject(MailText.SUBJECTS_FORGOT);
            message.setText(MailText.TEST);
            try {
                    Thread.sleep(1000);
                    sender.send(message);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

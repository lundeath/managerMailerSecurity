package app.mailsender.service;

import app.mailsender.domain.User;
import app.mailsender.mailer.EmailCallable;
import app.mailsender.mailer.EmailData;
import app.mailsender.mailer.ThreadEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class ConcurrentEmailService {
    private final JavaMailSender emailSender;
    private BlockingQueue<EmailData> blockingQueue = new ArrayBlockingQueue<>(1024);
    private ExecutorService executor = Executors.newFixedThreadPool(10);
//    private BlockingQueue<EmailCallable> threads = new ArrayBlockingQueue<>(10);
    private ThreadEmail thread = null;


    @Autowired
    public ConcurrentEmailService(JavaMailSender emailSender) throws InterruptedException {
        this.emailSender = emailSender;
        thread = (ThreadEmail)instantiateThreads();
    }

    /*public User execute(EmailData emailData) throws InterruptedException, ExecutionException {
        blockingQueue.put(emailData);
        User user = null;
        ThreadPoolExecutor e = (ThreadPoolExecutor) executor;
        if (blockingQueue.size() > 0) {
//            delegateQueueToThreads(threads);
//            user = executor.invokeAny(threads);
            System.out.println("Number of active threads from the given thread: " + Thread.activeCount());
        }
        return user;
    }*/

    public void executeThread(EmailData emailData) throws InterruptedException {

            blockingQueue.put(emailData);
            thread.setSender(emailSender);
            thread.setQueue(blockingQueue);
            thread.run();

    }

    private Runnable instantiateThreads() throws InterruptedException {
        /*for (int i = 0; i < 10; i++) {
            threads.add(new EmailCallable(emailSender));
        }*/

        return new ThreadEmail();
    }

    private void delegateQueueToThreads(BlockingQueue<EmailCallable> threads){
        for (EmailCallable thread : threads
                ) {
            thread.setBlockingQueue(blockingQueue);
        }
    }

}

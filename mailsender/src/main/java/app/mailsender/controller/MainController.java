package app.mailsender.controller;


import app.mailsender.domain.User;
import app.mailsender.filecifre.TemporaryPassword;
import app.mailsender.mailer.EmailData;
import app.mailsender.repositories.UserRepository;
import app.mailsender.service.ConcurrentEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
public class MainController {

    private final UserRepository userRepository;

    private final ConcurrentEmailService concurrentEmailService;

    @Autowired
    public MainController(UserRepository userRepository, ConcurrentEmailService concurrentEmailService) {
        this.userRepository = userRepository;
        this.concurrentEmailService = concurrentEmailService;
    }

    @GetMapping(name = "/")
    public User sendMail(@RequestParam String email,
                         @RequestParam String subject) throws InterruptedException, ExecutionException {
        concurrentEmailService.executeThread(new EmailData(email, subject));
//        userRepository.save(user);

        return new User("test","test",true);
    }


}

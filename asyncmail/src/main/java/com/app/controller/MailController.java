package com.app.controller;

import com.app.service.NotificationService;
import com.app.utils.MailPattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.Email;

@Controller
public class MailController {

    private Logger logger = LoggerFactory.getLogger(MailController.class);

    private NotificationService notificationService;

    @Autowired
    public MailController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @GetMapping("/forgot")
    public ResponseEntity forgot(@NonNull @Email @RequestParam String email){
        try {
            notificationService.sendNotification(email, MailPattern.FORGOT_PWD);
        }catch( Exception e ){
            logger.error("Error Sending Email: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/welcome")
    public ResponseEntity welcome(@NonNull @Email @RequestParam String email){
        try {
            notificationService.sendNotification(email, MailPattern.WELCOME);
        }catch( Exception e ){
            logger.error("Error Sending Email: " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
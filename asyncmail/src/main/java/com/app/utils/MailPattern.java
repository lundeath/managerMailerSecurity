package com.app.utils;

import com.app.domain.Mail;

public class MailPattern {
    public static Mail WELCOME;
    public static Mail FORGOT_PWD;
    public static Mail CHANGE_PWD;

    static {
        WELCOME = new Mail("emailservice@gmail.com",
                "Welcome!",
                "We're glad to meet you on our service.");
        FORGOT_PWD = new Mail("emailservice@gmail.com",
                "Forgot password",
                "This is your password");
        CHANGE_PWD = new Mail("emailservice@gmail.com",
                "Changing password",
                "Click here to change your password");
    }
}

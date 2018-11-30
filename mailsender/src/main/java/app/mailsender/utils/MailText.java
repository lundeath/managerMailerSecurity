package app.mailsender.utils;

public class MailText {
    public static String SUBJECTS_FORGOT = "Temporary password";

    public static String FORGOT(String temp_pwd){
        return ".This is your temporary password: \" + temp_pwd +\". Don't share it to anyone";
    }

    public static String TEST = "This is a test message";

}

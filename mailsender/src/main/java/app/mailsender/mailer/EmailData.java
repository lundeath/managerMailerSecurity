package app.mailsender.mailer;

public class EmailData {
    private String to;
    private String subject;

    public EmailData() {
    }


    public EmailData(String to, String subject) {
        this.to = to;
        this.subject = subject;
    }


    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }


}

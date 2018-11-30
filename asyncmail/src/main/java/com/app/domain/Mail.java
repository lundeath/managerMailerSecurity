package com.app.domain;

public class Mail {
    private String from;
    private String subject;
    private String text;

    public Mail() {
    }

    public Mail(String from, String subject, String text) {
        this.from = from;
        this.subject = subject;
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

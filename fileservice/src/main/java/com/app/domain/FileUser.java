package com.app.domain;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="fileUser")
public class FileUser {
    @Id
    private String userEmail;
    private Code code;

    public FileUser() {
    }

    public FileUser(String userEmail, Code code) {
        this.userEmail = userEmail;
        this.code = code;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Code getCode() {
        return code;
    }

    public void setCode(Code code) {
        this.code = code;
    }
}

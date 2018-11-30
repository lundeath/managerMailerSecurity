package com.app.domain;

import org.mongodb.morphia.annotations.PrePersist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
@Document(collection="code")
public class Code {
    @Id
    private String code;
    @Field
    private Date creationDate;
    private boolean expired;

    @PrePersist
    protected void onCreate() {
        creationDate = new Date();
    }

    public Code() {
    }

    public Code(String code) {
        this.code = code;
        this.expired = false;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }
}

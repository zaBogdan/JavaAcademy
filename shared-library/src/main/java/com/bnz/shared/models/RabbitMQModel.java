package com.bnz.shared.models;

import java.util.Date;

public class RabbitMQModel<T> {
    private String name;
    private String sender;
    private Date sentAt;
    private boolean isDev;
    private T data;

    public RabbitMQModel() {
    }

    public RabbitMQModel(String name, String sender, T data) {
        this.name = name;
        this.sender = sender;
        this.sentAt = new Date();
        this.isDev = true;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public Date getSentAt() {
        return sentAt;
    }

    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }

    public boolean isDev() {
        return isDev;
    }

    public void setDev(boolean dev) {
        isDev = dev;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "RabbitMQModel{" +
                "name='" + name + '\'' +
                ", sender='" + sender + '\'' +
                ", sentAt=" + sentAt +
                ", isDev=" + isDev +
                ", data=" + data +
                '}';
    }
}

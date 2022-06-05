package com.bnz.services.quizzes.models;

import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

public class Attenders {
    @DBRef
    private String userId;
    private int score;
    private Date triedAt;
    private int status; // 1 - started, 2 - in progress, 3 - finished, 4 - not finished in time

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Date getTriedAt() {
        return triedAt;
    }

    public void setTriedAt(Date triedAt) {
        this.triedAt = triedAt;
    }

    @Override
    public String toString() {
        return "Attenders{" +
                "userId='" + userId + '\'' +
                ", score=" + score +
                ", triedAt=" + triedAt +
                '}';
    }
}

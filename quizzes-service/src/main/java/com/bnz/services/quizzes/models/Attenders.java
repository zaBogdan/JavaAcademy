package com.bnz.services.quizzes.models;

import java.util.Date;

public class Attenders {
    private String userId;
    private int score;
    private Date startedAt;
    private Date finishedAt;
    private int status; // 1 - started, 2 - in progress, 3 - finished, 4 - not finished in time

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Date startedAt) {
        this.startedAt = startedAt;
    }

    public Date getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Date finishedAt) {
        this.finishedAt = finishedAt;
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

    @Override
    public String toString() {
        return "Attenders{" +
                "userId='" + userId + '\'' +
                ", score=" + score +
                ", startedAt=" + startedAt +
                ", finishedAt=" + finishedAt +
                ", status=" + status +
                '}';
    }
}

package com.bnz.services.quizzes.models;

public class DetailedScore {
    private Integer score;
    private String message;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public DetailedScore(Integer score, String message) {
        this.score = score;
        this.message = message;
    }

    @Override
    public String toString() {
        return "DetailedScore{" +
                "score=" + score +
                ", message='" + message + '\'' +
                '}';
    }
}

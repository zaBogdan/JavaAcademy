package com.bnz.shared.models;

public class QuestionResponse {
    private String questionUID;
    private String response;
    private String quizId;
    private int maximumScore;
    private int score;
    private String language;

    public String getQuizId() {
        return quizId;
    }

    public void setQuizId(String quizId) {
        this.quizId = quizId;
    }

    public int getMaximumScore() {
        return maximumScore;
    }

    public void setMaximumScore(int maximumScore) {
        this.maximumScore = maximumScore;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getQuestionUID() {
        return questionUID;
    }

    public void setQuestionUID(String questionUID) {
        this.questionUID = questionUID;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return "QuestionResponse{" +
                "questionUID='" + questionUID + '\'' +
                ", response='" + response + '\'' +
                ", quizId='" + quizId + '\'' +
                ", maximumScore=" + maximumScore +
                ", score=" + score +
                ", language='" + language + '\'' +
                '}';
    }
}

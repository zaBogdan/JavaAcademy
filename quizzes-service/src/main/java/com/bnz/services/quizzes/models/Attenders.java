package com.bnz.services.quizzes.models;

import com.bnz.shared.models.QuestionResponse;
import com.bnz.shared.models.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

public class Attenders {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String userId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Field(write=Field.Write.NON_NULL)
    private User user = null;
    private int score;
    private Date startedAt;
    private Date finishedAt;
    private int status; // 1 - started, 2 - in progress, 3 - finished, 4 - not finished in time
    private List<QuestionResponse> questionInformation;

    public List<QuestionResponse> getQuestionInformation() {
        return questionInformation;
    }

    public void setQuestionInformation(List<QuestionResponse> questionInformation) {
        this.questionInformation = questionInformation;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

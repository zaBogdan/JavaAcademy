package com.bnz.services.quizzes.models;

import com.mongodb.lang.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Document(collection = "quizzes")
public class Quiz {
    @Id
    private String id;
    @NonNull
    private String name;
    @NonNull
    private String description;
    @NonNull
    private Date createdAt;
    @NonNull
    private Date updatedAt = new Date();
    private boolean acceptsAnswers = true;
    private boolean automaticallyClose = false;
    private Date closeAt;
    @NonNull
    private Object quizTime;
    @NonNull
    private List<Question> questions;
    private List<Attenders> attenders;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @NonNull
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(@NonNull Date createdAt) {
        this.createdAt = createdAt;
    }

    @NonNull
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(@NonNull Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean isAcceptsAnswers() {
        return acceptsAnswers;
    }

    public void setAcceptsAnswers(boolean acceptsAnswers) {
        this.acceptsAnswers = acceptsAnswers;
    }

    public boolean isAutomaticallyClose() {
        return automaticallyClose;
    }

    public void setAutomaticallyClose(boolean automaticallyClose) {
        this.automaticallyClose = automaticallyClose;
    }

    public Date getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(Date closeAt) {
        this.closeAt = closeAt;
    }

    @NonNull
    public Object getQuizTime() {
        return quizTime;
    }

    public void setQuizTime(@NonNull Object quizTime) {
        this.quizTime = quizTime;
    }

    public void addQuestion(Question q) {
        if(questions == null)
            questions = new ArrayList<>();
        questions.add(q);
    }
    @NonNull
    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(@NonNull List<Question> questions) {
        this.questions = questions;
    }

    public void attendersQuestion(Attenders a) {
        if(attenders == null)
            attenders = new ArrayList<>();
        attenders.add(a);
    }

    public List<Attenders> getAttenders() {
        return attenders;
    }

    public void setAttenders(List<Attenders> attenders) {
        this.attenders = attenders;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "id='" + id + '\'' +
                ", quizName='" + name + '\'' +
                ", description='" + description + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", acceptsAnswers=" + acceptsAnswers +
                ", automaticallyClose=" + automaticallyClose +
                ", closeAt=" + closeAt +
                ", quizTime=" + quizTime +
                ", questions=" + questions +
                ", attenders=" + attenders +
                '}';
    }
}
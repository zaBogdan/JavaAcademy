package com.example.application.data.entity;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "quizzes")
public class Quiz extends AbstractEntity {

    private String programmingLanguage;

    private String level;

    private int duration;

    public String getProgrammingLanguage() {
        return programmingLanguage;
    }

    public void setProgrammingLanguage(String programmingLanguage) {
        this.programmingLanguage = programmingLanguage;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getScore() {
        return 0;
    }
}
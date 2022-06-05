package com.bnz.services.quizzes.models;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Question {
    private String name;
    private int type; // 1 - code // TODO: 1 - choice, 2 - multiple choice
    private String language;
    private int score = 0;
    private String uid = UUID.randomUUID().toString().replace("-", "");

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Question{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", language='" + language + '\'' +
                ", score=" + score +
                ", uid='" + uid + '\'' +
                '}';
    }
}

package com.example.demo.model;

import java.util.List;

public class QuestionType {
    private String type;
    private String question;
    private List<String> options;
    private String correctForm;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrectForm() {
        return correctForm;
    }

    public void setCorrectForm(String correctForm) {
        this.correctForm = correctForm;
    }
}



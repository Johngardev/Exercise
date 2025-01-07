package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Exam {
    private String id;
    public String examName;
    public String instructions;
    public List<QuestionType> questions;

    public Exam(String id, String examName, String instructions, List<QuestionType> questions) {
        this.id = id;
        this.examName = examName;
        this.instructions = instructions;
        this.questions = questions;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public List<QuestionType> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionType> questions) {
        this.questions = questions;
    }
}
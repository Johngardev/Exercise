package com.example.demo.model;

import java.util.ArrayList;

public class Exam {
    private String id;
    public String examName;
    public String instructions;
    public ArrayList questions;

    public Exam(String id, String examName, String instructions, ArrayList questions) {
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

    public ArrayList getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList questions) {
        this.questions = questions;
    }
}
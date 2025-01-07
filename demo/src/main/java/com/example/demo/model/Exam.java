package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "exams")
public class Exam {
    @Id
    private String id;

    @Indexed(unique = true)
    @JsonProperty("examName")
    @NonNull
    public String examName;

    @JsonProperty("instructions")
    @NonNull
    public String instructions;

    @JsonProperty("questions")
    @NonNull
    public List<QuestionType> questions;

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
package com.webQuiz.demo.models;

import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank
    private String question;
    @Size(min = 2)
    @NotNull
    private String[] options;
    private int answer;

    public Quiz(int id, String question, String[] options, int answer) {
        this.id = id;
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public Quiz() {
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String[] getOptions() {
        return options;
    }

}

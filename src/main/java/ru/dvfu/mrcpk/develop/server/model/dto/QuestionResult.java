package ru.dvfu.mrcpk.develop.server.model.dto;

import lombok.Data;
import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.Question;

import java.util.Date;
import java.util.List;

@Data
public class QuestionResult {

    private int id;

    private Question question;

    private Date timestart;

    private Date timestop;

    private List<Integer> options;

    private int score;

    private int optionsTrue;

    private int ansTrue;

    private int ansFalse;

    private float mark;

    private boolean answered;

    public QuestionResult() {}

    public QuestionResult(Question question) {
        this.question = question;
        this.timestart = new Date();
    }

    public QuestionResult(Question question, List<Integer> options) {
        this.question = question;
        this.options = options;
        this.timestart = new Date();
    }

    public QuestionResult(Question question, float mark) {
        this.question = question;
        this.mark = mark;
    }

    public QuestionResult(int id, Question question, float mark) {
        this.id = id;
        this.question = question;
        this.mark = mark;
    }
}

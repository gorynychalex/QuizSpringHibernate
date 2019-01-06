package ru.dvfu.mrcpk.develop.server.model.dto;

import lombok.Data;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.statistic.StQuiz;


import java.util.Date;
import java.util.List;

@Data
public class QuizResultDTO {

    private String sessionid;

    private UserAuth usr;

    private String userip;

    private Quiz quiz;

    private StQuiz.Mode mode;

    private float score;

    private int mark;

    private Date timeStart;

    private Date timeStop;

    private List<QuestionResult> questionsresult;

    // Attempted Questions
    private int questionsAttempted;

    // Unattempted Questions
    private int questionsUnAttempted;

    // Total Correct Options
    private int optionsTrue;

    // Total Correct Answer
    private int ansTrue;

    // Total Wrong Answer
    private int ansFalse;

    public QuizResultDTO(){}

    public QuizResultDTO(String sessionid, Quiz quiz, StQuiz.Mode mode, Date timeStart) {
        this.sessionid = sessionid;
        this.quiz = quiz;
        this.mode = mode;
        this.timeStart = timeStart;
    }

    public QuizResultDTO(List<QuestionResult> questionsresult) {
        this.questionsresult = questionsresult;
    }

}

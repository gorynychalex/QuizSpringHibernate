package ru.dvfu.mrcpk.develop.server.model.statistic;

import lombok.Data;
import lombok.NonNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.UserAuth;
import ru.dvfu.mrcpk.develop.server.model.dto.QuizResultDTO;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "stquiz")
public class StQuiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String session;

    @OneToOne
    @JoinColumn
    private UserAuth usr;

    private String userip;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "quizid",referencedColumnName = "id")
    private Quiz quiz;

    private Date timestart;

    private Date timestop;

    private Mode mode;

    private float score;

    private int mark;

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

    public StQuiz() {}

    public StQuiz(String session, Quiz quiz, UserAuth usr) {
        this.session = session;
        this.quiz = quiz;
        this.usr = usr;
        this.mode = Mode.EXAM;
        this.timestart = new Date();
    }

    public StQuiz(String session, Quiz quiz, UserAuth usr, Mode mode) {
        this.session = session;
        this.quiz = quiz;
        this.usr = usr;
        this.mode = mode;
        this.timestart = new Date();
    }

    public StQuiz(@NonNull QuizResultDTO quizResultDTO){
        this.session = quizResultDTO.getSessionid();
        this.quiz = quizResultDTO.getQuiz();
        this.usr = quizResultDTO.getUsr();
        this.userip = quizResultDTO.getUserip();
        this.mode = quizResultDTO.getMode();
        this.mark = quizResultDTO.getMark();
        this.timestart = quizResultDTO.getTimeStart();
        this.timestop = quizResultDTO.getTimeStop();
        this.score = quizResultDTO.getScore();
        this.questionsAttempted = quizResultDTO.getQuestionsAttempted();
        this.questionsUnAttempted = quizResultDTO.getQuestionsUnAttempted();
        this.optionsTrue = quizResultDTO.getOptionsTrue();
        this.ansTrue = quizResultDTO.getAnsTrue();
        this.ansFalse = quizResultDTO.getAnsFalse();
    }

    public enum Mode {
        TEST,
        EXAM
    }
}

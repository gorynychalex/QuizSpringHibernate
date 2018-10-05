package ru.dvfu.mrcpk.develop.server.model.statistic;

import lombok.Data;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "stquiz")
public class StQuiz {

    @Id
    private String session;

    private Date timestart;

    private Date timestop;

    private float mark;

    private String usr;

    @OneToOne
    @JoinColumn(name = "quizid",referencedColumnName = "id")
    private Quiz quiz;

    public StQuiz() {}

    public StQuiz(String session, Quiz quiz, String user) {
        this.session = session;
        this.quiz = quiz;
        this.usr = user;
        this.timestart = new Date();
    }
}

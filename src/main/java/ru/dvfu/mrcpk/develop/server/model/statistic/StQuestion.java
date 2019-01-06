package ru.dvfu.mrcpk.develop.server.model.statistic;

import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import ru.dvfu.mrcpk.develop.server.model.Question;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class StQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToOne
    @JoinColumn(name = "questionid",referencedColumnName = "id")
    private Question question;

    private Date timestart;

    private Date timestop;

    @OneToMany
    @JoinColumn(name = "squestionid")
    private List<StOption> stOptionList;

//    @ManyToOne
//    @JoinColumn(name = "stquiz", referencedColumnName = "session")
//    private StQuiz stQuiz;

    private int score;

    private int optionsTrue;

    private int ansTrue;

    private int ansFalse;

    private float mark;

    private boolean answered;

    public StQuestion(){}

    public StQuestion(Question question) {
        this.question = question;
        this.timestart = new Date();
    }
}

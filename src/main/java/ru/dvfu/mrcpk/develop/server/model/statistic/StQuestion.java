package ru.dvfu.mrcpk.develop.server.model.statistic;

import lombok.Data;
import ru.dvfu.mrcpk.develop.server.model.Question;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class StQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float mark;

    private Date timestart;

    private Date timestop;

    @OneToMany
    @JoinColumn(name = "squestionid")
    private List<StOption> stOptionList;

    @OneToOne
    @JoinColumn(name = "questionid",referencedColumnName = "id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "stquiz", referencedColumnName = "session")
    private StQuiz stQuiz;

    public StQuestion(Question question) {
        this.question = question;
        this.timestart = new Date();
    }
}

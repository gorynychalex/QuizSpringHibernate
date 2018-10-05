package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by gorynych on 20.06.17.
 */

public class StatisticQuiz implements StatisticQuizIf{
    /**
     * No need to generate Id, because sessionId is Id!
     */
    @Id
    @Column(name = "sessionid")
    private String sessionId;

    //    @Type(type = "date")
    private Date timestart;

//    private Date timestop;

    private float mark;

    @OneToOne
    @JoinColumn(name = "quizid",referencedColumnName = "id")
    private QuizInterface quiz;

//    @Column(name = "quizid")
//    private int quizId;

//    @Column(name = "userid")
//    private int userId;

    @OneToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private UserInterface user;

    @OneToMany
    @JoinColumn(name = "sessionid", referencedColumnName = "sessionid")
    private List<StatisticQuestionsInterface> statisticQuestionsList;

    public StatisticQuiz() {
    }

    public StatisticQuiz(QuizInterface quiz, UserInterface user, List<StatisticQuestionsInterface> statisticQuestionsList) {
        this.quiz = quiz;
        this.user = user;
        this.statisticQuestionsList = statisticQuestionsList;
        this.timestart = new Date();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestart", nullable = false)
    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    @Override
    public QuizInterface getQuiz() {
        return quiz;
    }

    @Override
    public void setQuiz(QuizInterface quiz) {
        this.quiz = quiz;
    }

    @Override
    public UserInterface getUser() {
        return user;
    }

    @Override
    public void setUser(UserInterface user) {
        this.user = user;
    }

    @Override
    public List<StatisticQuestionsInterface> getStatisticQuestionsList() {
        return statisticQuestionsList;
    }

    @Override
    public void setStatisticQuestionsList(List<StatisticQuestionsInterface> statisticQuestionsList) {
        this.statisticQuestionsList = statisticQuestionsList;
    }
}

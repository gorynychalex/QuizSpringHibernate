package ru.dvfu.mrcpk.develop.server.model.statistic;

import org.hibernate.annotations.Type;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.QuizInterface;
import ru.dvfu.mrcpk.develop.server.model.User;
import ru.dvfu.mrcpk.develop.server.model.UserInterface;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by gorynych on 10.04.17.
 */
@Entity
@Table(name = "StatisticUserQuizSessions")
public class StatisticUserQuizSessions implements StatisticUserQuizSessionsInterface {

    /**
     * No need to generate Id, because sessionId is Id!
     */
    @Id
    @Column(name = "sessionid")
    private int sessionId;

//    @Type(type = "date")
    private Date timestart;

//    private Date timestop;

    private float mark;

    @OneToOne
    @JoinColumn(name = "quizid",referencedColumnName = "id")
    private Quiz quiz;

//    @Column(name = "quizid")
//    private int quizId;

//    @Column(name = "userid")
//    private int userId;

    @OneToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private User user;

    @OneToMany
    @JoinColumn(name = "sessionid", referencedColumnName = "sessionid")
    private List<StatisticQuestions> statisticQuestionsList;

    public StatisticUserQuizSessions(){    }

    public StatisticUserQuizSessions(int sessionId, User user, Quiz quiz) {
        this.sessionId = sessionId;
//        this.userId = userId;
        this.user = user;
//        this.quizId = quizId;
        this.quiz = quiz;
        this.timestart=new Date();
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

//
//    public int getQuizId() {
//        return quizId;
//    }
//
//    public void setQuizId(int quizId) {
//        this.quizId = quizId;
//    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

//    public int getUserId() {
//        return userId;
//    }
//
//    public void setUserId(int userId) {
//        this.userId = userId;
//    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestart", nullable = false)
    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

//    public Date getTimestop() {
//        return timestop;
//    }
//
//    public void setTimestop(Date timestop) {
//        this.timestop = timestop;
//    }



    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<StatisticQuestions> getStatisticQuestionsList() {
        return statisticQuestionsList;
    }

    public void setStatisticQuestionsList(List<StatisticQuestions> statisticQuestionsList) {
        this.statisticQuestionsList = statisticQuestionsList;
    }
}

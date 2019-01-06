package ru.dvfu.mrcpk.develop.server.model.statistic;

import org.hibernate.annotations.Type;
import ru.dvfu.mrcpk.develop.server.model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gorynych on 10.04.17.
 */

public class StatisticUserQuizSessions implements StatisticUserQuizSessionsInterface {

    /**
     * No need to generate Id, sessionId is Id!
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

    public StatisticUserQuizSessions(String sessionId, User user, Quiz quiz) {
        this.sessionId = sessionId;
//        this.userId = userId;
        this.user = user;
//        this.quizId = quizId;
        this.quiz = quiz;
        this.timestart=new Date();
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
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

    @Override
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

    /**
     * Method to estimate mark
     * sumAnsTrue / sumOptionsTrue / (sunAnsFalse + 1)
     * where
     * sumAnsTrue - true user answers
     * sumAnsFalse - false user answers
     * sumOptionsTrue - true all options in questions
     *
     * @return
     */

    /*
    public List<Float> getResultMark(){

        // Create List of Marks of every Questions
        List<Float> marks = new ArrayList<Float>();

        for(StatisticQuestions squestion : statisticQuestionsList){

            if(squestion.getQuestion().getOptions().size() > 0) {
                //mark = КВП/ОКП/(КВН + 1)
                int sumOptionsTrue = 0, sumAnsTrue = 0, sumAnsFalse = 0;

                for (StatisticOptions soption : squestion.getStatisticOptionsList()) {
                    if (soption.getOption() != null && soption.getOption().isCorrect())
                        { sumOptionsTrue++; }

                    if (soption.getOption() != null && soption.getOption().isCorrect() && soption.getId() == soption.getOption().getId())
                        { sumAnsTrue++; }

                    if (soption.getOption() != null && !soption.getOption().isCorrect() && soption.getId() == soption.getOption().getId())
                        { sumAnsFalse++; }
                }
//                logger.info("sumAnsTrue = " + sumAnsTrue + "; sumOptTrue = " + sumOptionsTrue + "; sumAnsFalse = " + sunAnsFalse);
                float mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sumAnsFalse + 1);
//                logger.info("mark = " + mark);
                marks.add(mark);

            } else { marks.add((float) 0);}
        }


//        // Estimate mark for every questions
//        for(Question question: quiz.getQuestions()){
//
//            if(question.getOptions().size() > 0) {
//
//                //mark = КВП/ОКП/(КВН + 1)
//                int sumOptionsTrue = 0, sumAnsTrue = 0, sumAnsFalse = 0;
//
//                for (Option option : question.getOptions()) {
//
//                    if (option.isCorrect()) sumOptionsTrue++;
//
//                    for (UserAnswerOptions userAnswerOptions : userAnswerOptionss) {
//                        //                logger.info("i = " + option.getId() + "; userans = " + userans);
////                        if (option.isCorrect() & option.getId().equals(userAnswerOptions.getOptionid())) {
//                        if (option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
//                            sumAnsTrue++;
//                        }
//                        if (!option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
//                            sumAnsFalse++;
//                        }
//
//                    }
//                }
////                logger.info("sumAnsTrue = " + sumAnsTrue + "; sumOptTrue = " + sumOptionsTrue + "; sumAnsFalse = " + sunAnsFalse);
//                float mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sumAnsFalse + 1);
////                logger.info("mark = " + mark);
//                marks.add(mark);
//            } else { marks.add((float) 0);}
//        }

        mark = (float) marks.stream().mapToDouble(x -> x.floatValue()).sum();

        return marks;
    }
    */
}

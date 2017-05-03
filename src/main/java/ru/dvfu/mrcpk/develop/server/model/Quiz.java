package ru.dvfu.mrcpk.develop.server.model;

/**
 * Top Class QUIZ
 */

import com.sun.org.glassfish.external.statistics.Statistic;
import org.hibernate.annotations.Type;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessions;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticUserQuizSessionsInterface;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Quizs")
@XmlRootElement
public class Quiz implements QuizInterface {

    /**
     * Quiz id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Type(type = "org.hibernate.type.IntegerType")
    private int id;

    /**
     * Quiz name
     */
    private String name;

    /**
     * URL for Quiz picture
     */

    private String picture;

    /**
     * Quiz question numbers
     */

    private int qnums;

    /**
     * Connect One Quiz to Many Questions
     */
    @OneToMany
    @JoinColumn(name = "quizid")
    private List<Question> questions;
//
//    @OneToMany
//    @JoinColumn(name = "quizid", referencedColumnName = "id")
//    private List<StatisticUserQuizSessions> userQuizSessions;


    /**
     * Constructor dummy
     */
    public Quiz(){}

    /**
     * Constructor
     * @param name
     */
    public Quiz(String name){
        this.name = name;
    }

    /**
     * Constructor for Quiz with 2 params:
     * @param name
     * @param questions
     */
    public Quiz(String name, List<Question> questions) {
        this.name = name;
        this.questions = questions;
    }

    public Number getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getQnums() {
        return qnums;
    }

    public void setQnums(int qnums) {
        this.qnums = qnums;
    }

//    public List<StatisticUserQuizSessions> getUserQuizSessions() {
//        return userQuizSessions;
//    }
//
//    public void setUserQuizSessions(List<StatisticUserQuizSessions> userQuizSessions) {
//        this.userQuizSessions = userQuizSessions;
//    }
//
//    public void addUserQuizSession(int sessionId, User user){
//        userQuizSessions.add(new StatisticUserQuizSessions(sessionId,user,this));
//    }

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
//    public List<Float> getResult(int sessionId, List<UserAnswerOptions> userAnswerOptionss ){
//
//
//        // Create List of Marks of every Questions
//        List<Float> marks = new ArrayList<Float>();
//
//        //Choose statistic by sessionId
//        StatisticUserQuizSessions statisticUserQuiz =
//                userQuizSessions.stream().
//                        filter(x->x.getQuiz().getId().equals(sessionId)).
//                        findFirst().orElse(null);
//
//        // Estimate mark for every questions
//        for(Question question: questions){
//
//            if(question.getOptions().size() > 0) {
//                //mark = КВП/ОКП/(КВН + 1)
//                int sumOptionsTrue = 0, sumAnsTrue = 0, sunAnsFalse = 0;
//
//                for (Option option : question.getOptions()) {
//
//                    if (option.isCorrect()) sumOptionsTrue++;
//
//
//
//                    for (UserAnswerOptions userAnswerOptions : userAnswerOptionss) {
//                        //                logger.info("i = " + option.getId() + "; userans = " + userans);
////                        if (option.isCorrect() & option.getId().equals(userAnswerOptions.getOptionid())) {
//                        if (option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
//                            sumAnsTrue++;
//                        }
//                        if (!option.isCorrect() & option.getId() == userAnswerOptions.getOptionid()) {
//                            sunAnsFalse++;
//                        }
//
//                    }
//                }
////                logger.info("sumAnsTrue = " + sumAnsTrue + "; sumOptTrue = " + sumOptionsTrue + "; sumAnsFalse = " + sunAnsFalse);
//                float mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sunAnsFalse + 1);
////                logger.info("mark = " + mark);
//                marks.add(mark);
//            } else { marks.add((float) 0);}
//        }
//
//        return marks;
//    }

}

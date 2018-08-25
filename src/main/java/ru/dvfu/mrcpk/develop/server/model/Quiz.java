package ru.dvfu.mrcpk.develop.server.model;

/**
 * Top Class QUIZ
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "quiz")
public class Quiz implements QuizInterface {

    /**
     * Quiz id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Type(type = "org.hibernate.type.IntegerType")
    private Number id;

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
    @JsonIgnore
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

}

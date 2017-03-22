package ru.dvfu.mrcpk.develop.server.model;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "Questions")
@XmlRootElement
public class Question implements QuestionInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "org.hibernate.type.IntegerType")
    private Number id;

    private String text;

    private String picture;

//    @ManyToOne
//    @JoinColumn(name = "quizid")
//    private Quiz quiz;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "questionid")
    private List<Option> options;

    public Question(){}

    public Question(int id, String text, List<Option> options) {
        this.id=id;
        this.text = text;
        this.options = options;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
//
//    public float getMark() {
//
//        //mark = КВП/ОКП/(КВН + 1)
//        int sumOptionsTrue=0,sumAnsTrue=0,sunAnsFalse=0;
//        for(int i = 0; i < options.size(); i++) {
//            if(options.get(i).isCorrect()) sumOptionsTrue++;
//            if(options.get(i).isCorrect() & options.get(i).isUserAnswer()) sumAnsTrue++;
//            if( (options.get(i).isCorrect() ^ options.get(i).isUserAnswer()) & !options.get(i).isUserAnswer()) {sunAnsFalse++; }
//        }
//
//        float mark = (float)sumAnsTrue/(float)sumOptionsTrue/((float)sunAnsFalse+1);
//
//        return mark;
//    }

//    public int getQuizid() {
//        return quizid;
//    }
//
//    public void setQuizid(int quizid) {
//        this.quizid = quizid;
//    }


//    public Quiz getQuiz() {
//        return quiz;
//    }
//
//    public void setQuiz(Quiz quiz) {
//        this.quiz = quiz;
//    }
}

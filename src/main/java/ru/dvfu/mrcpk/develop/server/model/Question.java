package ru.dvfu.mrcpk.develop.server.model;

import org.hibernate.annotations.ManyToAny;
import org.hibernate.annotations.Type;
import org.springframework.http.MediaType;
import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticQuestions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "Questions")
public class Question implements QuestionInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "org.hibernate.type.IntegerType")
    private Number id;

    private String text;

    private String picture;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "questionid")
    private List<Option> options;
//
//    @OneToMany
//    @JoinColumn(name = "questionid")
//    private List<StatisticQuestions> statisticQuestions;

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

}

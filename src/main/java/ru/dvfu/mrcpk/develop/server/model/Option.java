package ru.dvfu.mrcpk.develop.server.model;

import ru.dvfu.mrcpk.develop.server.model.statistic.StatisticOptions;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Entity
@Table(name = "Options")
public class Option implements OptionInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Type(type = "org.hibernate.type.IntegerType")
    private int id;

    private String text;

    private String picture;

    private boolean correct;

//    @OneToMany
//    @JoinColumn(name = "optionid")
//    private List<StatisticOptions> statisticOptions;

    public Option(){}

    public Option(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

//    public List<StatisticOptions> getStatisticOptions() {
//        return statisticOptions;
//    }
//
//    public void setStatisticOptions(List<StatisticOptions> statisticOptions) {
//        this.statisticOptions = statisticOptions;
//    }
}

package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Question;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created by gorynych on 10.04.17.
 */
@Entity
@Table(name = "StatisticQuestions")
public class StatisticQuestions implements StatisticQuestionsInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private float mark;

//    private Date timestart;
//
//    private Date timestop;
//
    @OneToMany
    @JoinColumn(name = "squestionid")
    private List<StatisticOptions> statisticOptionsList;

    @OneToOne
    @JoinColumn(name = "questionid",referencedColumnName = "id")
    private Question question;

    public StatisticQuestions(){}

    public StatisticQuestions(Question question){
        this.question = question;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<StatisticOptions> getStatisticOptionsList() {
        return statisticOptionsList;
    }

    public void setStatisticOptionsList(List<StatisticOptions> statisticOptionsList) {
        this.statisticOptionsList = statisticOptionsList;
    }

//    public Date getTimestart() {
//        return timestart;
//    }
//
//    public void setTimestart(Date timestart) {
//        this.timestart = timestart;
//    }
//
//    public Date getTimestop() {
//        return timestop;
//    }
//
//    public void setTimestop(Date timestop) {
//        this.timestop = timestop;
//    }

     public Float getResult(){

        if(question.getOptions().size() > 0){

            int sumOptionsTrue = 0, sumAnsTrue = 0, sumAnsFalse = 0;

            for(StatisticOptions soption : getStatisticOptionsList()){

                if(soption.getOption().isCorrect()) sumOptionsTrue++;

                if(soption.getOption().isCorrect() && soption.getId() == soption.getOption().getId())
                    sumAnsTrue++;
                if (!soption.getOption().isCorrect() && soption.getId() == soption.getOption().getId())
                    sumAnsFalse++;
            }

            //                logger.info("sumAnsTrue = " + sumAnsTrue + "; sumOptTrue = " + sumOptionsTrue + "; sumAnsFalse = " + sunAnsFalse);
            mark = sumOptionsTrue == 0 ? 0 : (float) sumAnsTrue / (float) sumOptionsTrue / ((float) sumAnsFalse + 1);

        } else { return  0.0F;}

        return mark;
     }
}

package ru.dvfu.mrcpk.develop.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

//Save result answers for question
//@Entity
//@Table(name = "UserAnswerOptions")
public class UserAnswerOptions1 {

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Type(type = "org.hibernate.type.IntegerType")
    private Number id;

    private Date date;

//    @OneToOne
//    @JoinColumn(name = "userid")
    private User user;

//    @OneToOne
//    @JoinColumn(name = "optionid")
    private Option option;

    public UserAnswerOptions1(){}

    public UserAnswerOptions1(User user, Option option){
        this.date = new Date();
        this.user = user;
        this.option = option;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Option getOption() {
        return option;
    }

    public void setOption(Option option) {
        this.option = option;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

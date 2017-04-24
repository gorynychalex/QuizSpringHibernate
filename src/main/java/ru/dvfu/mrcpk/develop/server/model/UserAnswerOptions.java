package ru.dvfu.mrcpk.develop.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;

//
@Entity
@Table(name = "UserAnswerOptions")
public class UserAnswerOptions implements UserAnswerOptionsInterface{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "org.hibernate.type.IntegerType")
    private Number id;

//    @Type(type = "timestamp")
//    private Date date;

    private int sessionid;

    private int userid;

    private int questionid;

    private int optionid;

    public UserAnswerOptions(){}

    public UserAnswerOptions(int sessionid, int userid, int questionid, int optionid) {
//        this.date = new Date(System.currentTimeMillis());
        this.sessionid = sessionid;
        this.userid = userid;
        this.questionid = questionid;
        this.optionid = optionid;
    }

    public Number getId() {
        return id;
    }

    public void setId(Number id) {
        this.id = id;
    }

//    public Date getTimestamp() {
//        return date;
//    }
//
//    public void setTimestamp(Date date) {
//        this.date = date;
//    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getOptionid() {
        return optionid;
    }

    public void setOptionid(int optionid) {
        this.optionid = optionid;
    }

    public int getSessionid() {
        return sessionid;
    }

    public void setSessionid(int sessionid) {
        this.sessionid = sessionid;
    }

    public int getQuestionid() {
        return questionid;
    }

    public void setQuestionid(int questionid) {
        this.questionid = questionid;
    }
}

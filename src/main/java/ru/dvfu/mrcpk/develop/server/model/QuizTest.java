package ru.dvfu.mrcpk.develop.server.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Question")
public class QuizTest implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Number id;

    private String text;

    @Column(name = "quiz_id")
    private int idq;

    public QuizTest(){}

    public QuizTest(String text, int idq) {
        this.text = text;
        this.idq = idq;
    }

    public QuizTest(int id, String text, int idq) {
        this.id = id;
        this.text = text;
        this.idq = idq;
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

    public int getIdq() {
        return idq;
    }

    public void setIdq(int idq) {
        this.idq = idq;
    }
}

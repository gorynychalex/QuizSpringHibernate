package ru.dvfu.mrcpk.develop.server.model;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Type(type = "org.hibernate.type.IntegerType")
    private int id;

    private String firstname;

    private String lastname;

    private String middlename;

    private String nickname;

    private String password;

//    private String usercategory;

    private Usercategory usercategory;

    public User(){}

    public User(int id, String firstname, String lastname, String middlename, String nickname, String password, Usercategory usercategory) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.nickname = nickname;
        this.password = password;
        this.usercategory = usercategory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(value = EnumType.STRING)
    public Usercategory getUsercategory() {
        return usercategory;
    }

    public void setUsercategory(Usercategory usercategory) {
        this.usercategory = usercategory;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public enum Usercategory {
        STUDENT,
        TEACHER,
        ADMIN;
    }
}

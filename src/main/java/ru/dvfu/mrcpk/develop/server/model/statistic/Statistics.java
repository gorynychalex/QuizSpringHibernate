package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.Question;
import ru.dvfu.mrcpk.develop.server.model.Quiz;
import ru.dvfu.mrcpk.develop.server.model.User;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Class for statistics of quiz.
 *
 * This class included statistic of questions and options
 *
 * Created by gorynych on 25.04.17.
 */

@Entity
@Table(name = "statisticsuiz")
public class Statistics implements StatisticsInterface {

    /**
     * No need to generate Id, because sessionId is Id!
     */
    @Id
    @Column(name = "sessionid")
    private int sessionId;

    //    @Type(type = "date")
    private Date timestart;

//    private Date timestop;

    /**
     * Mark of quiz entirely
     */
    private float mark;

    @OneToOne
    @JoinColumn(name = "quizid",referencedColumnName = "id")
    private Quiz quiz;

    @OneToOne
    @JoinColumn(name = "userid",referencedColumnName = "id")
    private User user;

    @OneToMany
    @JoinColumn(name = "sessionid", referencedColumnName = "sessionid")
    private List<squestion> squestion;

    /**
     * Empty initializer
     */
    public Statistics(){    }

    /**
     * Initializer with params:
     * @param sessionId
     * @param user
     * @param quiz
     */
    public Statistics(int sessionId, User user, Quiz quiz) {
        this.sessionId = sessionId;
        this.user = user;
        this.quiz = quiz;
        this.timestart=new Date();
    }

    public int getSessionId() {
        return sessionId;
    }

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "timestart", nullable = false)
    public Date getTimestart() {
        return timestart;
    }

    public void setTimestart(Date timestart) {
        this.timestart = timestart;
    }

//    public Date getTimestop() {
//        return timestop;
//    }
//
//    public void setTimestop(Date timestop) {
//        this.timestop = timestop;
//    }



    public float getMark() {
        return mark;
    }

    public void setMark(float mark) {
        this.mark = mark;
    }

    public List<squestion> getSquestion() {
        return squestion;
    }

    public void setSquestion(List<squestion> squestion) {
        this.squestion = squestion;
    }

    @Override
    public List<StatisticQuestions> getStatisticQuestions() {
        return null;
    }

    /**
     * Inner class for statistic quiestion
     */
    @Entity
    @Table(name = "StatisticQuizQuestion")
    public class squestion {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private float mark;

        //    private Date timestart;
        //
        //    private Date timestop;
        //
        @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
        @JoinColumn(name = "squestionid")
        private List<soption> soptions;

        @OneToOne
        @JoinColumn(name = "questionid",referencedColumnName = "id")
        private Question question;

        public squestion(){}

        public squestion(Question question){
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

        public List<soption> getSoptions() {
            return soptions;
        }

        public void setSoptions(List<soption> soptions) {
            this.soptions = soptions;
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

        /**
         * Inner Inner Class for statistic options
         */
        @Entity
        @Table(name = "StatisticQuizQuestionOption")
        public class soption {

            @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
            private int id;

            @OneToOne
            @JoinColumn(name = "optionid", referencedColumnName = "id")
            private Option option;

            public soption(){}

            public soption(Option option){
                this.option = option;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public Option getOption() {
                return option;
            }

            public void setOption(Option option) {
                this.option = option;
            }

        }

    }

}

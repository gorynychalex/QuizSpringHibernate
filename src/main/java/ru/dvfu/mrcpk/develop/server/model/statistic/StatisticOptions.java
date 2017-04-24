package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Option;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by gorynych on 10.04.17.
 */
@Entity
@Table(name = "StatisticOptions")
public class StatisticOptions implements StatisticOptionsInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "optionid", referencedColumnName = "id")
    private Option option;

    public StatisticOptions(){}

    public StatisticOptions(Option option){
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

package ru.dvfu.mrcpk.develop.server.model.statistic;

import ru.dvfu.mrcpk.develop.server.model.Option;
import ru.dvfu.mrcpk.develop.server.model.OptionInterface;

import java.io.Serializable;

/**
 * Created by gorynych on 10.04.17.
 *
 * Interface STATISTIC of OPTIONS
 */
public interface StatisticOptionsInterface extends Serializable{
    Option getOption();
    void setOption(Option option);
}

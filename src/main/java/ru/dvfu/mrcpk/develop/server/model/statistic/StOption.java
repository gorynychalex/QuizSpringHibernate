package ru.dvfu.mrcpk.develop.server.model.statistic;

import lombok.Data;
import ru.dvfu.mrcpk.develop.server.model.Option;

import javax.persistence.*;

@Entity
@Data
public class StOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "optionid", referencedColumnName = "id")
    private Option option;

    public StOption(Option option) {
        this.option = option;
    }
}

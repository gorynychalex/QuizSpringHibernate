package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;
import java.util.List;

public interface QuestionInterface extends Serializable {
    String getText();
    void setText(String text);
    Number getId();
    void setId(Number id);
    List<Option> getOptions();
}

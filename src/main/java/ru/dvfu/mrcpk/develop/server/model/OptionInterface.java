package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;

public interface OptionInterface extends Serializable{
    public String getText();

    public void setText(String text);

    public boolean isCorrect();

    public void setCorrect(boolean correct);

}

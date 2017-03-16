package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;

public interface UserInterface extends Serializable {
    int getId();
    void setId(int id);
    String getFirstname();
    void setFirstname(String firstname);
}

package ru.dvfu.mrcpk.develop.server.model;

import java.io.Serializable;

/**
 * Created by gorynych on 18.03.17.
 */
public interface UserAnswerOptionsInterface extends Serializable{

    void setUserid(int id);
    void setOptionid(int id);

//    void setUser(User user);
//    void setOption(Option option);
}

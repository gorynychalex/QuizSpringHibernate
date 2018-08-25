package ru.dvfu.mrcpk.develop.server.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

public interface UserIf extends Serializable {

    public Number getId();

    public void setId(Number id);

    public String getFirstname();

    public void setFirstname(String firstname);

    public String getLastname();

    public void setLastname(String lastname);

    public String getPassword();

    public void setPassword(String password);

    public User.Usercategory getUsercategory();

    public void setUsercategory(User.Usercategory usercategory);

    public String getMiddlename();

    public void setMiddlename(String middlename);

    public String getNickname();

    public void setNickname(String nickname);
}

package ru.dvfu.mrcpk.develop.server.model;


import javax.persistence.*;

@Entity
public class UserAuthorities {

    @Id
    private String authority;

    @ManyToOne
    private UserAuth userAuth;

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}

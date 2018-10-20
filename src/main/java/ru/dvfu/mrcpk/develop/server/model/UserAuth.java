package ru.dvfu.mrcpk.develop.server.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class UserAuth {

    @Id
    private String username;

    private String password;

    private boolean enabled;

    private String firstname;

    private String lastname;

    private String middlename;

    private String email;

    private String phonenumber;

//    private Authorities authority;
//
//    @OneToMany(cascade = CascadeType.ALL ,mappedBy = "userAuth")
//    private Set<UserAuthorities> authorities = new HashSet<>();

    @ElementCollection(targetClass = Authorities.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "username"))
    @Enumerated(EnumType.STRING)
    private Set<Authorities> authorities;

    public UserAuth(){}

    public UserAuth(String username, String password, boolean enabled) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Set<Authorities> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authorities> authorities) {
        this.authorities = authorities;
    }

    public enum Authorities implements GrantedAuthority {
        ROLE_USER,
        ROLE_TEACHER,
        ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return null;
        }
    }
}

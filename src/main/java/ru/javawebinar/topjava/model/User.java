package ru.javawebinar.topjava.model;

import java.util.Date;
import java.util.EnumSet;
import java.util.Set;

/**
 * Created by Aspire on 10.12.2016.
 */
public class User extends NamedEntity{
    private String password;

    private String email;

    private boolean enabled = true;

    private Date registered = new Date();

    private Set<Role> authorities;

    private Integer id;

    public User() {
    }

    public User(String name, String email, String password, Role role, Role...roles) {
        super(name);
        this.password = password;
        this.email = email;
        this.authorities = EnumSet.of(role, roles);
    }

    public boolean isNew(){
        return id==null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getRegistered() {
        return registered;
    }

    public void setRegistered(Date registered) {
        this.registered = registered;
    }

    public Set<Role> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }
}

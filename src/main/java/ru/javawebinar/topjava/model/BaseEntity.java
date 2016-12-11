package ru.javawebinar.topjava.model;

/**
 * Created by Aspire on 10.12.2016.
 */
public class BaseEntity {
    protected Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isNew(){
        return id==null;
    }
}

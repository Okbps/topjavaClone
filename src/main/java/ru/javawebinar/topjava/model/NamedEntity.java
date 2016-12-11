package ru.javawebinar.topjava.model;

/**
 * Created by Aspire on 10.12.2016.
 */
public class NamedEntity {
    protected String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NamedEntity() {
    }

    protected NamedEntity(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}

package com.summertraining.iysproject.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Methods {
    private @Id @GeneratedValue Long id;
    private String name;
    private String functionName;

    public Methods() {}

    public Methods(Long id, String name, String functionName) {
        this.id = id;
        this.name = name;
        this.functionName = functionName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    @Override
    public String toString() {
        return "Methods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", functionName='" + functionName + '\'' +
                '}';
    }
}

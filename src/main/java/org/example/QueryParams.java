package org.example;

public abstract class QueryParams {   
    public String name;

    public abstract boolean hasName();

    public abstract void setName(String name);

    public abstract String getName();
}

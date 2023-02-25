package com.ingenifi.chartifi.yuml.model;

public record Type(String name, Argument argument, boolean isCollection, boolean isCustom, String completeName) {

    public Type(String name, Argument argument, boolean isCollection, boolean isCustom) {
        this(name, argument, isCollection, isCustom, name);
    }

    public Type(String name, Argument argument) {
        this(name, argument, false, false, name);
    }

    public Type(String name) {
        this(name, null, false, false, name);
    }

}

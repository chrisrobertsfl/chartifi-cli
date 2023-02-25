package com.ingenifi.chartifi.yuml.model;

public record Variable(String name, Type type, Scope scope, boolean isStatic, boolean isFinal) {
    public Variable(String name, Type type, Scope scope, boolean isStatic) {
        this(name, type, scope, isStatic, false);
    }

    public Variable(String name, Type type, Scope scope) {
        this(name, type, scope, false, false);
    }
}

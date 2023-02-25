package com.ingenifi.chartifi.yuml.model;

import java.util.List;

import static java.util.Collections.emptyList;

public record Operation(String name, Scope scope, Type returnType, List<Parameter> parameters, boolean isStatic,
                        boolean isFinal) {
    // TODO: Figure out best order for these constructor parameters:
    public Operation(String name, Scope scope, Type returnType, List<Parameter> parameters) {
        this(name, scope, returnType, parameters, false, false);
    }

    public Operation(String name, Scope scope, Type returnType) {
        this(name, scope, returnType, emptyList(), false, false);
    }
}

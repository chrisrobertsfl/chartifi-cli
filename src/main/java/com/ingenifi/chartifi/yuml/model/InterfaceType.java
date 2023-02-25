package com.ingenifi.chartifi.yuml.model;

import java.util.List;

import static java.util.Collections.emptyList;

public record InterfaceType(String name, List<Variable> variables) implements TypedEntity {
    public InterfaceType(String name, Variable... variables) {
        this(name, List.of(variables));
    }

    public InterfaceType(String name) {
        this(name, emptyList());
    }
}

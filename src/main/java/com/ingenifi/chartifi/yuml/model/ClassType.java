package com.ingenifi.chartifi.yuml.model;

import java.util.List;

import static java.util.Collections.emptyList;

public record ClassType(String name, List<Variable> variables, List<Operation> operations) implements TypedEntity {
    public ClassType(String name, List<Variable> variables) {
        this(name, variables, emptyList());
    }
    public ClassType(String name) {
        this(name, emptyList(), emptyList());
    }
}

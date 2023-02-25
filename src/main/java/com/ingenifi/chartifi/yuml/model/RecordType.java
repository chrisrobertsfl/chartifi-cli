package com.ingenifi.chartifi.yuml.model;

import java.util.List;

import static java.util.Collections.emptyList;

public record RecordType(String name, List<Variable> variables) implements TypedEntity {
    public RecordType(String name, Variable... variables) {
        this(name, List.of(variables));
    }

    public RecordType(String name) {
        this(name, emptyList());
    }
}

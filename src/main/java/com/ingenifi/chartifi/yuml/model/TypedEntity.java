package com.ingenifi.chartifi.yuml.model;

import java.util.List;

public sealed interface TypedEntity permits ClassType, InterfaceType, RecordType {
    String name();

    List<Variable> variables();
}

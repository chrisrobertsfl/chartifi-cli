package com.ingenifi.chartifi.yuml.render;


import com.ingenifi.chartifi.yuml.model.RecordType;
import com.ingenifi.chartifi.yuml.model.Variable;

import java.util.List;

import static java.util.stream.Collectors.joining;

public record RecordRenderer(RecordType recordType) {
    public String render() {
        List<Variable> variables = recordType.variables();
        String name = recordType.name();
        if (variables == null || variables.isEmpty()) {
            return "[<<record>>;" + name + "]";
        }
        return "[<<record>>;" + name + "|" + renderVariables(variables) + "]";
    }

    String renderVariables(List<Variable> variables) {
        VariableRenderer variableRenderer = new VariableRenderer();
        return variables.stream()
                .map(variableRenderer::render)
                .collect(joining(";"));
    }
}

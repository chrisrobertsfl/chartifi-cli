package com.ingenifi.chartifi.yuml.render;

import com.ingenifi.chartifi.yuml.model.InterfaceType;
import com.ingenifi.chartifi.yuml.model.Variable;

import java.util.List;

import static java.util.stream.Collectors.joining;

public record InterfaceRenderer(InterfaceType interfaceType) {
    public String render() {
        List<Variable> variables = interfaceType.variables();
        String name = interfaceType.name();
        if (variables == null || variables.isEmpty()) {
            return "[<<interface>>;" + name + "]";
        }
        return "[<<interface>>;" + name + "|" + renderVariables(variables) + "]";
    }

    String renderVariables(List<Variable> variables) {
        VariableRenderer variableRenderer = new VariableRenderer();
        return variables.stream()
                .map(variableRenderer::render)
                .collect(joining(";"));
    }
}

package com.ingenifi.chartifi.yuml.render;

import com.ingenifi.chartifi.yuml.model.ClassType;
import com.ingenifi.chartifi.yuml.model.Operation;
import com.ingenifi.chartifi.yuml.model.Variable;

import java.util.List;

import static com.chrisrobertsfl.base.MoreIterables.isNullOrEmpty;
import static java.util.stream.Collectors.joining;
import static org.apache.commons.lang3.StringUtils.EMPTY;


// TODO:  Inject VariablesRenderer
// TODO:  Inject OperationsRenderer
public record ClassRenderer(ClassType classType) {
    public String render() {
        List<Variable> variables = classType.variables();
        List<Operation> operations = classType.operations();
        String name = classType.name();
        return "[" + name + renderVariables(variables) + renderOperations(operations) + "]";
    }

    String renderVariables(List<Variable> variables) {
        if (isNullOrEmpty(variables)) {
            return EMPTY;
        }
        VariableRenderer variableRenderer = new VariableRenderer();
        return "|" + variables.stream()
                .map(variableRenderer::render)
                .collect(joining(";"));
    }

    String renderOperations(List<Operation> operations) {
        if (isNullOrEmpty(operations)) {
            return EMPTY;
        }
        OperationRenderer operationRenderer = new OperationRenderer();
        return "|" + operations.stream()
                .map(operationRenderer::render)
                .collect(joining(";"));
    }

}

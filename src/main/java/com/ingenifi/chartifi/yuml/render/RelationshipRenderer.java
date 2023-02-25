package com.ingenifi.chartifi.yuml.render;


import com.ingenifi.chartifi.yuml.model.Argument;
import com.ingenifi.chartifi.yuml.model.Type;
import com.ingenifi.chartifi.yuml.model.TypedEntity;
import com.ingenifi.chartifi.yuml.model.Variable;

import java.util.Optional;
import java.util.stream.Collectors;

import static com.chrisrobertsfl.base.Exceptions.supplyRuntime;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;

public record RelationshipRenderer() {
    public String render(TypedEntity typedEntity) {
        return typedEntity.variables().stream()
                .map(variable -> renderRelationship(typedEntity.name(), variable))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.joining("\n"));
    }

    Optional<String> renderRelationship(String className, Variable variable) {
        Type type = variable.type();
        String variableName = variable.name();
        if (type.isCustom()) {
            return Optional.of("[" + className + "]" + variableName + "->[" + type.name() + "]");
        }
        if (type.isCollection()) {
            Argument argument = ofNullable(type.argument())
                    .orElseThrow(supplyRuntime("Argument is missing for type: %s", type));
            if (argument.isCustom()) {
                return Optional.of("[" + className + "]" + variableName + "++->[" + argument.name() + "]");
            }
        }
        return empty();
    }
}

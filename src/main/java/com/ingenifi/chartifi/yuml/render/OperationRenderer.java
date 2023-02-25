package com.ingenifi.chartifi.yuml.render;


import com.chrisrobertsfl.base.MoreIterables;
import com.ingenifi.chartifi.yuml.model.Operation;
import com.ingenifi.chartifi.yuml.model.Parameter;
import com.ingenifi.chartifi.yuml.model.Scope;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.chrisrobertsfl.base.Exceptions.supplyRuntime;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

// TODO:  DRY -> Angle bracket replacement
public record OperationRenderer() {
    public String render(Operation operation) {
        String modifiers = renderModifiers(operation);
        String rendered = modifiers;
        rendered += modifiers.isEmpty() ? "" : " ";
        rendered += operation.name();
        rendered += renderParameters(operation.parameters());
        rendered += " : ";
        rendered += operation.returnType()
                .completeName()
                .replace("<", "\\<")
                .replace(">", "\\>");
        return rendered;
    }

    String renderParameters(List<Parameter> parameters) {
        if (MoreIterables.isNullOrEmpty(parameters)) {
            return "()";
        }
        String rendered = "(";
        rendered += parameters.stream()
                .map(this::renderParameter)
                .collect(Collectors.joining(", "));
        rendered += ")";
        return rendered;
    }

    String renderParameter(Parameter parameter) {
        return parameter.name() + " : " + parameter.type()
                .completeName()
                .replace("<", "\\<")
                .replace(">", "\\>");
    }

    // TODO:  Make reusable class for this:
    String renderModifiers(Operation operation) {
        return Stream.of(
                        renderScope(operation.scope()),
                        renderStatic(operation.isStatic()),
                        renderFinal(operation.isFinal())
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(joining(" "));
    }

    // TODO:  Make reusable class for this:
    Optional<String> renderScope(final Scope scope) {
        ofNullable(scope)
                .orElseThrow(supplyRuntime("Scope is missing"));
        return switch (scope) {
            case PUBLIC -> Optional.of("public");
            case PRIVATE -> Optional.of("private");
            case PROTECTED -> Optional.of("protected");
            case DEFAULT -> empty();
        };
    }

    Optional<String> renderStatic(final boolean isStatic) {
        return isStatic ? Optional.of("static") : empty();
    }

    Optional<String> renderFinal(final boolean isFinal) {
        return isFinal ? Optional.of("final") : empty();
    }
}

package com.ingenifi.chartifi.yuml.render;

import com.ingenifi.chartifi.yuml.model.Scope;
import com.ingenifi.chartifi.yuml.model.Variable;

import java.util.Optional;
import java.util.stream.Stream;

import static com.chrisrobertsfl.base.Exceptions.supplyRuntime;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static java.util.stream.Collectors.joining;

public record VariableRenderer() {
    public String render(Variable variable) {
        String modifiers = renderModifiers(variable);
        String rendered = modifiers;
        rendered += modifiers.isEmpty() ? "" : " ";
        rendered += variable.name();
        rendered += " : ";
        rendered += variable.type()
                .completeName()
                .replace("<", "\\<")
                .replace(">", "\\>");
        return rendered;
    }

    String renderModifiers(Variable variable) {
        return Stream.of(
                        renderScope(variable.scope()),
                        renderStatic(variable.isStatic()),
                        renderFinal(variable.isFinal())
                )
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(joining(" "));
    }

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

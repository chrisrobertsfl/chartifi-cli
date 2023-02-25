package com.ingenifi.chartifi.yuml.model;

import org.jboss.forge.roaster.model.VisibilityScoped;

import java.util.function.Predicate;

import static java.util.EnumSet.allOf;

public enum Scope {
    PRIVATE(VisibilityScoped::isPrivate),
    PROTECTED(VisibilityScoped::isProtected),
    PUBLIC(VisibilityScoped::isPublic),
    DEFAULT(VisibilityScoped::isPackagePrivate);
    private final Predicate<VisibilityScoped> predicate;

    Scope(Predicate<VisibilityScoped> predicate) {
        this.predicate = predicate;
    }

    public static Scope of(VisibilityScoped visibilityScoped) {
        return allOf(Scope.class).stream()
                .filter(scope -> scope.predicate.test(visibilityScoped))
                .findFirst()
                .orElse(null);
    }
}

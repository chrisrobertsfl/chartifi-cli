package com.ingenifi.chartifi.yuml.model;

public record Argument(String name, boolean isCustom) {
    public Argument(String name) {
        this(name, false);
    }
}

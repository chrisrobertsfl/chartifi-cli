package com.ingenifi.chartifi.yuml.render;

import com.ingenifi.chartifi.yuml.model.ClassType;
import com.ingenifi.chartifi.yuml.model.InterfaceType;
import com.ingenifi.chartifi.yuml.model.RecordType;
import com.ingenifi.chartifi.yuml.model.TypedEntity;

import java.util.Optional;
import java.util.function.Supplier;

import static com.chrisrobertsfl.base.Exceptions.supplyRuntime;
import static java.util.Optional.empty;
import static java.util.Optional.ofNullable;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public record YumlRenderer() {
    static Optional<String> nameOf(TypedEntity typedEntity) {
        return isNotBlank(typedEntity.name()) ? Optional.of(typedEntity.name()) : empty();
    }

    static Supplier<RuntimeException> missingNameException(TypedEntity typedEntity) {
        return supplyRuntime(typedEntity.getClass().getSimpleName() + " missing name");
    }

    public String render(TypedEntity typedEntity) {
        ofNullable(typedEntity)
                .orElseThrow(supplyRuntime("Missing typed entity"));
        nameOf(typedEntity)
                .orElseThrow(missingNameException(typedEntity));
        return switch (typedEntity) {
            case ClassType type -> new ClassRenderer(type).render();
            case RecordType type -> new RecordRenderer(type).render();
            case InterfaceType type -> new InterfaceRenderer(type).render();
        };
    }

}

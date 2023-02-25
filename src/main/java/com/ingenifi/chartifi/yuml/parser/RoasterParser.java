package com.ingenifi.chartifi.yuml.parser;

import com.chrisrobertsfl.base.MorePaths;
import com.ingenifi.chartifi.yuml.model.*;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.JavaUnit;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

import static com.chrisrobertsfl.coreengine.service.drools.DroolsEngine.assemble;
import static com.chrisrobertsfl.coreengine.service.drools.Option.SHOW_FACTS;
import static com.chrisrobertsfl.coreengine.service.drools.Option.SHOW_RULES;
import static com.chrisrobertsfl.coreengine.service.drools.RuleSource.file;
import static com.google.common.io.MoreFiles.getFileExtension;

public record RoasterParser() {

    static final Predicate<Path> HAS_JAVA_EXTENSION = path -> "java".equals(getFileExtension(path));

    public List<? extends TypedEntity> parse(List<Path> files) {
        return files.stream()
                .filter(HAS_JAVA_EXTENSION)
                .peek(p -> System.out.println("path is ---> " + p))
                .map(MorePaths::asString)
                .map(Roaster::parseUnit)
                .map(this::parse)
                .flatMap(Collection::stream)
                .filter(ClassType.class::isInstance)
                .map(ClassType.class::cast)
                .toList();

//        return List.of(new ClassType(
//                        "TextCommand",
//                        List.of(
//                                new Variable("fileOrDirectory", new Type("Path"), DEFAULT)
//                        ),
//                        List.of(
//                                new Operation("call", PUBLIC, new Type("Integer"))
//                        )
//                )
//        );
    }

    public Collection<?> parse(JavaUnit unit) {
        List<JavaType<?>> topLevelTypes = unit.getTopLevelTypes();
        JavaType<?> javaType = topLevelTypes.get(0);

        return assemble()
                .option(SHOW_RULES)
                .option(SHOW_FACTS)
                .rules(file("src/main/resources/model-parser.drl"))
                .insertAll(topLevelTypes)
                .run()
                .findAll();
    }
}

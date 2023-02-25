package com.ingenifi.chartifi.yuml;

import com.chrisrobertsfl.base.MorePaths;
import org.jboss.forge.roaster.Roaster;
import org.jboss.forge.roaster.model.JavaType;
import org.jboss.forge.roaster.model.JavaUnit;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.nio.file.Paths;

import static com.chrisrobertsfl.coreengine.service.drools.DroolsEngine.assemble;
import static com.chrisrobertsfl.coreengine.service.drools.Option.SHOW_FACTS;
import static com.chrisrobertsfl.coreengine.service.drools.Option.SHOW_RULES;
import static com.chrisrobertsfl.coreengine.service.drools.RuleSource.classpath;

public class RuleRunTest {

    @Disabled
    @Test
    void run() {

        JavaUnit unit = Roaster.parseUnit(MorePaths.asString(Paths.get("src/main/java/com/ingenifi/chartifi/cli/TextCommand.java")));
        System.out.println("unit = " + unit);
        unit.getTopLevelTypes().stream().map(JavaType::getName).forEach(n -> System.out.printf("---------> %s\n", n));
        assemble()
                .option(SHOW_RULES)
                .option(SHOW_FACTS)
                .rules(classpath("model-parser.drl"))
                .insertAll(unit.getTopLevelTypes())
                .run()
                .findAll()
                .forEach(System.out::println);
    }
}

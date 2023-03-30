package com.example.bug;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureMonkeyTest {
    FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .defaultArbitraryContainerInfoGenerator(context -> new ArbitraryContainerInfo(1, 1))
            .nullableContainer(false)
            .nullableElement(false)
            .interfaceImplements(Book.class, List.of(FantasyBook.class))
            .pushAssignableTypeArbitraryIntrospector(Record.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();

    @Test
    void bugOne() {
        CustomJooqRecord customJooqRecord = fixtureMonkey.giveMeOne(CustomJooqRecord.class);

        assertThat(customJooqRecord).isNotNull();
    }

    @Test
    void bugTwo() {
        BasicCondition basicCondition = fixtureMonkey.giveMeOne(BasicCondition.class);

        assertThat(basicCondition).isNotNull();
    }

    @Test
    void bugThree() {
        // I'm still trying to replicate the issue
        StuffWithNodes node = fixtureMonkey.giveMeOne(StuffWithNodes.class);

        assertThat(node).isNotNull();
    }
}

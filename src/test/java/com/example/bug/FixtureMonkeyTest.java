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
    void testMe() {
        Book book = fixtureMonkey.giveMeOne(Book.class);

        assertThat(book).isNotNull();
    }
}

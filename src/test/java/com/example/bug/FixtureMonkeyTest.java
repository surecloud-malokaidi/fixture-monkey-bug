package com.example.bug;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.JavaTypeArbitraryGenerator;
import net.jqwik.api.arbitraries.StringArbitrary;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.multipart.FilePart;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureMonkeyTest {

    static class CustomJavaTypeArbitaryGenerator implements JavaTypeArbitraryGenerator {
        @Override
        public StringArbitrary strings() {
            return JavaTypeArbitraryGenerator.super.strings()
                    .alpha()
                    .ofMinLength(5)
                    .ofMaxLength(25)
                    .repeatChars(0);
        }
    }

    FixtureMonkey fixtureMonkey = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .defaultArbitraryContainerInfoGenerator(context -> new ArbitraryContainerInfo(1, 1))
            .nullableContainer(false)
            .defaultNotNull(true)
            .nullableElement(false)
            .interfaceImplements(Book.class, List.of(FantasyBook.class))
            .pushAssignableTypeArbitraryIntrospector(Record.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            //.pushAssignableTypeArbitraryIntrospector(DataTest.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .javaTypeArbitraryGenerator(new CustomJavaTypeArbitaryGenerator())
            .build();

    @RepeatedTest(100)
    @Disabled
    void bug() {
        List<Node> nodes = fixtureMonkey.giveMe(Node.class, 25);

        List<String> names = nodes.stream()
                .map(node -> node.name)
                .toList();

        Map<String, Long> duplicateCount = names.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(s -> s, Collectors.counting()));

        List<String> duplicateStrings = duplicateCount.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        System.out.println("Duplicate strings: " + duplicateStrings);


        assertThat(duplicateStrings).isEmpty();
    }

    @Test
    void anotherBug() {
        View view = fixtureMonkey.giveMeBuilder(new View())
                .set("version", new AggregateVersion(1))
                .set("deleted", true)
                .sample();

        assertThat(view.isDeleted()).isTrue();
        assertThat(view.getVersion().getValue()).isNotEqualTo(0);
    }

    @Test
    void filePart() {
        FilePart filePart = fixtureMonkey.giveMeOne(FilePart.class);

        assertThat(filePart).isNotNull();
    }

    @Test
    void atDataBug() {
        DataTest dataTest = fixtureMonkey.giveMeOne(DataTest.class);

        assertThat(dataTest).isNotNull();
        assertThat(dataTest.getId()).isNotNull();
        assertThat(dataTest.getName()).isNotNull();
    }
}

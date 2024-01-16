package com.example.bug;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.http.codec.multipart.FilePart;

import java.net.URL;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureMonkeyTest {

    private final static FixtureMonkey FIXTURE_MONKEY = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .defaultArbitraryContainerInfoGenerator(context -> new ArbitraryContainerInfo(1, 1))
            .nullableContainer(false)
            .defaultNotNull(true)
            .nullableElement(false)
            .interfaceImplements(Book.class, List.of(FantasyBook.class))
            .interfaceImplements(Condition.class, List.of(ValueCondition.class))
            .pushAssignableTypeArbitraryIntrospector(Record.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .pushAssignableTypeArbitraryIntrospector(Timestamp.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .pushAssignableTypeArbitraryIntrospector(URL.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE)
            .build();

    @RepeatedTest(100)
    @Disabled
    void bug() {
        List<Node> nodes = FIXTURE_MONKEY.giveMe(Node.class, 25);

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
        View view = FIXTURE_MONKEY.giveMeBuilder(new View())
                .set("version", new AggregateVersion(1))
                .set("deleted", true)
                .sample();

        assertThat(view.isDeleted()).isTrue();
        assertThat(view.getVersion().getValue()).isNotEqualTo(0);
    }

    @Test
    void filePart() {
        FilePart filePart = FIXTURE_MONKEY.giveMeOne(FilePart.class);

        assertThat(filePart).isNotNull();
    }

    @Test
    void atDataBug() {
        DataTest dataTest = FIXTURE_MONKEY.giveMeOne(DataTest.class);

        assertThat(dataTest).isNotNull();
        assertThat(dataTest.getId()).isNotNull();
        assertThat(dataTest.getName()).isNotNull();
    }

    @Test
    void nodeBug() {
        Node node = FIXTURE_MONKEY.giveMeOne(Node.class);

        assertThat(node.nodes).isNotEmpty();

        Node parentNode = FIXTURE_MONKEY.giveMeBuilder(Node.class)
                .set("nodes", List.of(node))
                .sample();

        assertThat(parentNode).isNotNull();
        assertThat(parentNode.nodes).isNotEmpty();
        assertThat(parentNode.nodes.get(0).nodes).isNotEmpty();
    }

    @Test
    void timestamp() {
        Timestamp timestamp = FIXTURE_MONKEY.giveMeOne(Timestamp.class);

        assertThat(timestamp).isNotNull();
    }

    @Test
    void recordBug() {
        WorkflowRecord workflowRecord = FIXTURE_MONKEY.giveMeOne(WorkflowRecord.class);

        assertThat(workflowRecord).isNotNull();
    }

    @Test
    void locale() {
        URL locale = FIXTURE_MONKEY.giveMeOne(URL.class);

        assertThat(locale).isNotNull();
    }

    @Test
    void objectTest() {
        ObjectTest sample = FIXTURE_MONKEY.giveMeBuilder(ObjectTest.class)
                .set("object", "test")
                .sample();

        assertThat(sample.getObject()).isEqualTo("test");
    }

    @RepeatedTest(100)
    void setBug() {
        ValueCondition condition = FIXTURE_MONKEY.giveMeOne(ValueCondition.class);
        UUID id = FIXTURE_MONKEY.giveMeOne(UUID.class);

        Trigger trigger = FIXTURE_MONKEY.giveMeBuilder(Trigger.class)
                .set("id", id)
                .set("conditions", List.of(condition))
                .sample();

        assertThat(trigger.getId()).isEqualTo(id);
        assertThat(trigger.getConditions().get(0)).isEqualTo(condition);
    }
}

package com.example.bug;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FixtureMonkeyTest {

    private final static FixtureMonkey FIXTURE_MONKEY = FixtureMonkey.builder()
            .objectIntrospector(FieldReflectionArbitraryIntrospector.INSTANCE)
            .interfaceImplements(EntityAttribute.class, List.of(NumberEntityAttribute.class, TextEntityAttribute.class))
            .generateMaxTries(Integer.MAX_VALUE)
            .build();

    @Test
    void bug() {
        List<EntityAttribute> attributes = FIXTURE_MONKEY.giveMeBuilder(EntityAttribute.class)
                .setPostCondition(attribute -> isANumber(attribute))
                .sampleList(5);

        assertThat(attributes).hasSize(5);
    }

    private static boolean isANumber(EntityAttribute attribute) {
        System.out.printf("attribute is %s%n", attribute.getClass().getSimpleName());
        return attribute instanceof NumberEntityAttribute;
    }

}

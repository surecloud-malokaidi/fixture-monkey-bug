package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;

class StackOverflow
{
    record Author( String name, List<Book> books ) {}

    record Book( String name, Author author ) {}

    @Test
    void fails()
    {
        FixtureMonkey fixtureMonkey = FixtureMonkey.labMonkeyBuilder()
                                                   .objectIntrospector( ConstructorPropertiesArbitraryIntrospector.INSTANCE )
                                                   .build();

        Book book = fixtureMonkey.giveMeOne( Book.class );

        assertThat( book ).isNotNull();
    }
}

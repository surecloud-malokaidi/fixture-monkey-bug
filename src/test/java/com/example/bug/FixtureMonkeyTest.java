package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

class FixtureMonkeyTest
{
    FixtureMonkey fixtureMonkey = LabMonkey.labMonkeyBuilder()
                                           .objectIntrospector( FieldReflectionArbitraryIntrospector.INSTANCE )
                                           .defaultArbitraryContainerInfo( new ArbitraryContainerInfo( 1, 3, false ) )
                                           .nullableContainer( false )
                                           .nullableElement( false )
                                           .interfaceImplements( IAuthor.class, List.of( IJohn.class ) )
                                           .interfaceImplements( AbstractAuthor.class, List.of( John.class ) )
                                           .build();

    @Test
    void abstractClass()
    {
        Book book = fixtureMonkey.giveMeOne( Book.class );

        assertThat( book.getAuthors() ).isNotEmpty().doesNotContainNull();
    }

    @Test
    void interfaceTest()
    {
        IBook book = fixtureMonkey.giveMeOne( IBook.class );

        assertThat( book.getAuthors() ).isNotEmpty().doesNotContainNull();
    }
}

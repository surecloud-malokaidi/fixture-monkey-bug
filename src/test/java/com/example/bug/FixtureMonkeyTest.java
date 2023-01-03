package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.ConstructorPropertiesArbitraryIntrospector;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

class FixtureMonkeyTest
{
    FixtureMonkey fixtureMonkey = LabMonkey.labMonkeyBuilder()
                                           .objectIntrospector( FieldReflectionArbitraryIntrospector.INSTANCE )
                                           .defaultArbitraryContainerInfo( new ArbitraryContainerInfo( 1, 1, false ) )
                                           .nullableContainer( false )
                                           .nullableElement( false )
                                           .pushAssignableTypeArbitraryIntrospector( AbstractAuthor.class, ConstructorPropertiesArbitraryIntrospector.INSTANCE )
                                           .interfaceImplements( AbstractAuthor.class, List.of( John.class, Peter.class, Mark.class ) )
                                           .build();

    @Test
    void abstractTest()
    {
        Mark mark = fixtureMonkey.giveMeOne( Mark.class );

        Book book = fixtureMonkey.giveMeBuilder( Book.class )
                                 .set( "authors", List.of( mark ) )
                                 .sample();

        assertThat( book.getAuthors() ).isNotEmpty().doesNotContainNull();
    }
}

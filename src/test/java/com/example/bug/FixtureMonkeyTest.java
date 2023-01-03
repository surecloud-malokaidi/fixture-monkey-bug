package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.jackson.introspector.JacksonArbitraryIntrospector;

class FixtureMonkeyTest
{
    FixtureMonkey fixtureMonkey = LabMonkey.labMonkeyBuilder()
                                           .objectIntrospector( FieldReflectionArbitraryIntrospector.INSTANCE )
                                           .defaultArbitraryContainerInfo( new ArbitraryContainerInfo( 1, 1, false ) )
                                           .nullableContainer( false )
                                           .nullableElement( false )
                                           .interfaceImplements( Condition.class, List.of( BasicCondition.class, ValueCondition.class ) )
                                           .build();

    @Test
    void bugTest()
    {
        ValueCondition condition = fixtureMonkey.giveMeOne( ValueCondition.class );

        Workflow workflow = fixtureMonkey.giveMeBuilder( Workflow.class )
                                         .set( "states[0].triggers[0].conditions", List.of( condition ) )
                                         .sample();

        assertThat( workflow ).isNotNull();
    }

    @Test
    void invalidIntrospector()
    {
        FixtureMonkey fixtureMonkey = LabMonkey.labMonkeyBuilder()
                                               .objectIntrospector( FieldReflectionArbitraryIntrospector.INSTANCE )
                                               .defaultArbitraryContainerInfo( new ArbitraryContainerInfo( 1, 1, false ) )
                                               .nullableContainer( false )
                                               .nullableElement( false )
                                               .pushAssignableTypeArbitraryIntrospector( Trigger.class, JacksonArbitraryIntrospector.INSTANCE )
                                               .interfaceImplements( Condition.class, List.of( BasicCondition.class, ValueCondition.class ) )
                                               .build();

        Workflow workflow = fixtureMonkey.giveMeOne( Workflow.class );

        assertThat( workflow ).isNotNull();
    }
}

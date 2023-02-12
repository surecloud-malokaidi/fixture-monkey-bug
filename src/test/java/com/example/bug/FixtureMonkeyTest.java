package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.LabMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;

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
    void mockTest()
    {
        UUID id = UUID.randomUUID();
        ValueCondition condition = mock( ValueCondition.class );

        given( condition.getId() ).willReturn( id );

        assertThat( condition.getId() ).isEqualTo( id );

        Workflow workflow = fixtureMonkey.giveMeBuilder( Workflow.class )
                                         .set( "states[0].triggers[0].conditions", List.of( condition ) )
                                         .sample();

        assertThat( workflow.getStates().get( 0 ).getTriggers().get( 0 ).getConditions().get( 0 ).getId() ).isEqualTo( id );
    }

    @Test
    void entityTest()
    {
        Entity entity = fixtureMonkey.giveMeOne( Entity.class );

        assertThat( entity ).isNotNull();
    }
}

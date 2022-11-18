package com.example.bug;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Map;

import org.junit.jupiter.api.Test;

import com.navercorp.fixturemonkey.FixtureMonkey;
import com.navercorp.fixturemonkey.api.generator.ArbitraryContainerInfo;
import com.navercorp.fixturemonkey.api.introspector.FieldReflectionArbitraryIntrospector;
import com.navercorp.fixturemonkey.generator.FieldReflectionArbitraryGenerator;

class Bug
{
    @Test
    void fails()
    {
        FixtureMonkey labMonkey =
            FixtureMonkey.labMonkeyBuilder().objectIntrospector( FieldReflectionArbitraryIntrospector.INSTANCE ).defaultArbitraryContainerInfo( new ArbitraryContainerInfo( 1, 1, false ) )
                         .defaultNotNull( true ).build();

        BookAttribute bookAttribute = new BookAttribute( "Attribute name" );
        bookAttribute.getRules().put( BookAttributeValidationRuleType.MINIMUM_CHARACTER_LIMIT, new MinimumCharacterLimitValidationRule( 3 ) );

        Book book = labMonkey.giveMeBuilder( Book.class ).set( "attributes", Map.of( bookAttribute.getName(), bookAttribute ) ).sample();

        assertThat( book.getAttributes().get( bookAttribute.getName() ).getRules().get( BookAttributeValidationRuleType.MINIMUM_CHARACTER_LIMIT ) ).isNotNull();
    }

    @Test
    void works()
    {
        FixtureMonkey labMonkey =
            FixtureMonkey.builder().defaultGenerator( FieldReflectionArbitraryGenerator.INSTANCE )
                         .nullableContainer( false )
                         .defaultNotNull( true )
                         .build();

        BookAttribute bookAttribute = new BookAttribute( "Attribute name" );
        bookAttribute.getRules().put( BookAttributeValidationRuleType.MINIMUM_CHARACTER_LIMIT, new MinimumCharacterLimitValidationRule( 3 ) );

        Book book = labMonkey.giveMeBuilder( Book.class ).set( "attributes", Map.of( bookAttribute.getName(), bookAttribute ) ).sample();

        assertThat( book.getAttributes().get( bookAttribute.getName() ).getRules().get( BookAttributeValidationRuleType.MINIMUM_CHARACTER_LIMIT ) ).isNotNull();
    }
}

package com.example.bug;

import java.util.EnumMap;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
public class BookAttribute
{
    private String name;

    private Map<BookAttributeValidationRuleType, BookAttributeValidationRule> rules = new EnumMap<>( BookAttributeValidationRuleType.class );

    public BookAttribute( final String name )
    {
        this.name = name;
    }
}

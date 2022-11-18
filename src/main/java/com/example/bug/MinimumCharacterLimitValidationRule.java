package com.example.bug;

public record MinimumCharacterLimitValidationRule( int limit )
    implements BookAttributeValidationRule
{
    @Override
    public BookAttributeValidationRuleType getRuleType()
    {
        return BookAttributeValidationRuleType.MINIMUM_CHARACTER_LIMIT;
    }
}

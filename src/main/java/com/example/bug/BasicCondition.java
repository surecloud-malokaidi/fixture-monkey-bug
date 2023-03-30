package com.example.bug;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class BasicCondition
    extends Condition
{
    public Class<?> anyClass;
}

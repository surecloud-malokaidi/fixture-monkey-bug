package com.example.bug;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class StringValue extends Value {
    public StringValue(String value) {
        super(value);
    }
}

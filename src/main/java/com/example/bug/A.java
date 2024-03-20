package com.example.bug;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class A extends Letter {
    protected String test;
}

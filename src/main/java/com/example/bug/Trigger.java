package com.example.bug;

import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Trigger
{
    private UUID id;

    private List<Condition> conditions;
}

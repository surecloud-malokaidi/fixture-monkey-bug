package com.example.bug;

import java.util.List;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class State
{
    private UUID id;

    private List<Trigger> triggers;
}

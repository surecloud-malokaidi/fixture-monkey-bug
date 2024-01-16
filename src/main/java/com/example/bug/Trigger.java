package com.example.bug;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class Trigger
{
    private UUID id;

    private List<Condition> conditions;
}

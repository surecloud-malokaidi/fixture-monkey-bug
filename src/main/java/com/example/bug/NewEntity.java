package com.example.bug;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class NewEntity {
    private UUID entityId;

    private String name;

    private List<EntityAttribute> attributes;
}

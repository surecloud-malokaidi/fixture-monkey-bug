package com.example.bug;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class EntityAttribute {
    protected UUID attributeId;

    protected String name;

    protected List<EntityAttributePermission> permissions = new ArrayList<>();
}

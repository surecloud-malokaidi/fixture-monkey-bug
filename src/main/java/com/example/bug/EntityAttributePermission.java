package com.example.bug;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.UUID;

@Getter
@EqualsAndHashCode
public class EntityAttributePermission {
    private UUID entityAttributePermissionId;

    private boolean readPermission;

    private boolean updatePermission;
}

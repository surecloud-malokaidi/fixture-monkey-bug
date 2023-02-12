package com.example.bug;

import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class Entity
    extends PersistableEntity<UUID>
{
    @Id
    private UUID uuid;

    @Override
    public UUID getId()
    {
        return uuid;
    }

    @Override
    public boolean isNew()
    {
        return false;
    }
}

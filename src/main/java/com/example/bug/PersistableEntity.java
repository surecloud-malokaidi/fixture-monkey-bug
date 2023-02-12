package com.example.bug;

import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public abstract class PersistableEntity<T>
    implements Persistable<T>
{
    @Transient
    private boolean isNew;

    @Override
    public boolean isNew()
    {
        return isNew;
    }
}

package com.example.bug;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Aggregate {
    protected AggregateVersion version;

    protected boolean deleted;
}

package com.example.bug;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AggregateVersion {
    private final long value;

    public AggregateVersion() {
        this.value = 0;
    }

    public AggregateVersion(long value) {
        this.value = value;
    }
}

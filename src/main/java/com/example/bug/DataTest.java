package com.example.bug;

import lombok.Data;

import java.util.UUID;

@Data
public class DataTest {
    private final UUID id;

    private String name;
}

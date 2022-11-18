package com.example.bug;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book
{
    private UUID id;

    private Map<String, BookAttribute> attributes = new HashMap<>();
}
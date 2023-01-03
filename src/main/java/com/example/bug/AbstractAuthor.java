package com.example.bug;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class AbstractAuthor
{
    protected String name;

    protected List<AbstractAuthor> authors;
}

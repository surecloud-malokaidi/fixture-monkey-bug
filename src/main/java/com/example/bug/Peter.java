package com.example.bug;

import java.beans.ConstructorProperties;
import java.util.ArrayList;

import lombok.Getter;

@Getter
public class Peter
    extends AbstractAuthor
{
    private String age;

    @ConstructorProperties( "age" )
    public Peter( String age )
    {
        super( "Peter", new ArrayList<>() );

        this.age = age;
    }
}

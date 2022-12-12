package com.example.bug;

import java.beans.ConstructorProperties;

import lombok.Getter;

@Getter
public class Peter
    extends AbstractAuthor
{
    private String age;

    @ConstructorProperties( "age" )
    public Peter( String age )
    {
        super( "Peter" );

        this.age = age;
    }
}

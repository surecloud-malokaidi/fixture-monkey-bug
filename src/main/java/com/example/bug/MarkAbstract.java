package com.example.bug;

import java.beans.ConstructorProperties;

public abstract class MarkAbstract
    extends AbstractAuthor
{
    @ConstructorProperties( "name" )
    public MarkAbstract( String name )
    {
        super( name );
    }
}

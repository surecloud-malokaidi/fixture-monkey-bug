package com.example.bug;

import java.beans.ConstructorProperties;

public class John
    extends AbstractAuthor
{
    @ConstructorProperties( "name" )
    public John( String name )
    {
        super( name );
    }
}

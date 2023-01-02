package com.example.bug;

import java.beans.ConstructorProperties;

public class Mark
    extends MarkAbstract
{
    @ConstructorProperties( "name" )
    public Mark( String name )
    {
        super( name );
    }
}

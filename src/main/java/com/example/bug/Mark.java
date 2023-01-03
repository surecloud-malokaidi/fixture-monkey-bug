package com.example.bug;

import java.beans.ConstructorProperties;
import java.util.List;

public class Mark
    extends MarkAbstract
{
    @ConstructorProperties( { "name", "authors" } )
    public Mark( String name, List<AbstractAuthor> authors )
    {
        super( name, authors );
    }
}

package com.example.bug;

import java.beans.ConstructorProperties;
import java.util.List;

public class John
    extends AbstractAuthor
{
    @ConstructorProperties( { "name", "authors" } )
    public John( String name, List<AbstractAuthor> authors )
    {
        super( name, authors );
    }
}

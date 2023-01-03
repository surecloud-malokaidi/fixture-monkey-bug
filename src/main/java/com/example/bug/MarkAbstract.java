package com.example.bug;

import java.beans.ConstructorProperties;
import java.util.List;

public abstract class MarkAbstract
    extends AbstractAuthor
{
    @ConstructorProperties( { "name", "authors" } )
    protected MarkAbstract( String name, List<AbstractAuthor> authors  )
    {
        super( name, authors );
    }
}

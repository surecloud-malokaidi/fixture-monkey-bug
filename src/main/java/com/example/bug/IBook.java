package com.example.bug;

import java.util.List;

public class IBook
{
    String author;

    List<IAuthor> authors;

    public List<IAuthor> getAuthors()
    {
        return authors;
    }

    public void setAuthors( List<IAuthor> authors )
    {
        this.authors = authors;
    }

    public String getAuthor()
    {
        return author;
    }

    public void setAuthor( String author )
    {
        this.author = author;
    }
}

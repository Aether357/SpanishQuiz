package com.example.josh.spanishquiz;

import java.io.Serializable;

/**
 * Created by Josh on 9/25/2015.
 *
 * wrapper for a flashcard
 * contains two strings - english/spanish
 */
public class FlashCard implements Serializable
{
    private String spanish;
    private String english;
    private boolean isSpanish;

    FlashCard( String spanish, String english )
    {
        this.spanish = spanish;
        this.english = english;
        this.isSpanish = false;
    }

    public String getEnglishText()
    {
        return english;
    }

    public String getSpanishText()
    {
        return spanish;
    }

    public boolean isSpanishText()
    {
        return isSpanish;
    }

    public void setIsSpanish( boolean isSpanish )
    {
        this.isSpanish = isSpanish;
    }
}

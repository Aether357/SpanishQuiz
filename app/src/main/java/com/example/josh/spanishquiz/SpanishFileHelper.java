package com.example.josh.spanishquiz;

import android.net.Uri;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Josh on 9/26/2015.
 *
 * Helper class to parse the data files that store all the spanish words I need to learn
 *
 * // Later // Maybe make it so checks online for updated wordbanks and updates via those
 * Maybe I can sync up with google drive or something?
 *
 */
public class SpanishFileHelper
{
    private final String WORDBANK_PATH = "android.resource://SpanishQuiz/wordbanks/";
    private ArrayList<String> wordBankFiles;

    SpanishFileHelper()
    {
        wordBankFiles = new ArrayList<String>();

        //get all filenames
        File file = new File( WORDBANK_PATH );

        if( file.exists() )
        {
            File[] files = file.listFiles();
            for( File f: files )
            {
                wordBankFiles.add( f.getName() );
            }
        }

        if( wordBankFiles.isEmpty() )
        {
            wordBankFiles.add( "No Wordbanks Found! ");
        }

    }

    public ArrayList<String> getAllFileNames()
    {
        return wordBankFiles;
    }

    public ArrayList<FlashCard> getAllFlashCards( String fileName )
    {
        ArrayList<FlashCard> flashCards = new ArrayList<FlashCard>();



        return flashCards;
    }
}

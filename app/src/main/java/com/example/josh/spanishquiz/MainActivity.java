package com.example.josh.spanishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ArrayList<FlashCard> flashCards;
    private TextView cardText;
    private FlashCard currentCard;
    private Button restartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button flipButton = (Button) findViewById( R.id.flipButton );
        Button nextButton = (Button) findViewById( R.id.nextButton);
        restartButton = (Button) findViewById( R.id.restartButton);

        restart();

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restart();
            }
        });

        flipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flipCard();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                displayNewCard();
            }
        });
    }

    private void restart()
    {
        restartButton.setVisibility(View.INVISIBLE);
        ArrayList<FlashCard> flashCardsT = (ArrayList<FlashCard>) getIntent().getSerializableExtra("flashcards");

        flashCards = new ArrayList<FlashCard>();
        for( FlashCard flashCard : flashCardsT )
        {
            flashCards.add( new FlashCard(flashCard) );
        }
        
        cardText = (TextView) findViewById(R.id.cardText);
        currentCard = null;

        displayNewCard();
    }

    private void displayNewCard()
    {
        if( flashCards.size() == 0 )
        {
            currentCard = null;
            finished();
            return;
        }

        Random rand = new Random();
        int index = rand.nextInt( flashCards.size() );
        currentCard = flashCards.get( index );
        flashCards.remove( index );

        int choice = rand.nextInt(2);
        if( choice == 1 )
        {
            currentCard.setIsSpanish(true);
        }
        else
        {
            currentCard.setIsSpanish(false);
        }

        updateCurrentCard();
    }

    private void finished()
    {
        cardText.setText( "Finished" );
        restartButton.setVisibility(View.VISIBLE);

    }

    private void flipCard()
    {
        if( currentCard != null )
        {
            if( currentCard.isSpanishText() )
            {
                currentCard.setIsSpanish( false );
            }
            else
            {
                currentCard.setIsSpanish( true );
            }

            updateCurrentCard();
        }
    }

    private void updateCurrentCard()
    {
        if( currentCard.isSpanishText() )
        {
            cardText.setText( currentCard.getSpanishText() );
        }
        else
        {
            cardText.setText( currentCard.getEnglishText() );
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        /*
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
        */
        return true;
    }
}

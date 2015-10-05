package com.example.josh.spanishquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SelectionActivity extends AppCompatActivity {

    private String[] items;
    private ListView listView;
    private ArrayList<Integer> SelectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        ArrayList<String> wordBankFiles = new ArrayList<>();
        Field[] fields = R.raw.class.getFields();
        //get all filenames
        for( Field field: fields )
        {
            wordBankFiles.add( field.getName() );
        }
        Collections.sort(wordBankFiles, new Comparator<String>() {
            @Override
            public int compare(String lhs, String rhs) {
                int lhi = Integer.parseInt(lhs.substring(lhs.length() - 1, lhs.length()));
                int rhi = Integer.parseInt(rhs.substring(rhs.length() - 1, rhs.length()));

                if( lhi < rhi )
                {
                    return -1;
                }
                else if( lhi == rhi )
                {
                    return 0;
                }
                else
                {
                    return 1;
                }
            }
        });

        String[] selectedItemsarray = wordBankFiles.toArray(new String[wordBankFiles.size()]);

        //create the listview
        final WordBankAdapter adapter = new WordBankAdapter(this, R.layout.listlayout,
                                R.id.textView3, selectedItemsarray);

        //get reference to listview
        listView = (ListView) findViewById( R.id.listView );
        listView.setAdapter(adapter);


        Button beginButton = (Button) findViewById( R.id.flashcardButton );
        beginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                // load up flashcards based on the SelectedItems
                ArrayList<FlashCard> flashCards = new ArrayList<>();
                for (String string: adapter.getSelectedWordbanks() )
                {
                    loadFlashCards( flashCards, string );
                }
                if( flashCards.size() > 0 )
                {
                    //create all the flashcards
                    Intent i = new Intent(getApplicationContext(), MainActivity.class);

                    i.putExtra("flashcards", flashCards);
                    startActivity(i);
                }
            }
        });




    }

    private void loadFlashCards( ArrayList<FlashCard> cards, String wordbank )
    {
        Field[] fields = R.raw.class.getFields();

        //get all filenames
        for( Field field: fields )
        {
            if( field.getName().equals(wordbank) )
            {
                try {
                    ArrayList<FlashCard> addCards = getAllFlashCards( field.getInt( field ));
                    cards.addAll( addCards );
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private ArrayList<FlashCard> getAllFlashCards( int res )
    {
        ArrayList<FlashCard> flashCards = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader( new InputStreamReader( getResources().openRawResource(res) ));
            String line;

            while((line = br.readLine()) != null)
            {
                String[] s = line.split(":");
                if( s.length == 2 )
                {
                    flashCards.add( new FlashCard(s[0].trim(), s[1].trim()));
                }
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return flashCards;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
       /* int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);*/
        return true;
    }
}

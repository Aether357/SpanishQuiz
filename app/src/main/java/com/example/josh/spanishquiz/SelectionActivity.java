package com.example.josh.spanishquiz;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class SelectionActivity extends AppCompatActivity {

    private String[] items;
    private ListView listView;
    private ArrayList<Integer> SelectedItems;
    private SpanishFileHelper fileHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection);

        fileHelper = new SpanishFileHelper();
        SelectedItems = new ArrayList<Integer>();

        //create the listview
        items = new String[] { "Test1", "Test2", "Test3"};
        ListAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_selection,
                                R.id.textView2, items);

        //get reference to listview
        listView = (ListView) findViewById( R.id.listView );
        listView.setAdapter(adapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if( SelectedItems.contains( position ) )
                {
                    view.setBackgroundColor( Color.WHITE );
                    SelectedItems.remove( new Integer( position ) );
                }
                else
                {
                    view.setBackgroundColor(Color.GREEN);
                    SelectedItems.add( position );
                }
            }
        });

        /*
        Button beginButton = (Button) findViewById( R.id.button2 );
        beginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // load up flashcards based on the SelectedItems
                if( SelectedItems.size() > 0 )
                {
                    //have items to load, try and get them

                }
            }
        });*/




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_selection, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

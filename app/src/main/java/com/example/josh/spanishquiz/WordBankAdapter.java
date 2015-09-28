package com.example.josh.spanishquiz;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Josh on 9/27/2015.
 *
 * Adapter created for the listlayout because I like the checkboxes.
 */
public class WordBankAdapter extends ArrayAdapter<String>
{
    private ArrayList<String> selectedWordbanks;

    public WordBankAdapter(Context context, int resource)
    {
        super(context, resource);
        selectedWordbanks = new ArrayList<>();
    }

    public WordBankAdapter( Context context, int resource, List<String> words)
    {
        super( context, resource, words );
        selectedWordbanks = new ArrayList<>();
    }

    public WordBankAdapter(Context context, int resource, int textViewResourceId,
                        String[] objects)
    {
        super(context, resource, textViewResourceId, Arrays.asList(objects));
        selectedWordbanks = new ArrayList<>();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        View v = convertView;

        if (v == null)
        {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.listlayout, null);
        }

        String p = getItem(position);

        if (p != null)
        {
            TextView text = (TextView) v.findViewById(R.id.textView3);
            CheckBox checkBox = (CheckBox) v.findViewById(R.id.selectBox);

            if (text != null)
            {
                text.setText(p);
            }

            if (checkBox != null)
            {
                checkBox.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)
                    {
                        RelativeLayout rel = (RelativeLayout) v.getParent();
                        TextView textfield = (TextView) rel.findViewById(R.id.textView3);
                        CheckBox check = (CheckBox) v;

                        if( check.isChecked() )
                        {
                            textfield.setTextColor(Color.BLUE);
                            if(!selectedWordbanks.contains(textfield.getText().toString()))
                            {
                                selectedWordbanks.add(textfield.getText().toString());
                            }
                        }
                        else
                        {
                            textfield.setTextColor(Color.BLACK);
                            if(selectedWordbanks.contains(textfield.getText().toString()))
                            {
                                selectedWordbanks.remove(textfield.getText().toString());
                            }
                        }
                    }
                });
            }


        }

        return v;
    }

    public ArrayList<String> getSelectedWordbanks()
    {
        return selectedWordbanks;
    }

}

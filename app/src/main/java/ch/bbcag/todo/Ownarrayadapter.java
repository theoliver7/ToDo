package ch.bbcag.todo;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zfehrn on 17.06.2015.
 */
public class Ownarrayadapter extends ArrayAdapter<String> {
    LayoutInflater mInflater;
    FragmentManager fragmentManager;

    public Ownarrayadapter(Context context, List<String> items, LayoutInflater inflater, FragmentManager fragmentManager) {
        super(context, -1, items);
        this.mInflater = inflater;
        this.fragmentManager = fragmentManager;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.aufgabenlistview_layout, null);

        final TextView aufgabeTextView = (TextView) convertView.findViewById(R.id.aufgabe);
        aufgabeTextView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String selectedFromList = (String) (aufgabeTextView.getText());
                Bundle bundle = new Bundle();
                bundle.putString("Aufgabe", selectedFromList);
                Fragment myFragment = new AufgabeAnsicht_Fragment();
                myFragment.setArguments(bundle);
                // update the main content by replacing fragments
                fragmentManager.beginTransaction()
                        .replace(R.id.container, myFragment)
                        .commit();

            }
        });

        final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.aufgabecheckbox);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    aufgabeTextView.setTextColor(Color.rgb(163, 163, 163));
                }
                else {
                    aufgabeTextView.setTextColor(Color.rgb(0, 0, 0));
                }
            }
        });

        final String aufgabe = this.getItem(pos);

        ((TextView) convertView.findViewById(R.id.aufgabe)).setText(aufgabe);

        return convertView;
    }
}

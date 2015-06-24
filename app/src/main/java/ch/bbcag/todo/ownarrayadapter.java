package ch.bbcag.todo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zfehrn on 17.06.2015.
 */
public class Ownarrayadapter extends ArrayAdapter<String> {

    LayoutInflater mInflater;

    public Ownarrayadapter(Context context, List<String> items, LayoutInflater inflater) {
        super(context, -1, items);
        this.mInflater = inflater;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.aufgabenlistview_layout, null);
        final String aufgabe = this.getItem(pos);

        ((TextView) convertView.findViewById(R.id.aufgabe)).setText(aufgabe);
        return convertView;
    }
}

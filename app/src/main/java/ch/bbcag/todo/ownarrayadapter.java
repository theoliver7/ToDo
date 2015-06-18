package ch.berufsbildungscenter.train_alert;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ch.bbcag.todo.R;
import ch.bbcag.todo.database.Aufgabe;

/**
 * Created by zfehrn on 17.06.2015.
 */
public class ownarrayadapter {

    LayoutInflater mInflater;
    private List<Aufgabe> mItems;

    public ownarrayadapter(Context context, List<Aufgabe> items, LayoutInflater inflater) {
        this.mItems = items;
        this.mInflater = inflater;
    }

    public View getView(int pos, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(R.layout.aufgabenlistview_layout, null);
        final Aufgabe aufgabe = (Aufgabe) mItems.get(pos);

        ((TextView) convertView.findViewById(R.id.aufgabe)).setText(aufgabe.getAufgabe());
        return convertView;
    }
}

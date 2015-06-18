package ch.bbcag.todo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import ch.bbcag.todo.database.Aufgabe;

/**
 * Created by zascho on 17.06.2015.
 */
public class Listen_Details_Fragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.listen_details_layout, container, false);
        return myView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plus, menu);
    }

    private void addAufgabetolist() {


        ListView list = (ListView) myView.findViewById(R.id.listView);
        ArrayList<Aufgabe> aufgabe = new ArrayList<Aufgabe>();

        list.setAdapter(new ch.berufsbildungscenter.train_alert.ownarrayadapter, aufgabe, getLayoutInflater());

        list.setAdapter(list);
    }




}

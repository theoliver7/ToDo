package ch.bbcag.todo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        addAufgabetolist();
        return myView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plus_aufgabe, menu);
    }

    private void addAufgabetolist() {


        ListView list = (ListView) myView.findViewById(R.id.listView);
        ArrayList<Aufgabe> aufgabe = new ArrayList<Aufgabe>();
        Aufgabe test = new Aufgabe();
        test.setAufgabe("hello");
        aufgabe.add(test);
        list.setAdapter(new Ownarrayadapter(this.getActivity().getApplicationContext(), aufgabe, this.getActivity().getLayoutInflater()));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.plus) {
            Fragment myFragment = null;
            myFragment = new Aufgaben_erstellen_Fragment();

            // update the main content by replacing fragments
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.container, myFragment)
                    .commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



}

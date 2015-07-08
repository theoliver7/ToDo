package ch.bbcag.todo.Fragments;


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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bbcag.todo.Database.AufgabenDAO;
import ch.bbcag.todo.Database.ToDoListDAO;
import ch.bbcag.todo.Ownarrayadapter;
import ch.bbcag.todo.R;

/**
 * Created by zascho on 17.06.2015.
 */
public class Listen_Details_Fragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.listen_details_layout, container, false);
        AufgabenDAO aufgabenTitel = new AufgabenDAO(getActivity());
        ToDoListDAO toDoListDAO = new ToDoListDAO(getActivity());
        addAufgabetolist(aufgabenTitel.getAlleAufgabenfromList(toDoListDAO.primaryKeyAuslesen(getListentitel())));




        TextView listennametextview = (TextView) myView.findViewById(R.id.Listenname);
        listennametextview.setText(getListentitel());

        return myView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plus_aufgabe, menu);
        ToDoListDAO toDoListDAO = new ToDoListDAO(getActivity());
        int fav = toDoListDAO.favoritenueberpruefen(getListentitel());

        MenuItem favorit = (MenuItem) menu.findItem(R.id.favoriten);

        if (fav == 1) {
            favorit.setIcon(R.mipmap.full_star);
        } else {
            favorit.setIcon(R.mipmap.stern);
        }
    }

    private void addAufgabetolist(ArrayList aufgabe) {
        ListView list = (ListView) myView.findViewById(R.id.listView);
        Ownarrayadapter ownarrayadapter = new Ownarrayadapter(this.getActivity().getApplicationContext(), aufgabe, this.getActivity().getLayoutInflater(), this.getActivity().getSupportFragmentManager());
        list.setAdapter(ownarrayadapter);
        if (aufgabe.size() == 0) {
            TextView empty = (TextView) myView.findViewById(R.id.keineAufgaben);
            empty.setVisibility(View.VISIBLE);
            Button aufgabenloeschen = (Button) myView.findViewById(R.id.aufgabenloeschen);
            aufgabenloeschen.setVisibility(View.INVISIBLE);
        }
        final TextView aufgabeloeschen = (TextView) myView.findViewById(R.id.aufgabenloeschen);
        aufgabeloeschen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AufgabenDAO db = new AufgabenDAO(getActivity().getApplicationContext());
                db.deleteAufgabe();
                Bundle bundle = new Bundle();
                TextView listenname = (TextView) myView.findViewById(R.id.Listenname);
                bundle.putString("Liste", listenname.getText().toString());
                Fragment myFragment = new Listen_Details_Fragment();
                myFragment.setArguments(bundle);
                // update the main content by replacing fragments
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.container, myFragment)
                        .commit();

            }
        });


    }

    public String getListentitel() {
        Bundle bundle = this.getArguments();
        String listenname = bundle.getString("Liste");
        return listenname;
    }

}

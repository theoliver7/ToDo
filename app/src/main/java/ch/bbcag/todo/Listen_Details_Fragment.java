package ch.bbcag.todo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ch.bbcag.todo.database.AufgabenDAO;
import ch.bbcag.todo.database.ToDoListDAO;

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
        ToDoListDAO foreignkey = new ToDoListDAO(getActivity());
        addAufgabetolist(aufgabenTitel.getAlleAufgabenfromList(foreignkey.primaryKeyAuslesen(getListentitel())));
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
    }

    private void addAufgabetolist(ArrayList aufgabenTitel) {
        ListView list = (ListView) myView.findViewById(R.id.listView);
        Ownarrayadapter ownarrayadapter = new Ownarrayadapter(this.getActivity().getApplicationContext(), aufgabenTitel, this.getActivity().getLayoutInflater(), this.getActivity().getSupportFragmentManager());
        list.setAdapter(ownarrayadapter);


        final ListView aufgabenListe = (ListView) myView.findViewById(R.id.listView);
        aufgabenListe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });

    }

    private String getListentitel() {
        Bundle bundle = this.getArguments();
        String listenname = bundle.getString("Liste");
        return listenname;
    }

}

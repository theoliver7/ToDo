package ch.bbcag.todo.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.todo.Database.ToDoList;
import ch.bbcag.todo.Database.ToDoListDAO;
import ch.bbcag.todo.R;

/**
 * Created by zascho on 17.06.2015.
 */
public class Main_Fragment extends Fragment {
    private View myView;
    private ArrayAdapter todolisteadapter;
    private ListView todoliste;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.main_layout, container, false);
        getlisten();














        return myView;
    }

    public void getlisten() {
        todoliste = (ListView) myView.findViewById(R.id.todolisten);
        List<ToDoList> toDoLists = new ArrayList<ToDoList>();
        todolisteadapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1);
        ToDoListDAO db = new ToDoListDAO(getActivity());
        db.getAllListen();
        toDoLists = db.getAllListen();
        for (int i = 0; i < toDoLists.size(); i++) {
            todolisteadapter.add(toDoLists.get(i).getListenname());
        }
        todoliste.setAdapter(todolisteadapter);
        if (toDoLists.size() == 0) {
            TextView meldung = (TextView) myView.findViewById(R.id.keineListen);
            meldung.setVisibility(View.VISIBLE);
        }

        todoliste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long l) {
                String selectedFromList = (String) (todoliste.getItemAtPosition(position));
                Bundle bundle = new Bundle();
                bundle.putString("Liste", selectedFromList);
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


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.plus_liste, menu);

    }
}

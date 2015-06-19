package ch.bbcag.todo;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by zascho on 17.06.2015.
 */
public class Main_Fragment extends Fragment {
    private View myView;
    private ArrayAdapter todoliste;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.main_layout, container, false);
        addBadisToList();
        return myView;
    }

    private void addBadisToList() {
        ListView badis = (ListView) myView.findViewById((R.id.todolisten));
        todoliste = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_list_item_1);
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        todoliste.add(getString(R.string.verwalten));
        badis.setAdapter(todoliste);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.plus_liste, menu);
    }
}

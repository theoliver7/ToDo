package ch.bbcag.todo;

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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import ch.bbcag.todo.database.Aufgabe;
import ch.bbcag.todo.database.AufgabenDAO;

/**
 * Created by zascho on 24.06.2015.
 */
public class AufgabeAnsicht_Fragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.aufgabe_layout, container, false);
        AufgabenDAO aufgabenDAO = new AufgabenDAO(getActivity());
        informationenSetzen(aufgabenDAO.getAllInformationForTask("Peter"));
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

    public void informationenSetzen(Aufgabe aufgabe) {
        final TextView titel = (TextView) myView.findViewById(R.id.aufgabentitel);
        TextView beschreibung = (TextView) myView.findViewById(R.id.detailAnsichtBeschreibung);
        ImageView bild = (ImageView) myView.findViewById(R.id.imageView);
        titel.setText(aufgabe.getAufgabe());
        beschreibung.setText(aufgabe.getBeschreibung());
        bild.setImageURI(aufgabe.getBild_uri());
        CheckBox checkBox = (CheckBox) myView.findViewById(R.id.taskbeenden);
        checkBox.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            titel.setText("OK");
                                        }
                                    }
        );


    }

}

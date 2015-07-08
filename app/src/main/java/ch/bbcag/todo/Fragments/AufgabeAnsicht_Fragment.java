package ch.bbcag.todo.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import ch.bbcag.todo.Database.Aufgabe;
import ch.bbcag.todo.Database.AufgabenDAO;
import ch.bbcag.todo.Database.ToDoListDAO;
import ch.bbcag.todo.R;

/**
 * Created by zascho on 24.06.2015.
 */
public class AufgabeAnsicht_Fragment extends Fragment {
    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.aufgabe_ansicht_layout, container, false);
        AufgabenDAO aufgabenDAO = new AufgabenDAO(getActivity());
        informationenSetzen(aufgabenDAO.getAllInformationForTask(getAufgabentitel()));
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
        inflater.inflate(R.menu.global, menu);
    }

    public void informationenSetzen(Aufgabe aufgabe) {
         TextView titel = (TextView) myView.findViewById(R.id.aufgabentitel);
         TextView beschreibung = (TextView) myView.findViewById(R.id.detailAnsichtBeschreibung);
         TextView wichtigkeit = (TextView) myView.findViewById(R.id.wichtigkeitText);
         ImageView imgview = (ImageView) myView.findViewById(R.id.imageView);

        titel.setText(aufgabe.getAufgabe());
        beschreibung.setText(aufgabe.getBeschreibung());
        wichtigkeit.setText(wichtigkeitErmitteln(aufgabe.getWichtigkeit()));


        if(aufgabe.getBild_uri() != null){
            imgview.setImageURI(aufgabe.getBild_uri());


        } else {
            imgview.setImageResource(R.mipmap.todo);
        }




        Button button = (Button) myView.findViewById(R.id.done);
        button.setOnClickListener(new View.OnClickListener() {
                                      @Override
                                      public void onClick(View view) {
                                          Fragment myFragment = new Listen_Details_Fragment();
                                          Bundle bundle = new Bundle();
                                          ToDoListDAO db = new ToDoListDAO(getActivity());
                                          AufgabenDAO dao = new AufgabenDAO(getActivity());

                                          dao.aufgabeAlsErledigtMarkieren(getAufgabentitel());
                                          String listenname = getAufgabentitel();
                                          int debug= dao.foreignKeyAuslesen(listenname);
                                          String liste = db.nameAuslesen(debug);
                                          bundle.putString("Liste", liste);
                                          myFragment.setArguments(bundle);

                                          FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                                          fragmentManager.beginTransaction()
                                                  .replace(R.id.container, myFragment)
                                                  .commit();
                                      }
                                  }
        );
    }
    private String getAufgabentitel() {
        Bundle bundle = this.getArguments();
        String listenname = bundle.getString("Aufgabe");
        return listenname;
    }

    private String wichtigkeitErmitteln(long wichtigkeitsID) {
        if (wichtigkeitsID == -1) {
            return "Wichtig";
        }
        if (wichtigkeitsID == 2131558502) {
            return "Normal";
        }
        if (wichtigkeitsID == 2131558503) {
            return "Unwichtig";
        } else {
            return "Sehr wichtig";
        }

    }
}

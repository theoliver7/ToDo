package ch.bbcag.todo;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.sql.SQLException;
import java.util.Calendar;

import ch.bbcag.todo.database.Aufgabe;
import ch.bbcag.todo.database.AufgabenDAO;

/**
 * Created by zascho on 17.06.2015.
 */
public class Aufgaben_erstellen_Fragment extends Fragment {
    private View myView;
    private EditText aufgabename;
    private EditText beschreibung;
    private Spinner liste;
    private RadioGroup wichtigkeit;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.aufgaben_erstellen_layout, container, false);
        createSpinner(myView);

        setHasOptionsMenu(true);

        final Button time = (Button) myView.findViewById(R.id.time);
        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        time.setText("" + hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                mTimePicker.setTitle("Zeit auswählen");
                mTimePicker.show();
            }
        });

        final Button date = (Button) myView.findViewById(R.id.date);
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        date.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, 2015, 0, 1);
                dialog.setTitle("Datum auswählen");
                dialog.show();
            }
        });

        final Button aufgabeerstellenBtn = (Button) myView.findViewById(R.id.date);
        aufgabeerstellenBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                aufgabename = (EditText) myView.findViewById(R.id.aufgabename);
                beschreibung = (EditText) myView.findViewById(R.id.aufgabebeschreibung);
                liste = (Spinner) myView.findViewById(R.id.ausgewählteliste);
                wichtigkeit = (RadioGroup) myView.findViewById(R.id.wichtigkeit);

                Aufgabe newAufgabe = new Aufgabe();
                AufgabenDAO database = new AufgabenDAO(getActivity().getApplicationContext());
                newAufgabe.setAufgabe(aufgabename.getText().toString());
                newAufgabe.setBeschreibung(beschreibung.getText().toString());
                newAufgabe.setListe(liste.getSelectedItem().toString());
                newAufgabe.setWichtigkeit(wichtigkeit.getCheckedRadioButtonId());
                try {
                    database.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                database.aufgabeerstellen(newAufgabe);
                database.close();
            }
        });


        return myView;

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.aufgabe_erstellen_bar, menu);
    }

    private void createSpinner(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.ausgewählteliste);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.test_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }

}

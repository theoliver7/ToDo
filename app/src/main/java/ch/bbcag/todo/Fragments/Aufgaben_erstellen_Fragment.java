package ch.bbcag.todo.Fragments;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ch.bbcag.todo.Alarm.AlarmSetter;
import ch.bbcag.todo.Alarm.AlertReceiver;
import ch.bbcag.todo.Camera;
import ch.bbcag.todo.Database.Aufgabe;
import ch.bbcag.todo.Database.AufgabenDAO;
import ch.bbcag.todo.Database.ToDoList;
import ch.bbcag.todo.Database.ToDoListDAO;
import ch.bbcag.todo.R;

/**
 * Created by zascho on 17.06.2015.
 */
public class Aufgaben_erstellen_Fragment extends Fragment {
    private View myView;
    private EditText aufgabename;
    private EditText beschreibung;
    private Spinner liste;
    private RadioGroup wichtigkeit;
    private Camera camera = new Camera();
    private int minute, hour, day, month, year;
    private final Calendar calendar = Calendar.getInstance();



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.aufgaben_erstellen_layout, container, false);
        createSpinner(myView);

        setHasOptionsMenu(true);

        final Button time = (Button) myView.findViewById(R.id.time);
        time.setText("" + calendar.get(Calendar.HOUR_OF_DAY) + ":" + calendar.get(Calendar.MINUTE));
        time.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click

                TimePickerDialog mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute_) {
                        time.setText("" + hourOfDay + ":" + minute_);
                        hour = hourOfDay;
                        minute = minute_;
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);

                mTimePicker.setTitle("Zeit auswählen");
                mTimePicker.show();
            }
        });

        final Button date = (Button) myView.findViewById(R.id.date);
        date.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + (calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.YEAR));
        date.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                DatePickerDialog dialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year_, int monthOfYear, int dayOfMonth) {
                        date.setText("" + dayOfMonth + "/" + (monthOfYear + 1) + "/" + year_);
                        day = dayOfMonth;
                        month = monthOfYear;
                        year = year_;
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.setTitle("Datum auswählen");
                dialog.show();
            }
        });
        final Button alarmSetzen = (Button) myView.findViewById(R.id.aufgabeerstellen);
        alarmSetzen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                aufgabename = (EditText) myView.findViewById(R.id.aufgabename);
                beschreibung = (EditText) myView.findViewById(R.id.aufgabebeschreibung);
                liste = (Spinner) myView.findViewById(R.id.ausgewählteliste);
                wichtigkeit = (RadioGroup) myView.findViewById(R.id.wichtigkeit);



                if(TextUtils.isEmpty(aufgabename.getText().toString()) || TextUtils.isEmpty(beschreibung.getText().toString())) {
                    if(TextUtils.isEmpty(aufgabename.getText().toString())){
                        aufgabename.setError("Aufgabe eingeben");

                    }else {
                        beschreibung.setError("Beschreibung hinzufügen");
                    }
                }else {
                    Aufgabe newAufgabe = new Aufgabe();
                    AufgabenDAO database = new AufgabenDAO(getActivity().getApplicationContext());
                    newAufgabe.setAufgabe(aufgabename.getText().toString());
                    newAufgabe.setBeschreibung(beschreibung.getText().toString());
                    try {
                        newAufgabe.setBild_uri(camera.getUriSavedImage());

                    } catch (NullPointerException e) {
                        Log.d("myTag", "GEEEEECAATCHED");
                    }

                    ToDoListDAO db = new ToDoListDAO(getActivity().getApplicationContext());
                    try {
                        db.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    newAufgabe.setListe(db.primaryKeyAuslesen(liste.getSelectedItem().toString()));
                    newAufgabe.setWichtigkeit(wichtigkeit.getCheckedRadioButtonId());

                    database.aufgabeerstellen(newAufgabe);


                    db.close();
                    Toast toast = Toast.makeText(getActivity().getApplicationContext(), "Aufgabe wurde erstellt", Toast.LENGTH_SHORT);
                    toast.show();

                    if (hour != 0 && minute != 0) {
                        if(year == 0 && month==0 && day==0) {
                            year = calendar.get(Calendar.YEAR);
                            month = calendar.get(Calendar.MONTH);
                            day = calendar.get(Calendar.DAY_OF_MONTH);
                        }
                        AlarmSetter alarm = new AlarmSetter(getActivity());
                        alarm.setAlert(getTime(year, month, day, hour, minute), newAufgabe.getAufgabe());

                        Toast.makeText(getActivity().getApplicationContext(), "Alarm wurde gesetzt",
                                Toast.LENGTH_LONG).show();

                    }
                        Bundle bundle = new Bundle();
                        bundle.putString("Liste", liste.getSelectedItem().toString());
                        Fragment myFragment = new Listen_Details_Fragment();
                        myFragment.setArguments(bundle);
                        // update the main content by replacing fragments
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.container, myFragment)
                                .commit();



                }
            }
        });

        return myView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        camera.createCamera(this.getActivity());

        return super.onOptionsItemSelected(item);
    }

    public long getTime(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE, minute);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.DAY_OF_MONTH, day);
        cal.set(Calendar.MONTH, month);
        cal.set(Calendar.YEAR, year);

        long difference = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        return difference;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.aufgabe_erstellen_bar, menu);
    }


    private void createSpinner(View view) {
        List<ToDoList> toDoLists = new ArrayList<ToDoList>();
        List<String> spinnerelemente = new ArrayList<String>();
        ToDoListDAO test = new ToDoListDAO(getActivity());
        try {
            test.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        test.getAllListen();
        toDoLists = test.getAllListen();
        for (int i = 0; i < toDoLists.size(); i++) {
            spinnerelemente.add(toDoLists.get(i).getListenname());
        }
        test.close();
        Spinner s = (Spinner) myView.findViewById(R.id.ausgewählteliste);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, spinnerelemente);
        s.setAdapter(adapter);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        s.setAdapter(adapter);
    }
    @Override
    public void onStop() {
        super.onStop();
    }
}

package ch.bbcag.todo;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by zascho on 17.06.2015.
 */
public class Aufgaben_erstellen_Fragment extends Fragment {
    private View myView;
    int i = 5;
    int minute, hour, day, month, year;
    final Calendar calendar = Calendar.getInstance();

    final static int RQS_1 = 1;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.myView = inflater.inflate(R.layout.aufgaben_erstellen_layout, container, false);
        createSpinner(myView);

        setHasOptionsMenu(true);

        final Button time = (Button) myView.findViewById(R.id.time);
        time.setText("" + calendar.get(Calendar.HOUR)+ ":" +calendar.get(Calendar.MINUTE));
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
                }, calendar.get(Calendar.HOUR), calendar.get(Calendar.MINUTE), true);

               // Calendar.HOUR_OF_DAY, Calendar.MINUTE, true);
                mTimePicker.setTitle("Zeit auswählen");
                mTimePicker.show();
            }
        });

        final Button date = (Button) myView.findViewById(R.id.date);
        date.setText("" + calendar.get(Calendar.DAY_OF_MONTH) + "/" + calendar.get(Calendar.MONTH) + "/" + calendar.get(Calendar.YEAR));
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
                },calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                dialog.setTitle("Datum auswählen");
                dialog.show();
            }
        });
        final Button alarmSetzen =  (Button) myView.findViewById(R.id.aufgabeerstellen);
        alarmSetzen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
               setAlert();
                Toast.makeText(getActivity().getApplicationContext(), "Alarm wurde gesetzt",
                        Toast.LENGTH_LONG).show();            }
        });

        return myView;
    }

    public long getTime(int year, int month, int day, int hour, int minute) {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MINUTE,minute);
        cal.set(Calendar.HOUR_OF_DAY,hour);
        cal.set(Calendar.DAY_OF_MONTH,day);
        cal.set(Calendar.MONTH,month);
        cal.set(Calendar.YEAR,year);

        long difference = cal.getTimeInMillis() - Calendar.getInstance().getTimeInMillis();
        return difference;
    }
    public void setAlert(){
        long test = getTime(this.year, this.month,this.day,this.hour,this.minute);
        Intent intent = new Intent(getActivity(), AlertReceiver.class);
        int id_ =(int) System.currentTimeMillis();
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getActivity().getApplicationContext(), id_, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + test, pendingIntent);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        menu.clear();
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.aufgabe_erstellen_bar, menu);
    }


    private void createSpinner(View view) {
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity().getBaseContext(),
                R.array.test_array, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
    }



}

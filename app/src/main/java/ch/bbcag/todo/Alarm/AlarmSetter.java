package ch.bbcag.todo.Alarm;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import ch.bbcag.todo.Database.AufgabenDAO;

/**
 * Created by zjorgm on 24.06.2015.
 */
public class AlarmSetter {
    Activity activity;


    public AlarmSetter(Activity activity){

        this.activity = activity;
    }


    public void setAlert(long difference, String aufgabenname){
        AufgabenDAO aufgabenDAO = new AufgabenDAO(activity);
        aufgabenDAO.getAllInformationForTask(aufgabenname);

        Intent intent = new Intent(activity, AlertReceiver.class);
        intent.putExtra("aufgabenname",aufgabenDAO.getAllInformationForTask(aufgabenname).getAufgabe());
        intent.putExtra("beschreibung",aufgabenDAO.getAllInformationForTask(aufgabenname).getBeschreibung());
        int id_ = (int) System.currentTimeMillis();

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.activity.getApplicationContext(), id_, intent, 0);

        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + difference, pendingIntent);
    }
}

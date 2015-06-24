package ch.bbcag.todo;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by zjorgm on 24.06.2015.
 */
public class AlarmSetter {
    long difference;
    Activity activity;


    public AlarmSetter(long difference, Activity activity){
        this.activity = activity;
        this.difference = difference;
    }


    public void setAlert(){
        Intent intent = new Intent(activity, AlertReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                activity.getApplicationContext(), 234324243, intent, 0);
        AlarmManager alarmManager = (AlarmManager) activity.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()
                + difference, pendingIntent);
    }
}

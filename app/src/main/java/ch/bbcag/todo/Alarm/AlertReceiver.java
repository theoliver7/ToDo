package ch.bbcag.todo.Alarm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import ch.bbcag.todo.Database.Aufgabe;
import ch.bbcag.todo.MainActivity;
import ch.bbcag.todo.R;

/**
 * Created by zjorgm on 19.06.2015.
 */
public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context k1, Intent k2) {
        // TODO Auto-generated method stub

        createNotification(k1, k2.getStringExtra("aufgabenname"),k2.getStringExtra("aufgabenname"), k2.getStringExtra("beschreibung"));
    }

    public void createNotification(Context context, String msg, String msgalert, String msgtext){
        PendingIntent notificIntent = PendingIntent.getActivity(context, 0, new Intent(context, MainActivity.class), 0);

        NotificationCompat.Builder mbuilder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.java)
                .setContentTitle(msg)
                .setTicker(msgalert)
                .setContentText(msgtext);

        mbuilder.setContentIntent(notificIntent);
        mbuilder.setDefaults(NotificationCompat.DEFAULT_SOUND);
        mbuilder.setAutoCancel(true);

        NotificationManager mnotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        mnotificationManager.notify(1,mbuilder.build());

    }
}

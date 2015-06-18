package ch.bbcag.todo.database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by zascho on 18.06.2015.
 */
public class StandortDAO extends DatabaseDAO {
    public StandortDAO(Context context) {
        super(context);
    }

    public long standorthinzufügen(Standort standort) {
        ContentValues values = new ContentValues();
        values.put(StandortSQL.ADRESSE, standort.getAdresse());
        values.put(StandortSQL.LADITUDE, standort.getLaditude());
        values.put(StandortSQL.LONGITUDE, standort.getLongitude());
        // insert row
        long todo_id = db.insert(StandortSQL.TABLE_STANDORT, null, values);
        return todo_id;
    }

}

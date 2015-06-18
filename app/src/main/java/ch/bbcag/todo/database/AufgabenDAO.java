package ch.bbcag.todo.database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by zascho on 17.06.2015.
 */
public class AufgabenDAO extends DatabaseDAO {


    public AufgabenDAO(Context context) {
        super(context);
    }

    /*
      * Creating a todo
      */
    public long aufgabeerstellen(Aufgabe aufgabe) {
        ContentValues values = new ContentValues();
        values.put(AufgabeSQL.AUFGABE_TITEL, aufgabe.getAufgabe());
        values.put(AufgabeSQL.BESCHREIBUNG, aufgabe.getBeschreibung());
        values.put(AufgabeSQL.ERINNERUNGSZEIT, aufgabe.getErinngerungszeit());
        values.put(AufgabeSQL.WICHTIGKEIT, aufgabe.getWichtigkeit());
        // insert row
        long todo_id = db.insert(AufgabeSQL.TABLE_AUFGABEN, null, values);
        return todo_id;
    }
}

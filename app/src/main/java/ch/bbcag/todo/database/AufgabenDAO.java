package ch.bbcag.todo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.sql.SQLException;
import java.util.ArrayList;

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
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues values = new ContentValues();
        values.put(AufgabeSQL.AUFGABE_TITEL, aufgabe.getAufgabe());
        values.put(AufgabeSQL.BESCHREIBUNG, aufgabe.getBeschreibung());
        values.put(AufgabeSQL.BILD_URI, aufgabe.getBild_uri().toString());
        values.put(AufgabeSQL.WICHTIGKEIT, aufgabe.getWichtigkeit());
        values.put(AufgabeSQL.LISTE_ID, aufgabe.getListe());
        long todo_id = db.insert(AufgabeSQL.TABLE_AUFGABEN, null, values);
        return todo_id;
    }

    public ArrayList<Aufgabe> getAlleAufgabenfromList(long foreignkey) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList aufgabenvonListe = new ArrayList<Aufgabe>();
        Cursor cursor = db.rawQuery(AufgabeSQL.getSqlQuerySelectAlleAufgabenFromListe() + foreignkey, null);
        while (cursor.moveToNext()) {
            Aufgabe aufgabe= new Aufgabe();
            aufgabe.setAufgabe(cursor.getString(0));
            aufgabe.setErledigt(cursor.getInt(1));
            aufgabenvonListe.add(aufgabe);
        }
        close();
        return aufgabenvonListe;
    }

    public Aufgabe getAllInformationForTask(String aufgabenTitel) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery(AufgabeSQL.getSqlQuerySelectAllInformationForAufgabe() + "'" + aufgabenTitel + "'", null);
        Aufgabe aufgabe = new Aufgabe();
        while (cursor.moveToNext()) {
            aufgabe.setAufgabe(cursor.getString(1));
            aufgabe.setBeschreibung(cursor.getString(2));
            aufgabe.setBild_uri(Uri.parse(cursor.getString(3)));
            aufgabe.setWichtigkeit(cursor.getInt(4));
        }

        close();
        return aufgabe;
    }

    public void deleteAufgabe(String aufgabenTitel) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.rawQuery(AufgabeSQL.getSqlQueryDeleteAufgabe() + aufgabenTitel, null);
        close();

    }

    public int foreignKeyAuslesen(String aufgabenTitel) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery(AufgabeSQL.getSqlQuerySelectForeignKey() + "'" + aufgabenTitel + "'", null);
        cursor.moveToFirst();
        close();
        return cursor.getInt(0);
    }

    public void aufgabeAlsErledigtMarkieren(String aufgabenTitel) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues werte = new ContentValues();
        werte.put("Erledigt",1);
        db.update(AufgabeSQL.TABLE_AUFGABEN,werte,"Titel=?",new String[]{aufgabenTitel});
        close();

    }

    public void aufgabeAlsNichtErledigtMarkieren(String aufgabenTitel) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ContentValues werte = new ContentValues();
        werte.put("Erledigt",0);
        db.update(AufgabeSQL.TABLE_AUFGABEN, werte, "Titel=?", new String[]{aufgabenTitel});
        close();

    }
}

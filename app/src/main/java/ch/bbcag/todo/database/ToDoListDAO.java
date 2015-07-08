package ch.bbcag.todo.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zascho on 18.06.2015.
 */
public class ToDoListDAO extends DatabaseDAO {

    public ToDoListDAO(Context context) {
        super(context);
    }

    public long listeerstellen(ToDoList toDoList) {
        ContentValues values = new ContentValues();
        values.put(ToDoListSQL.LISTE_NAME, toDoList.getListenname());
        // insert rowlkl
        long todo_id = db.insert(ToDoListSQL.TABLE_LISTEN, null, values);

        return todo_id;
    }

    public List<ToDoList> getFavListen() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<ToDoList> toDoLists = new ArrayList<ToDoList>();
        Cursor cursor = db.query(ToDoListSQL.TABLE_LISTEN, new String[]{"Listenname"}, ToDoListSQL.FAVORITEN+"=?", new String[]{"1"}, null, null, null, null);
        while (cursor.moveToNext()) {
            ToDoList toDoList = new ToDoList();
            toDoList.setListenname(cursor.getString(0));
            toDoLists.add(toDoList);
        }
        close();
        return toDoLists;
    }

    public List<ToDoList> getAllListen() {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        List<ToDoList> toDoLists = new ArrayList<ToDoList>();
        String selectQuery = "SELECT  Listenname FROM " + ToDoListSQL.TABLE_LISTEN;

        Cursor cursor = db.query(ToDoListSQL.TABLE_LISTEN, new String[]{"Listenname"}, null, null, null, null, null, null);
        //Cursor cursor = db.rawQuery(selectQuery,null);
        while (cursor.moveToNext()) {
            ToDoList toDoList = new ToDoList();
            toDoList.setListenname(cursor.getString(0));
            toDoLists.add(toDoList);
        }
        close();
        return toDoLists;
    }

    public int primaryKeyAuslesen(String liste) {
        int primaryKey = 0;
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery(ToDoListSQL.getSqlQuerySelectForeignKey(liste), null);
        cursor.moveToFirst();
        primaryKey = cursor.getInt(0);
        close();
        return primaryKey;
    }


    public String nameAuslesen(int foreignkey) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Cursor cursor = db.rawQuery(ToDoListSQL.getSqlQueryListName(foreignkey), null);
        cursor.moveToFirst();
        String listenname = cursor.getString(0);
        close();
        return listenname;
    }

    public void addListToFavorites(String liste) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        ContentValues werte = new ContentValues();
        werte.put("Favoriten", 1);
        db.update(ToDoListSQL.TABLE_LISTEN,werte,ToDoListSQL.LISTE_NAME+"=?",new String[]{liste});
        close();
    }

    public void listeloeschen(String liste) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.delete(ToDoListSQL.TABLE_LISTEN, ToDoListSQL.LISTE_NAME + "=?", new String[]{liste});
        close();
    }

    public int favoritenueberpruefen(String liste) {
        try {
            open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Cursor cursor = db.rawQuery(ToDoListSQL.getSqlQueryFavorite()+"'"+liste+"'", null);
        cursor.moveToFirst();
        int fav = cursor.getInt(0);
        return fav;
    }
}
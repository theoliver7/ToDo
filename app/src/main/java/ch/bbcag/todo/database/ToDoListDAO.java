package ch.bbcag.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
}
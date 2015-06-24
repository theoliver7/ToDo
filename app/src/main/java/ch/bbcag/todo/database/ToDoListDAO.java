package ch.bbcag.todo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

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
        List<ToDoList> toDoLists = new ArrayList<ToDoList>();
        String selectQuery = "SELECT  Listenname FROM " + ToDoListSQL.TABLE_LISTEN;

        Cursor cursor = db.query(ToDoListSQL.TABLE_LISTEN, new String[]{"Listenname"}, null, null, null, null, null, null);
        //Cursor cursor = db.rawQuery(selectQuery,null);
        Log.e("Hello", cursor.toString());
        while (cursor.moveToNext()) {
            ToDoList toDoList = new ToDoList();
            toDoList.setListenname(cursor.getString(0));
            toDoLists.add(toDoList);
        }
        return toDoLists;
    }

    public long foreignKeyAuslesen(String liste) {
        db.rawQuery(ToDoListSQL.getSqlQuerySelectForeignKey(liste), null);
        return
    }
}
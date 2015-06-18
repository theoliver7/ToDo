package ch.bbcag.todo.database;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by zascho on 18.06.2015.
 */
public class ToDoListDAO extends DatabaseDAO {

    public ToDoListDAO(Context context) {
        super(context);
    }

    public long aufgabeerstellen(ToDoList toDoList) {
        ContentValues values = new ContentValues();
        values.put(ToDoListSQL.LISTE_NAME, toDoList.getListenname());
        // insert rowlkl
        long todo_id = db.insert(ToDoListSQL.TABLE_LISTEN, null, values);
        return todo_id;
    }
}

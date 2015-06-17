package ch.bbcag.todo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zascho on 17.06.2015.
 */
public class ToDoItemDAO {
    private final Database dbManager;
    private SQLiteDatabase db;

    public ToDoItemDAO(Context context) {
        dbManager = new Database(context);
    }

    public void open() {
        db = dbManager.getWritableDatabase();
    }

    public void close() {
        db.close();
    }

    public long insertToDo(ToDoItem toDoItem) {

        return 0;
    }
}

package ch.bbcag.todo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by zascho on 18.06.2015.
 */
public class DatabaseDAO {

    protected DatabaseManager dbHelper;
    protected SQLiteDatabase db;

    public DatabaseDAO(Context context) {
        dbHelper = new DatabaseManager(context);
    }

    public void open() throws SQLException {
        db = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }
}

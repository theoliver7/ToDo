package ch.bbcag.todo.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zascho on 17.06.2015.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ToDo.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(AufgabeSQL.getSqlQueryForCreateTableAufgabe());
        db.execSQL(ToDoListSQL.getSqlQueryForCreateTableToDoList());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + AufgabeSQL.TABLE_AUFGABEN);
        db.execSQL("DROP TABLE IF EXISTS " + ToDoListSQL.TABLE_LISTEN);
        onCreate(db);
    }
}

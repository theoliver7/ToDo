package ch.bbcag.todo.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by zascho on 17.06.2015.
 */
public class DatabaseManager extends SQLiteOpenHelper {
    // Database Version
    private static final int DATABASE_VERSION = 1;
    // Database Name
    private static final String DATABASE_NAME = "ToDo.db";

    public DatabaseManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // creating required tables
        db.execSQL(AufgabeSQL.getSqlQueryForCreateTableAufgabe());
        db.execSQL(ToDoListSQL.getSqlQueryForCreateTableToDoList());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + AufgabeSQL.TABLE_AUFGABEN);
        db.execSQL("DROP TABLE IF EXISTS " + ToDoListSQL.TABLE_LISTEN);
        db.execSQL("DROP TABLE IF EXISTS " + StandortSQL.TABLE_STANDORT);
        // create new tables
        onCreate(db);
    }
}

package ch.bbcag.todo.database;

/**
 * Created by zascho on 18.06.2015.
 */
public class ToDoListSQL {
    public static final String TABLE_LISTEN = "Listen";
    private static final String KEY_ID = "id";
    // TODOLISTE Table - column nmaes
    public static final String LISTE_NAME = "Listenname";
    public static final String STANDORT_ID = "Standort_ID";

    public static String getSqlQueryForCreateTableToDoList() {
        return "CREATE TABLE "
                + TABLE_LISTEN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + LISTE_NAME
                + " TEXT," + STANDORT_ID
                + " INTEGER)";
    }

    public static String getSqlQuerySelectAlleAufgaben() {
        return "SELECT * FROM  "
                + TABLE_LISTEN;
    }

    public static String getSqlQuerySelectForeignKey(String liste) {
        return "SELECT " + KEY_ID + " FROM " + TABLE_LISTEN + " WHERE " + LISTE_NAME + " = '" + liste + "';";
    }
}

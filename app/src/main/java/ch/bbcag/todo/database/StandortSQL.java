package ch.bbcag.todo.Database;

/**
 * Created by zascho on 18.06.2015.
 */
public class StandortSQL {
    public static final String TABLE_STANDORT = "Standort";
    private static final String KEY_ID = "id";
    // AUFGABEN Table - column nmaes
    public static final String ADRESSE = "Adresse";
    public static final String LADITUDE = "Laditude";
    public static final String LONGITUDE = "LONGITUDE";

    public static String getSqlQueryForCreateTableAufgabe() {
        return "CREATE TABLE "
                + TABLE_STANDORT + "(" + KEY_ID + " INTEGER PRIMARY KEY," + ADRESSE
                + " TEXT," + LADITUDE
                + " INTEGER," + LONGITUDE + " INTEGER)";
    }

    public static String getSqlQuerySelectAlleAufgaben() {
        return "SELECT * FROM TABLE "
                + TABLE_STANDORT;
    }
}

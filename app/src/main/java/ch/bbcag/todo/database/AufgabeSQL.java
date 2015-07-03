package ch.bbcag.todo.Database;

/**
 * Created by zascho on 18.06.2015.
 */
public class AufgabeSQL {
    public static final String TABLE_AUFGABEN = "Aufgaben";
    private static final String KEY_ID = "id";
    // AUFGABEN Table - column nmaes
    public static final String AUFGABE_TITEL = "Titel";
    public static final String BESCHREIBUNG = "Beschreibung";
    public static final String BILD_URI = "Bild";
    public static final String WICHTIGKEIT = "Wichtigkeit";
    public static final String ERLEDIGT = "Erledigt";
    public static final String LISTE_ID = "Liste_ID";

    public static String getSqlQueryForCreateTableAufgabe() {
        return "CREATE TABLE "
                + TABLE_AUFGABEN + "(" + KEY_ID + " INTEGER PRIMARY KEY," + AUFGABE_TITEL
                + " TEXT," + BESCHREIBUNG
                + " TEXT," + BILD_URI + " TEXT," + WICHTIGKEIT + " INTEGER," + ERLEDIGT + " INTEGER," + LISTE_ID + " INTEGER)";
    }


    public static String getSqlQuerySelectAlleAufgabenFromListe() {
        return "SELECT " + AUFGABE_TITEL + ","+ERLEDIGT+" FROM  "
                + TABLE_AUFGABEN + " WHERE " + LISTE_ID + " = ";
    }

    public static String getSqlQuerySelectAllInformationForAufgabe() {
        return "SELECT * FROM " + TABLE_AUFGABEN + " WHERE " + AUFGABE_TITEL + " =";
    }

    public static String getSqlQueryDeleteAufgabe() {
        return "DELETE FROM " + TABLE_AUFGABEN + " WHERE " + ERLEDIGT + " = 1";
    }

    public static String getSqlQuerySelectForeignKey() {
        return "SELECT " + KEY_ID + " FROM " + TABLE_AUFGABEN + " WHERE " + AUFGABE_TITEL + " = ";
    }

    public static String getSqlQueryAufgabeErledigt() {
        return "UPDATE " + TABLE_AUFGABEN + " SET " + ERLEDIGT + " = 1 WHERE " + AUFGABE_TITEL + " = ";
    }

    public static String getSqlQueryAufgabeNichtErledigt() {
        return "UPDATE " + TABLE_AUFGABEN + " SET " + ERLEDIGT + " = 0 WHERE " + AUFGABE_TITEL + " = ";
    }
}

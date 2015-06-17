package ch.bbcag.todo.database;

/**
 * Created by zascho on 17.06.2015.
 */
public class ToDoItem {

    private String aufgabe;
    private String beschreibung;
    private String erinnerung_zeit;
    private int wichtigkeit;

    public String getErinnerung_zeit() {
        return erinnerung_zeit;
    }

    public void setErinnerung_zeit(String erinnerung_zeit) {
        this.erinnerung_zeit = erinnerung_zeit;
    }


    public String getAufgabe() {
        return aufgabe;
    }

    public void setAufgabe(String aufgabe) {
        this.aufgabe = aufgabe;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung;
    }


    public int getWichtigkeit() {
        return wichtigkeit;
    }

    public void setWichtigkeit(int wichtigkeit) {
        this.wichtigkeit = wichtigkeit;
    }


}

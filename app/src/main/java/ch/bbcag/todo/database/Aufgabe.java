package ch.bbcag.todo.database;

/**
 * Created by zascho on 17.06.2015.
 */
public class Aufgabe {

    private String aufgabe;
    private String beschreibung;
    private int erinngerungszeit;
    private int wichtigkeit;
    private String liste;

    public String getListe() {
        return liste;
    }

    public void setListe(String liste) {
        this.liste = liste;
    }



    public int getErinngerungszeit() {
        return erinngerungszeit;
    }

    public void setErinngerungszeit(int erinngerungszeit) {
        this.erinngerungszeit = erinngerungszeit;
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

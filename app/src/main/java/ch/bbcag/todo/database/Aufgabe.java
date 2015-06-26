package ch.bbcag.todo.database;


import android.net.Uri;


/**
 * Created by zascho on 17.06.2015.
 */
public class Aufgabe {

    private String aufgabe;
    private String beschreibung;
    private Uri bild_uri;
    private int wichtigkeit;

    public long getListe() {
        return liste;
    }

    public void setListe(long liste) {
        this.liste = liste;
    }

    private long liste;





    public Uri getBild_uri() {
        return bild_uri;
    }

    public void setBild_uri(Uri bild_uri) {
        this.bild_uri = bild_uri;
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

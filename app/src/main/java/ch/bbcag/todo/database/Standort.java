package ch.bbcag.todo.Database;

/**
 * Created by zascho on 18.06.2015.
 */
public class Standort {
    public int getLongitude() {
        return Longitude;
    }

    public void setLongitude(int longitude) {
        Longitude = longitude;
    }

    public int getLaditude() {
        return laditude;
    }

    public void setLaditude(int laditude) {
        this.laditude = laditude;
    }

    public int getAdresse() {
        return adresse;
    }

    public void setAdresse(int adresse) {
        this.adresse = adresse;
    }

    private int adresse;
    private int laditude;
    private int Longitude;


}

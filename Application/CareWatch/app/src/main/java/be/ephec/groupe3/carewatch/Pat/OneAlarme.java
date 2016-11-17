package be.ephec.groupe3.carewatch.Pat;

/**
 * Created by aymeric on 12-11-16.
 */

public class OneAlarme {
    private String raison ,heure, minute;
    private int idSortie;
    public OneAlarme(String raison, String heure, String minute, int idSortie) {
        this.raison = raison;
        this.minute = minute;
        this.heure = heure;
        this.idSortie = idSortie;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public String getMinute() {
        return minute;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public int getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
    }
}

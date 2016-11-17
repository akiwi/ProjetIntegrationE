package be.ephec.groupe3.carewatch.Pat;

/**
 * Created by aymeric on 12-11-16.
 */

public class OneDate {
    private String raison ,dateRentrer, dateSortie;
    private int idSortie;
    public OneDate(String raison, String dateRentrer, String dateSortie, int idSortie) {
        this.raison = raison;
        this.dateRentrer = dateRentrer;
        this.dateSortie = dateSortie;
        this.idSortie = idSortie;
    }

    public String getRaison() {
        return raison;
    }

    public void setRaison(String raison) {
        this.raison = raison;
    }

    public String getDateRentrer() {
        return dateRentrer;
    }

    public void setDateRentrer(String dateRentrer) {
        this.dateRentrer = dateRentrer;
    }

    public String getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(String dateSortie) {
        this.dateSortie = dateSortie;
    }

    public int getIdSortie() {
        return idSortie;
    }

    public void setIdSortie(int idSortie) {
        this.idSortie = idSortie;
    }
}

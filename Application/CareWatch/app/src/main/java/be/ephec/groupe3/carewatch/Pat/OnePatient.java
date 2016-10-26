package be.ephec.groupe3.carewatch.pat;

/**
 * Created by aymeric on 23-10-16.
 */

public class OnePatient {
    private int id;
    private String nom,prenom;
    public OnePatient(Integer id,String nom,String prenom){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}

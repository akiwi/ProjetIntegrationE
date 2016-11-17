package be.ephec.groupe3.carewatch.Pat;

/**
 * Created by aymeric on 23-10-16.
 */

public class OnePatient {
    private int id, estPresent, port;
    private String nom,prenom, note;
    public OnePatient(Integer id,String nom,String prenom,Integer estPresent,String note,Integer port){
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.estPresent = estPresent;
        this.note = note;
        this.port = port;
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

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getEstPresent() {
        return estPresent;
    }

    public void setEstPresent(int estPresent) {
        this.estPresent = estPresent;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}

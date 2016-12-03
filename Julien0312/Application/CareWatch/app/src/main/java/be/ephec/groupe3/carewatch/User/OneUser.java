package be.ephec.groupe3.carewatch.User;

/**
 * Created by Julien on 3/12/2016.
 */

public class OneUser {
    int id;
    String nameUser;
    String pwdUser;
    int droit;

    public OneUser(Integer id,String nom,Integer droit){
        this.id = id;
        this.nameUser = nom;
        this.droit = droit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getPwdUser() {
        return pwdUser;
    }

    public void setPwdUser(String pwdUser) {
        this.pwdUser = pwdUser;
    }

    public int getDroit() {
        return droit;
    }

    public void setDroit(int droit) {
        this.droit = droit;
    }
}

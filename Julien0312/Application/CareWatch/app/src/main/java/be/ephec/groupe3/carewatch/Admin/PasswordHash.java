package be.ephec.groupe3.carewatch.Admin;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Julien on 11/12/2016.
 */

public class PasswordHash {
    private String login;
    private String pass;
    private String salt;
    private String passhash;

    public PasswordHash (String login,String pass){
        this.login = login;
        this.pass = pass;
        this.salt = this.login+this.pass;
        this.passhash = getSHA12(this.pass,this.salt);
    }

    public String getPasshash(){
        return this.passhash;
    }


    public String getSHA12(String password, String salt){
        String passwordHash = null;
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt.getBytes("UTF-8"));
            byte[] bytes = md.digest(password.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for(int i=0;i<bytes.length;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            passwordHash = sb.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return passwordHash;
    }
}

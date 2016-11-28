package be.ephec.groupe3.carewatch;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

import be.ephec.groupe3.carewatch.Task.Client;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

public class MainActivity extends Activity implements View.OnClickListener, TaskConnect.CustomConnexion {
    private TextView userName;
    private TextView userPwd;
    private Button btnConnect;

    private Button btnSocket;
    private TextView testSocket;

    private String login = "";
    private String pass = "";
    private final String URL_CONNEXION = "http://192.168.0.16/projetintegration/connexion.php?";

    private Boolean debug = false; //passer en mode debug et avoir des infos en plus


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);
        //socket
        //setContentView(R.layout.sockettest);

        //on récupère les composants du layout
        userName = (TextView) findViewById(R.id.ET_name);
        userPwd = (TextView) findViewById(R.id.ET_pass);
        btnConnect = (Button) findViewById(R.id.btn_connect);

        //socket
        //btnSocket = (Button) findViewById(R.id.btn_connect_socket);
        //testSocket = (TextView) findViewById(R.id.tv_socket);

        //on défini la méthode onClick plus bas et plus joli ATTENTION à bien ajouter l'implements
        btnConnect.setOnClickListener(this);
        //btnSocket.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn_connect: {
                login = userName.getText().toString();
                pass = userPwd.getText().toString();
                TaskConnect con = new TaskConnect(this);
                ContentValues cv = new ContentValues();
                //toujours mettre url dans le content value car important pour la TaskConnect
                cv.put("url", URL_CONNEXION);
                cv.put("nameUser", login);
                cv.put("pwdUser", pass);
                if ((!login.isEmpty()) && (!pass.isEmpty())) {
                    //on passe l'url et les identifiant à notre tache
                    con.execute(cv);
                } else
                    Toast.makeText(this, "Les champs ne peuvent pas être vide", Toast.LENGTH_SHORT).show();
            }
            //case R.id.btn_connect_socket:{
            //    Client myClient = new Client(testSocket);
            //    myClient.execute();
            //    Toast.makeText(this,"click",Toast.LENGTH_SHORT).show();
            //}
        }

    }

    /**
     * Grâce à l'implements CustomConnexion on peut utiliser les toast :-)
     */

    @Override
    public void showResultConnexion(String s){
        String id = "";
        try {
            JSONObject jo = new JSONObject(s);
            id = jo.optString("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(id.equals("-1")){
            if(debug)
                Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            Toast.makeText(this,"Login/mot de passe incorrect",Toast.LENGTH_SHORT).show();
        } else {
            if(debug)
                Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ListPatActivity.class);
            intent.putExtra("JsonList",s);
            intent.putExtra("idUser",id);
            startActivity(intent);
        }
    }
}
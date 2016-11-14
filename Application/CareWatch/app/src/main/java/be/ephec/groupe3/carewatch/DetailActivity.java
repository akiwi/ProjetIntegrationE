package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Time;

import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by aymeric on 26-10-16.
 */

public class DetailActivity extends Activity implements TaskConnect.CustomConnexion, View.OnClickListener{
    private String nom;
    private String prenom;
    private String note;
    private String descriptionAlarme;
    private Button ajouterAlarme;
    private TimePicker alarm;
    private int estPre;
    private int port;

    private EditText etDescAlarm;

    private final String URL_SET_ALARM = "http://192.168.0.16/projetintegration/setAlarm.php?";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_patient);

        Bundle extra = getIntent().getExtras();

        nom = extra.getString("nom");
        prenom = extra.getString("prenom");
        note = extra.getString("note");
        estPre = extra.getInt("estPresent");
        port = extra.getInt("port");

        TextView tvNomPre = (TextView) findViewById(R.id.tvNomPre);
        TextView tvEstPre = (TextView) findViewById(R.id.tvEstPresent);
        TextView tvNote = (TextView) findViewById(R.id.tvNote);
        etDescAlarm = (EditText) findViewById(R.id.ET_desc_Alarm);

        ajouterAlarme = (Button) findViewById(R.id.btn_add_alarm);
        ajouterAlarme.setOnClickListener(this);

        alarm = (TimePicker) findViewById(R.id.timePicker2);

        if(estPre == 0){
            tvEstPre.setTextColor(Color.RED);
            tvEstPre.setText("N'est actuellement pas présent ! ");
        } else{
            tvEstPre.setTextColor(Color.GREEN);
            tvEstPre.setText("Est actuellement présent");
        }
        tvNomPre.setText("Profil de : "+ nom +" "+prenom);
        tvNote.setText("commentaire : "+ note);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
           case R.id.btn_add_alarm :{
               int heure = alarm.getCurrentHour();
               int minute = alarm.getCurrentMinute();
               descriptionAlarme = etDescAlarm.getText().toString();
               Toast.makeText(this,heure+":"+minute+" sur le port : "+port+" note : "+descriptionAlarme,Toast.LENGTH_SHORT).show();
               ContentValues cv = new ContentValues();
               cv.put("url",URL_SET_ALARM);
               cv.put("port",String.valueOf(port));
               cv.put("heure",String.valueOf(heure));
               cv.put("minute",String.valueOf(minute));
               cv.put("raison",descriptionAlarme);

               TaskConnect setAlarm = new TaskConnect(this);
               setAlarm.execute(cv);


            }
        }
    }

    @Override
    public void showResultConnexion(String s) {
        String id = "";
        Log.d("result : ",s);
        try {
            JSONObject jo = new JSONObject(s);
            id = jo.optString("id").toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if(id.equals("-1")){
            Toast.makeText(this,"Erreur serveur, l'alarme n'a pas pu être ajoutée",Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this,"alarme ajoutée",Toast.LENGTH_SHORT).show();
        }
    }
}

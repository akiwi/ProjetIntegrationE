package be.ephec.groupe3.carewatch.DateChange;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

public class AlarmeActivity extends AppCompatActivity implements View.OnClickListener, TaskConnect.CustomConnexion {
    private String descriptionAlarme;
    private Button ajouterAlarme;
    private RadioButton rbEau;
    private RadioButton rbRepas;
    private RadioButton rbAutre;
    private EditText etDescAlarm;
    private int port;
    private TimePicker alarm;
    private final String URL_SET_ALARM = "http://192.168.0.16/projetintegration/setAlarm.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insert_time);

        Bundle extra = getIntent().getExtras();
        port = extra.getInt("port");
        Log.d("port Alarme", port+"");

        alarm = (TimePicker) findViewById(R.id.timePicker);
        ajouterAlarme = (Button) findViewById(R.id.btnSendAlarme);
        rbEau = (RadioButton) findViewById(R.id.rb_eau);
        rbRepas = (RadioButton) findViewById(R.id.rb_repas);
        rbAutre = (RadioButton) findViewById(R.id.rb_autre);
        etDescAlarm = (EditText) findViewById(R.id.descrAlarme);
        etDescAlarm.setEnabled(false);
        ajouterAlarme.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        int heure = alarm.getCurrentHour();
        int minute = alarm.getCurrentMinute();
        if(rbEau.isChecked()){
            descriptionAlarme = rbEau.getText().toString();
        }
        else if(rbRepas.isChecked()){
            descriptionAlarme = rbRepas.getText().toString();
        }
        else{
            descriptionAlarme = etDescAlarm.getText().toString();
        }

        Toast.makeText(this,heure+":"+minute+" sur le port : "+port+" note : "+descriptionAlarme,Toast.LENGTH_SHORT).show();
        ContentValues cv = new ContentValues();
        cv.put("url",URL_SET_ALARM);
        cv.put("port",String.valueOf(port));
        cv.put("heure",String.valueOf(heure));
        cv.put("minute",String.valueOf(minute));
        cv.put("raison",String.valueOf(descriptionAlarme));

        TaskConnect setAlarm = new TaskConnect(this);
        setAlarm.execute(cv);
    }

    @Override
    public void showResultConnexion(String s) {
        String id = "";
        Log.e("result Alarme:", s+"" );
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

    public void onRadioButtonClicked(View v){
        boolean checked = ((RadioButton) v).isChecked();

        switch (v.getId()){
            case R.id.rb_eau: {
                if(checked){
                    //Toast.makeText(this,"Eau coché",Toast.LENGTH_SHORT).show();
                    etDescAlarm.setEnabled(false);
                    etDescAlarm.setText("");
                }
                break;
            }
            case R.id.rb_repas: {
                if(checked){
                    //Toast.makeText(this,"Repas coché",Toast.LENGTH_SHORT).show();
                    etDescAlarm.setEnabled(false);
                    etDescAlarm.setText("");
                }
                break;
            }
            case R.id.rb_autre: {
                if(checked){
                    //Toast.makeText(this,"Autre coché",Toast.LENGTH_SHORT).show();
                    etDescAlarm.setEnabled(true);
                }
                break;
            }
        }
    }
}
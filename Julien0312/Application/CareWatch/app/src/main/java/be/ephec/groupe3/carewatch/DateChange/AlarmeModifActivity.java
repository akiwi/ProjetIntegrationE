package be.ephec.groupe3.carewatch.DateChange;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by aymeric on 17-11-16.
 */
public class AlarmeModifActivity  extends AppCompatActivity implements View.OnClickListener, TaskConnect.CustomConnexion {
    int idSortie;
    String heure,minute,raison;
    TextView tvModif;
    EditText etRaisonModif;
    Button btnModif,btnSuppr;
    private final String URL_SET_ALARM = "http:/192.168.42.1/setAlarme.php?";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modif_time);

        Bundle extras = getIntent().getExtras(); //on reprend les data inséré dans l'intent
        raison = extras.getString("raison");
        heure = extras.getString("heure");
        minute = extras.getString("minute");
        idSortie = extras.getInt("id");

        tvModif = (TextView) findViewById(R.id.tvHeureBase);
        etRaisonModif = (EditText) findViewById(R.id.descrAlarmeModif);
        btnModif = (Button) findViewById(R.id.btnModifAlarme);
        btnSuppr = (Button) findViewById(R.id.btnSupprAlarme);

        tvModif.setText("devait sonner à "+heure+":"+minute);
        etRaisonModif.setText(raison);

        btnSuppr.setOnClickListener(this);
        btnModif.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnModifAlarme:
                suppressionAlarme();
                break;
            case R.id.btnSupprAlarme:
                modifAlarme();
                break;
        }
    }

    public void  modifAlarme(){
        ContentValues cv = new ContentValues();
        cv.put("url",URL_SET_ALARM);
        cv.put("idSortie",String.valueOf(idSortie));
        cv.put("heure",String.valueOf(heure));
        cv.put("minute",String.valueOf(minute));
        cv.put("raison",String.valueOf(raison));
        cv.put("work","update");

        Log.i("sortie","id : "+ idSortie + "- heure :"+heure+"- minute : "+minute +"-raison :"+raison);
        TaskConnect setAlarm = new TaskConnect(this);
        setAlarm.execute(cv);
    }

    public void suppressionAlarme(){

        ContentValues cv = new ContentValues();
        cv.put("url",URL_SET_ALARM);
        cv.put("port",String.valueOf(idSortie));
        cv.put("work","suppression");
        TaskConnect setAlarm = new TaskConnect(this);
        setAlarm.execute(cv);
    }

    @Override
    public void showResultConnexion(String s) {
        Log.d("result", "showResultConnexion: " + s);
    }
}

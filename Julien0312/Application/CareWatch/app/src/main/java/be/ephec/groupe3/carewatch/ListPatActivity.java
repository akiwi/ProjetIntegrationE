package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import be.ephec.groupe3.carewatch.Pat.OnePatient;
import be.ephec.groupe3.carewatch.Pat.PatientAddAcitvity;
import be.ephec.groupe3.carewatch.Pat.PatientEditActivity;
import be.ephec.groupe3.carewatch.Task.TaskClient;
import be.ephec.groupe3.carewatch.UI.UIAdapter;

/**
 * Created by aymeric on 12-10-16.
 */

public class ListPatActivity extends Activity implements View.OnClickListener {
    ListView lvPatients;
    List<OnePatient> listPatient ;
    Button btn_ajout;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras(); //on reprend les data inséré dans l'intent

        String nameUser = extras.getString("NomUser");
        TextView tvBonjour = (TextView) findViewById(R.id.tvBonjour);
        tvBonjour.setText("Bonjour "+ nameUser);

        String jsonList = extras.getString("JsonList"); //récupération du tableau JSON sous forme de string
        listPatient = transformJson(jsonList); //transformation du tableau JSON en tableau d'objet OnePatient
        recupPatient(listPatient); // utilisation de l'adapter afin d'afficher les patients

        btn_ajout = (Button) findViewById(R.id.btn_pat_add);

        lvPatients = (ListView) findViewById(R.id.LvPatients);
        lvPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                OnePatient h = listPatient.get(position);
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("port",h.getPort());
                intent.putExtra("nom", h.getNom());
                intent.putExtra("prenom", h.getPrenom());
                intent.putExtra("estPresent",h.getEstPresent());
                intent.putExtra("note",h.getNote());
                startActivity(intent);
            }
        });

       // while(){
            TaskClient myClient = new TaskClient(this.getApplicationContext());
            myClient.execute();

      //  }
    }

    public void recupPatient(List<OnePatient> repertoire) {
        lvPatients = (ListView) findViewById(R.id.LvPatients);
        UIAdapter uiAdapter = new UIAdapter(this.getApplicationContext(), repertoire);
        lvPatients.setAdapter(uiAdapter);
    }

    public List<OnePatient> transformJson(String s) {
        List<OnePatient> listPatient = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray jArr = jo.getJSONArray("infoPatients");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject json_data = jArr.getJSONObject(i);
                Log.d("port recu", ""+json_data.getInt("port"));
                OnePatient patient = new OnePatient(i, json_data.getString("Nom"), json_data.getString("Prenom"),json_data.getInt("estPresent"),json_data.getString("note"),json_data.getInt("port"));
                listPatient.add(patient);

                if(json_data.getInt("estPresent") == 0){
            Log.d("not present", json_data.getString("Nom")+ " ");
        }
    }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPatient;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_pat_add:{
                Intent intent = new Intent(getApplicationContext(), PatientAddAcitvity.class);
                startActivity(intent);
            }
        }
    }
}
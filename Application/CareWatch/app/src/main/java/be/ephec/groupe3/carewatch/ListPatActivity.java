package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import be.ephec.groupe3.carewatch.Pat.OnePatient;
import be.ephec.groupe3.carewatch.ui.UIAdapter;

/**
 * Created by aymeric on 12-10-16.
 */

public class ListPatActivity extends Activity {
    ListView lvPatients;
    List<OnePatient> listPatient ;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bundle extras = getIntent().getExtras(); //on reprend les data inséré dans l'intent
        String jsonList = extras.getString("JsonList"); //récupération du tableau JSON sous forme de string
        listPatient = transformJson(jsonList); //transformation du tableau JSON en tableau d'objet OnePatient
        recupPatient(listPatient);

        lvPatients = (ListView) findViewById(R.id.LvPatients);
        lvPatients.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("position", String.valueOf(id));
                OnePatient h = listPatient.get(position);
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                intent.putExtra("nom", h.getNom());
                intent.putExtra("prenom", h.getPrenom());
                intent.putExtra("estPresent",h.getEstPresent());
                intent.putExtra("note",h.getNote());

                startActivity(intent);
            }
        });
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
            JSONObject jArr = jo.getJSONObject("infoPatients");

            Iterator x = jArr.keys(); //on récupère les key du jArr..
            JSONArray jsonArray = new JSONArray();
            while (x.hasNext()) {   //...nous permetant d'énumerer toute les key
                String key = (String) x.next();
                jsonArray.put(jArr.get(key));
            }
            Log.d("arraylength", String.valueOf(jsonArray.length()));
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                Log.d("POS-PAT", String.valueOf(i) + json_data.getString("Nom"));
                OnePatient patient = new OnePatient(i, json_data.getString("Nom"), json_data.getString("Prenom"),json_data.getInt("estPresent"),json_data.getString("note"));
                listPatient.add(patient);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listPatient;
    }
}

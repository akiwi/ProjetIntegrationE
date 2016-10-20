package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


/**
 * Created by aymeric on 12-10-16.
 */

public class ListPatActivity extends Activity{
    ListView lvPatients;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        String jsonList = extras.getString("JsonList");
        recupPatient(transformJson(jsonList));
    }
    public void recupPatient(String[][] repertoire) {
        lvPatients = (ListView) findViewById(R.id.LvPatients);
        List<HashMap<String, String>> liste = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> element;

        //Pour chaque personne dans notre répertoire…
        for (int i = 0; i < repertoire.length; i++){
            //… on crée un élément pour la liste…
            element = new HashMap<String, String>();
            element.put("Nom", repertoire[i][0]);
            element.put("Prénom", repertoire[i][1]);
            liste.add(element);
        }
        SimpleAdapter adapter = new SimpleAdapter(this, liste, R.layout.patient_list, new String[]{"Nom", "Prénom"},
                new int[]{R.id.listNom, R.id.listPrenom});
        //Pour finir, on donne à la ListView le SimpleAdapter
        lvPatients.setAdapter(adapter);

       // lvPatients.setOnItemClickListener();
    }
    public String[][] transformJson(String s){
        String[][] repertoire = new String[10][3];
        try {
            JSONObject jo = new JSONObject(s);
            JSONObject jArr = jo.getJSONObject("infoPatients");

            Iterator x = jArr.keys(); //on récupère les key du jArr..
            JSONArray jsonArray = new JSONArray();
            while (x.hasNext()){   //...nous permetant d'énumerer toute les key
                String key = (String) x.next();
                jsonArray.put(jArr.get(key));
            }
            Log.d("arraylength", String.valueOf(jsonArray.length()));
            repertoire = new String[jsonArray.length()][3];
            for (int i=0; i < jsonArray.length(); i++) {
                JSONObject json_data = jsonArray.getJSONObject(i);
                repertoire[i][0] = json_data.getString("Nom");
                repertoire[i][1] = json_data.getString("Prenom");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return repertoire;
    }
}

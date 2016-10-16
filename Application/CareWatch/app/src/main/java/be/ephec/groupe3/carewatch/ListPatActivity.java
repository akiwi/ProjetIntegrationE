package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


/**
 * Created by aymeric on 12-10-16.
 */

public class ListPatActivity extends Activity{
    ListView lvPatients;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recupPatient();
    }
    public void recupPatient() {
        lvPatients = (ListView) findViewById(R.id.LvPatients);

        String[][] repertoire = new String[][]{
                {"Bill Gates", "06 06 06 06 06"},
                {"Niels Bohr", "05 05 05 05 05"},
                {"Alexandre III de Macédoine", "04 04 04 04 04"}};

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
    }
}
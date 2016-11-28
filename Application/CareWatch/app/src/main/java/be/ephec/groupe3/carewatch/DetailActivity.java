package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import be.ephec.groupe3.carewatch.DateChange.AlarmeActivity;
import be.ephec.groupe3.carewatch.DateChange.CalendarActivity;
import be.ephec.groupe3.carewatch.Pat.OneAlarme;
import be.ephec.groupe3.carewatch.Pat.OneDate;
import be.ephec.groupe3.carewatch.Pat.OnePatient;
import be.ephec.groupe3.carewatch.Task.TaskConnect;
import be.ephec.groupe3.carewatch.UI.CalAdapter;

/**
 * Created by aymeric on 26-10-16.
 */

public class DetailActivity extends Activity implements  TaskConnect.CustomConnexion, View.OnClickListener {
    private List<OneDate> listDate ;
    private List<OneAlarme> listAlarme ;

    private String nom;
    private String prenom;
    private String note;
    private int estPre, port;
    private final String URL_CONNEXION = "http://192.168.0.16/projetintegration/connexion.php?";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_patient);

        Bundle extra = getIntent().getExtras();
        nom = extra.getString("nom");
        prenom = extra.getString("prenom");
        note = extra.getString("note");
        estPre = extra.getInt("estPresent");
        port = extra.getInt("port");
        recupeCalendar();

        TextView tvNomPre = (TextView) findViewById(R.id.tvNomPre);
        TextView tvEstPre = (TextView) findViewById(R.id.tvEstPresent);
        TextView tvNote = (TextView) findViewById(R.id.tvNote);

        Button btnCalendar = (Button) findViewById(R.id.btnCalendar);
        Button btnAlarme = (Button) findViewById(R.id.btnAlarme);
        if(estPre == 0){
            tvEstPre.setTextColor(Color.RED);
            tvEstPre.setText("N'est actuellement pas présent ! ");
        } else{
            tvEstPre.setTextColor(Color.GREEN);
            tvEstPre.setText("Est actuellement présent");
        }
        tvNomPre.setText("Profil de : "+ nom +" "+prenom);
        tvNote.setText( note);
        btnAlarme.setOnClickListener(this);
        btnCalendar.setOnClickListener(this);
    }
    private void recupeCalendar() {
        TaskConnect con = new TaskConnect(this);
        ContentValues cv = new ContentValues();
        cv.put("url", URL_CONNEXION);
        cv.put("portPat", port+"");
        con.execute(cv);
    }

    @Override
    public void showResultConnexion(String s) {
        transformJson(s);
        setAdapter();
      /*  ListView lvDateSortie = (ListView) findViewById(R.id.lvDateSortie);
        CalAdapter uiAdapter = new CalAdapter(this.getApplicationContext(), listDate);
        lvDateSortie.setAdapter(uiAdapter)*/
    }

    private void setAdapter() {
        HashMap<String, String> element;

        List<HashMap<String, String>> listDat = new ArrayList<HashMap<String, String>>();
        List<HashMap<String, String>> listAl = new ArrayList<HashMap<String, String>>();
        for(OneDate date: listDate) {
            element = new HashMap<String, String>();
            element.put("text1","Date de sortie : "+date.getDateSortie());
            element.put("text2","Date de rentrée : "+date.getDateRentrer());
            element.put("text3",date.getRaison());
            listDat.add(element);
        }
        for(OneAlarme alarme: listAlarme) {
            element = new HashMap<String, String>();
            element.put("text1",alarme.getHeure()+":"+alarme.getMinute());
            element.put("text2",alarme.getRaison());
            listAl.add(element);
        }

        ListView lvDateSotie = (ListView) findViewById(R.id.lvDateSortie);
        ListView lvAlarme = (ListView) findViewById(R.id.lvAlarme);

        ListAdapter adapterSortie = new SimpleAdapter(this, listDat, R.layout.sortie_list, new String[] {"text1", "text2","text3"},
                new int[] {R.id.tvListDroite, R.id.tvListGauche,R.id.tvDateComment });
        ListAdapter adapterAlarme = new SimpleAdapter(this, listAl, R.layout.alarme_list, new String[] {"text1", "text2"},
                new int[] {R.id.tvTimeAl, R.id.tvRaisonAl });
        lvDateSotie.setAdapter(adapterSortie);
        lvAlarme.setAdapter(adapterAlarme);
    }

    private void transformJson(String s) {
        listDate= new ArrayList<>();
        listAlarme = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray jArr = jo.getJSONArray("sortie");
            JSONArray jArrAlarme = jo.getJSONArray("alarme");

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject json_data = jArr.getJSONObject(i);
                OneDate date = new OneDate(json_data.getString("raison"),json_data.getString("DateRentrer"),json_data.getString("DateSortie"),json_data.getInt("idSortie"));
                Log.d("date", date.getRaison());
                listDate.add(date);
            }
            for (int i = 0; i < jArrAlarme.length(); i++) {
                JSONObject json_data = jArrAlarme.getJSONObject(i);
                OneAlarme alarme = new OneAlarme(json_data.getString("raison"),json_data.getString("heure"),json_data.getString("minute"),json_data.getInt("idAlarme"));
                Log.d("alarme", alarme.getRaison());
                listAlarme.add(alarme);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnCalendar:
                Intent intent = new Intent(getApplicationContext(),CalendarActivity.class);
                startActivity(intent);
                break;
            case R.id.btnAlarme:
                Intent intentAl = new Intent(getApplicationContext(),AlarmeActivity.class);
                Log.d("port Detail", port+"");
                intentAl.putExtra("port",port);
                startActivity(intentAl);
                break;
        }
    }
}

package be.ephec.groupe3.carewatch;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by aymeric on 26-10-16.
 */

public class DetailActivity extends Activity {
    private String nom;
    private String prenom;
    private String note;
    private int estPre;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_patient);

        Bundle extra = getIntent().getExtras();
        nom = extra.getString("nom");
        prenom = extra.getString("prenom");
        note = extra.getString("note");
        estPre = extra.getInt("estPresent");
        TextView tvNomPre = (TextView) findViewById(R.id.tvNomPre);
        TextView tvEstPre = (TextView) findViewById(R.id.tvEstPresent);
        TextView tvNote = (TextView) findViewById(R.id.tvNote);
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
}

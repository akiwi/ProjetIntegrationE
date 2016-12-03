package be.ephec.groupe3.carewatch.UI;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Pat.OnePatient;

import static android.R.*;

/**
 * Created by aymeric on 25-10-16.
 */

public class UIAdapter extends ArrayAdapter<OnePatient> {
    private Context context;
    private List<OnePatient> repertoire;
    public UIAdapter(Context context,List<OnePatient> repertoire) {
        super(context,0, repertoire);
        this.context = context;
        this.repertoire = repertoire;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.patient_list, parent, false);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.listNom);
        TextView text2 = (TextView) convertView.findViewById(R.id.listPrenom);
        ImageView btnFavoris = (ImageView) convertView.findViewById(R.id.btnFavoris);


        OnePatient h = repertoire.get(position);
        text1.setText(h.getNom());
        text2.setText(h.getPrenom());
        int estPre = h.getEstPresent();
        if(estPre == 0){
            btnFavoris.setImageResource(drawable.presence_busy);
            text2.setTextColor(Color.WHITE);
            text1.setTextColor(Color.WHITE);
            convertView.setBackgroundResource(R.drawable.round_corner_red);
        }
        if(estPre == 2){
            btnFavoris.setImageResource(drawable.presence_away);
            text2.setTextColor(Color.WHITE);
            text1.setTextColor(Color.WHITE);
            convertView.setBackgroundResource(R.drawable.round_corner_orange);

        }
        return convertView;
    }
}

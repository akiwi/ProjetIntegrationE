package be.ephec.groupe3.carewatch.ui;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Pat.OnePatient;

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

    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.patient_list, parent, false);
        }
        TextView text1 = (TextView) convertView.findViewById(R.id.listNom);
        TextView text2 = (TextView) convertView.findViewById(R.id.listPrenom);

        OnePatient h = repertoire.get(position);
        text1.setText(h.getNom());
        text2.setText(h.getPrenom());
        int estPre = h.getEstPresent();
        if(estPre == 0){
            text1.setTextColor(Color.WHITE);
            text2.setTextColor(Color.WHITE);
            convertView.setBackgroundResource(R.color.colorAccent);        }
        return convertView;
    }
}

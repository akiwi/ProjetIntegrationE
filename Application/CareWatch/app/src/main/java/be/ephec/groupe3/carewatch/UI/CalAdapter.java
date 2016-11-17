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
import android.widget.TextView;

import java.util.List;

import be.ephec.groupe3.carewatch.Pat.OneDate;
import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Pat.OnePatient;

/**
 * Created by aymeric on 25-10-16.
 */

public class CalAdapter extends ArrayAdapter<OneDate> {
    private Context context;
    private List<OneDate> repertoire;
    public CalAdapter(Context context, List<OneDate> repertoire) {
        super(context,0, repertoire);
        this.context = context;
        this.repertoire = repertoire;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.sortie_list, parent, false);
        }
        TextView sortie = (TextView) convertView.findViewById(R.id.tvListDroite);
        TextView rentrée = (TextView) convertView.findViewById(R.id.tvListGauche);
        TextView commentaire = (TextView) convertView.findViewById(R.id.tvDateComment);

        OneDate h = repertoire.get(position);
        sortie.setText(h.getDateSortie());
        rentrée.setText(h.getDateRentrer());
        commentaire.setText(h.getRaison());

        return convertView;
    }
}

package be.ephec.groupe3.carewatch.UI;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import be.ephec.groupe3.carewatch.Dialog.AlertDialogFragment;
import be.ephec.groupe3.carewatch.Admin.EditInfActivity;
import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.User.OneUser;

/**
 * Created by Julien on 3/12/2016.
 */

public class UIAdapterUser extends ArrayAdapter<OneUser>{
    private Context context;
    private List<OneUser> repertoire;
    private FragmentManager manager;

    public UIAdapterUser(Context context,List<OneUser> repertoire, FragmentManager manager) {
        super(context,0, repertoire);
        this.context = context;
        this.repertoire = repertoire;
        this.manager = manager;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.account_list, parent, false);
        }


        TextView text1 = (TextView) convertView.findViewById(R.id.tv_list_account_name);
        Button btn_delete = (Button) convertView.findViewById(R.id.btn_delete);
        Button btn_modifier = (Button) convertView.findViewById(R.id.btn_modifier);

        OneUser h = repertoire.get(position);

        btn_delete.setOnClickListener(new View.OnClickListener(){
         @Override
            public void onClick(View v){
             Toast.makeText(getContext(),"delete "+position,Toast.LENGTH_SHORT).show();
             AlertDialogFragment box = new AlertDialogFragment();
             Bundle extras = new Bundle();
             extras.putInt("id",repertoire.get(position).getId());
             extras.putString("nom",repertoire.get(position).getNameUser());
             box.setArguments(extras);
             box.show(manager,"dialog");
         }
        });

        btn_modifier.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(getContext(), EditInfActivity.class);
                intent.putExtra("id",repertoire.get(position).getId());
                intent.putExtra("nom",repertoire.get(position).getNameUser());
                intent.putExtra("droit",repertoire.get(position).getDroit());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });



        text1.setText(h.getNameUser());

        return convertView;
    }
}

package be.ephec.groupe3.carewatch.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import be.ephec.groupe3.carewatch.Task.TaskCon;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by Julien on 3/12/2016.
 */

public class AlertDialogFragment extends DialogFragment{




    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Voulez-vous vraiment supprimer cet utilisateur ?")
                .setPositiveButton("oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.i("Supprimé","Suppression de " + getArguments().get("nom"));
                        TaskCon connect = new TaskCon();
                        ContentValues cv = new ContentValues();
                        cv.put("url",getArguments().get("url").toString());
                        cv.put("id",getArguments().get("id").toString());
                        connect.execute(cv);

                    }
                })
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!getArguments().get("nom").equals(null))
                               Log.i("Supprimé","Annuler " + getArguments().get("nom"));

                    }
                });

        return builder.create();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

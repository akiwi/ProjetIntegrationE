package be.ephec.groupe3.carewatch.Pat;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by Julien on 9/12/2016.
 */

public class PatientAddAcitvity extends Activity implements View.OnClickListener, TaskConnect.CustomConnexion {

    private EditText et_namePat;
    private EditText et_surnamePat;
    private EditText et_comPat;
    private Button btn_add;

    private String name;
    private String surname;
    private String com;

    private final String URL_ADD_PAT = "http://192.168.0.16/projetintegration/addPat.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_add);

        et_comPat = (EditText) findViewById(R.id.ET_add_comPat);
        et_namePat = (EditText) findViewById(R.id.ET_add_namePat);
        et_surnamePat = (EditText) findViewById(R.id.ET_add_surnamePat);
        btn_add = (Button) findViewById(R.id.btn_add_patient);
        btn_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        name = et_namePat.getText().toString();
        surname = et_surnamePat.getText().toString();
        com = et_comPat.getText().toString();

        ContentValues cv = new ContentValues();
        cv.put("name",name);
        cv.put("surname",surname);
        cv.put("com",com);

        TaskConnect connect = new TaskConnect(this);
        connect.execute(cv);
    }

    @Override
    public void showResultConnexion(String s) {
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();

    }
}

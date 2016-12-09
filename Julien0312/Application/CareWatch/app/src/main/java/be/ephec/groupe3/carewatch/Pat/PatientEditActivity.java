package be.ephec.groupe3.carewatch.Pat;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by Julien on 9/12/2016.
 */

public class PatientEditActivity extends Activity implements View.OnClickListener,TaskConnect.CustomConnexion {

    private EditText et_namePat;
    private EditText et_surnamePat;
    private EditText et_comPat;
    private Button btn_edit;

    private String name;
    private String surname;
    private String com;
    private int port;

    private final String URL_EDIT_PAT = "http://192.168.0.16/projetintegration/editPat.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_edit);

        Bundle extra = getIntent().getExtras();

        et_comPat = (EditText) findViewById(R.id.ET_edit_comPat);
        et_namePat = (EditText) findViewById(R.id.ET_edit_namePat);
        et_surnamePat = (EditText) findViewById(R.id.ET_edit_surnamePat);
        btn_edit = (Button) findViewById(R.id.btn_edit_patient);
        btn_edit.setOnClickListener(this);

        name = extra.getString("name");
        surname = extra.getString("surname");
        com = extra.getString("comment");
        port = extra.getInt("port");

        et_namePat.setText(name);
        et_surnamePat.setText(surname);
        et_comPat.setText(com);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_edit_patient:{
                ContentValues cv = new ContentValues();
                cv.put("url",URL_EDIT_PAT);
                cv.put("port",Integer.toString(port));
                cv.put("name",et_namePat.getText().toString());
                cv.put("surname",et_surnamePat.getText().toString());
                cv.put("note",et_comPat.getText().toString());

                TaskConnect conn = new TaskConnect(this);
                conn.execute(cv);
                break;
            }
        }
    }

    @Override
    public void showResultConnexion(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}

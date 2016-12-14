package be.ephec.groupe3.carewatch.Admin;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by Julien on 3/12/2016.
 */

public class EditInfActivity  extends Activity implements View.OnClickListener,TaskConnect.CustomConnexion{
    private EditText et_name;
    private EditText et_mdp;
    private EditText et_mdp2;
    private RadioButton rb_inf_chef;
    private RadioButton rb_inf;
    private Button btn_modifier;

    private String URL_EDIT = "http://192.168.0.16/projetintegration/updateUser.php?";

    private Bundle extras;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_edit);

        extras = getIntent().getExtras();

        et_name = (EditText) findViewById(R.id.ET_edit_nameUser);
        et_mdp = (EditText) findViewById(R.id.ET_edit_pwd);
        et_mdp2 = (EditText) findViewById(R.id.ET_edit_pwd2);
        rb_inf_chef = (RadioButton) findViewById(R.id.rb_edit_infChef);
        rb_inf = (RadioButton) findViewById(R.id.rb_edit_inf);
        btn_modifier = (Button) findViewById(R.id.btn_edit);

        btn_modifier.setOnClickListener(this);

        et_name.setText(extras.getString("nom"));
        if(extras.getInt("droit") == 2){
            rb_inf_chef.setChecked(true);
        }
        else if(extras.getInt("droit") == 3){
            rb_inf.setChecked(true);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_edit: {
                String name = et_name.getText().toString();
                String mdp = et_mdp.getText().toString();
                String mdp2 = et_mdp2.getText().toString();
                String droit = "";

                if (rb_inf_chef.isChecked()) {
                    droit = "2";
                } else if (rb_inf.isChecked()) {
                    droit = "3";
                }

                int id = extras.getInt("id");

                ContentValues cv = new ContentValues();
                cv.put("id", Integer.toString(id));
                cv.put("url", URL_EDIT);
                cv.put("name", name);
                if (mdp.equals(mdp2) ) {
                    PasswordHash ph = new PasswordHash(name,mdp);
                    cv.put("mdp", ph.getPasshash());
                }
                cv.put("droit", droit);

                if (mdp.isEmpty() && mdp2.isEmpty()) {
                    TaskConnect connect = new TaskConnect(this);
                    connect.execute(cv);
                } else if(!mdp.equals(mdp2)){
                    Toast.makeText(this, "Les mots de passe doivent être les mêmes", Toast.LENGTH_SHORT).show();
                }
                else{
                    TaskConnect connect = new TaskConnect(this);
                    connect.execute(cv);
                }


            }
        }
    }

    @Override
    public void showResultConnexion(String s) {
        Log.e("ERROR",s);
        Toast.makeText(this,s, Toast.LENGTH_LONG).show();
    }
}

package be.ephec.groupe3.carewatch.Admin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import be.ephec.groupe3.carewatch.R;
import be.ephec.groupe3.carewatch.Task.TaskCon;
import be.ephec.groupe3.carewatch.Task.TaskConnect;

/**
 * Created by Julien on 3/12/2016.
 */

public class AddUserActivity extends Activity implements View.OnClickListener,TaskConnect.CustomConnexion{

    private EditText et_name;
    private EditText et_mdp;
    private EditText et_mdp2;
    private RadioButton rb_infChef;
    private RadioButton rb_inf;
    private Button btn_add;

    private String URL_ADD = "http://192.168.0.16/projetintegration/addUser.php?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_add);

        et_name = (EditText) findViewById(R.id.ET_add_nameUser);
        et_mdp = (EditText) findViewById(R.id.ET_add_pwd);
        et_mdp2 = (EditText) findViewById(R.id.ET_add_pwd2);
        rb_infChef = (RadioButton) findViewById(R.id.rb_add_infChef);
        rb_inf = (RadioButton) findViewById(R.id.rb_add_inf);
        btn_add = (Button) findViewById(R.id.btn_add_account);

        rb_inf.setChecked(true);

        btn_add.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_add_account:{
                String name = et_name.getText().toString();
                String mdp = et_mdp.getText().toString();
                String mdp2 = et_mdp2.getText().toString();
                String droit = "";

                if(rb_infChef.isChecked()){
                    droit = "2";
                }
                else if(rb_inf.isChecked()){
                    droit = "3";
                }

                ContentValues cv = new ContentValues();
                cv.put("id","1");
                cv.put("url",URL_ADD);
                cv.put("name",name);
                if(mdp.equals(mdp2)){
                    cv.put("mdp",mdp);
                }
                cv.put("droit",droit);

                if(mdp.equals(mdp2)){
                    if(((droit.equals("2")) || (droit.equals("3")))){
                        TaskConnect connect = new TaskConnect(this);
                        connect.execute(cv);
                    }
                    else{
                        Toast.makeText(this,"Veuillez définir si c'est une infirmière en chef ou non",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(this,"Mot de passe doivent être les mêmes",Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void showResultConnexion(String s) {
        if(s.equals("-1")){
            Toast.makeText(this,"Erreur lors de l'enregistrement",Toast.LENGTH_SHORT).show();
        }
        else if(s.equals("-2")){
            Toast.makeText(this,"Utilisateur déjà enregistré",Toast.LENGTH_SHORT).show();
        }
        else if(s.equals("1")){
            Toast.makeText(this,"Utilisateur enregistré",Toast.LENGTH_SHORT).show();
        }

    }
}

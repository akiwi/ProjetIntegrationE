package be.ephec.groupe3.carewatch.Admin;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import be.ephec.groupe3.carewatch.R;

import be.ephec.groupe3.carewatch.UI.UIAdapterUser;
import be.ephec.groupe3.carewatch.User.OneUser;

/**
 * Created by Julien on 3/12/2016.
 */

public class AdministrationActivity extends Activity implements View.OnClickListener{

    ListView lvUsers;
    List<OneUser> listUser;
    Button btn_AjouterCompte;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle extras = getIntent().getExtras();
        String id = extras.getString("id");
        String jsonList = extras.getString("JsonList");
        if(id.equals("1")){
            setContentView(R.layout.activity_administration);
        }
        else{
            finish();
        }

        btn_AjouterCompte = (Button) findViewById(R.id.btn_add);

        btn_AjouterCompte.setOnClickListener(this);

        listUser = transformJson(jsonList);
        recupUser(listUser);

        lvUsers = (ListView) findViewById(R.id.LvUsers);
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    public void recupUser(List<OneUser> repertoire) {
        lvUsers = (ListView) findViewById(R.id.LvUsers);

        UIAdapterUser uiAdapterUser = new UIAdapterUser(this.getApplicationContext(), repertoire, getFragmentManager());
        lvUsers.setAdapter(uiAdapterUser);
    }

    public List<OneUser> transformJson(String s) {
        List<OneUser> listUser = new ArrayList<>();
        try {
            JSONObject jo = new JSONObject(s);
            JSONArray jArr = jo.getJSONArray("listUser");
            for (int i = 0; i < jArr.length(); i++) {
                JSONObject json_data = jArr.getJSONObject(i);
                if(!json_data.getString("nom").equals("admin")){
                    OneUser user = new OneUser(json_data.getInt("id"),
                                               json_data.getString("nom"),
                                               json_data.getInt("droit")
                    );
                    listUser.add(user);
                }

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return listUser;
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn_add: {
                Toast.makeText(this,"ADD",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(),AddUserActivity.class);
                startActivity(intent);
            }
        }
    }
}

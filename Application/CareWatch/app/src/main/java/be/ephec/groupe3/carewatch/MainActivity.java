package be.ephec.groupe3.carewatch;
        import android.app.Activity;
        import android.content.ContentValues;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;
        import android.widget.Toast;

        import be.ephec.groupe3.carewatch.Task.TaskConnect;
        import be.ephec.groupe3.carewatch.Task.TaskConnexion;
//Test
public class MainActivity extends Activity implements TaskConnexion.CustomConnexion, View.OnClickListener, TaskConnect.CustomConnexion {
    private TextView userName;
    private TextView userPwd;
    private Button btnConnect;
    private Button btnTest;

    private String login = "";
    private String pass = "";

    private final String URL_CONNEXION = "http://192.168.0.16/projetintegration/connexion.php?";

    private Boolean debug = true; //passer en mode debug et avoir des infos en plus
    private Boolean test = true; //active l'interface de test


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        //on récupère les composants du layout
        userName = (TextView) findViewById(R.id.ET_name);
        userPwd = (TextView) findViewById(R.id.ET_pass);
        btnConnect = (Button) findViewById(R.id.btn_connect);
        btnTest = (Button) findViewById(R.id.btn_test);

        if(test)
            btnTest.setVisibility(View.VISIBLE);

        //on défini la méthode onClick plus bas et plus joli ATTENTION à bien ajouter l'implements
        btnConnect.setOnClickListener(this);
        btnTest.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {
            case R.id.btn_connect: {
                login = userName.getText().toString();
                pass = userPwd.getText().toString();

                //on créé une nouvelle Tache Asynchrone pour la connexion
                TaskConnexion connexion = new TaskConnexion(this);


                if ((!login.isEmpty()) && (!pass.isEmpty())) {
                    //on passe l'url et les identifiant à notre tache
                    connexion.execute(URL_CONNEXION, login, pass);
                } else
                    Toast.makeText(this, "Les champs ne peuvent pas être vide", Toast.LENGTH_SHORT).show();
            }
            case R.id.btn_test: {
                if (test) {
                    login = userName.getText().toString();
                    pass = userPwd.getText().toString();
                    TaskConnect con = new TaskConnect(this);
                    ContentValues cv = new ContentValues();
                    //toujours mettre url dans le content value car important pour la TaskConnect
                    cv.put("url", URL_CONNEXION);
                    cv.put("nameUser", login);
                    cv.put("pwdUser", pass);
                    if ((!login.isEmpty()) && (!pass.isEmpty())) {
                        //on passe l'url et les identifiant à notre tache
                        con.execute(cv);
                    } else
                        Toast.makeText(this, "Les champs ne peuvent pas être vide", Toast.LENGTH_SHORT).show();

                }
            }
        }

    }

    /**
     * Grâce à l'implements CustomConnexion on peut utiliser les toast :-)
     * méthode définie dans TaskConnexion
     */

    @Override
    public void showResultConnexion(String s){
        Log.d("Réponse",s);
        if(s.equals("-1")){
            Toast.makeText(this,"Login/mot de passe incorrect",Toast.LENGTH_SHORT).show();
        }
        else{
            if(debug)
                Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ListPatActivity.class);
            startActivity(intent);
        }
    }
}

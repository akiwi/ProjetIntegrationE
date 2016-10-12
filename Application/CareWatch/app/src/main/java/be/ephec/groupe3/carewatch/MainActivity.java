package be.ephec.groupe3.carewatch;
        import android.app.Activity;
        import android.content.Context;
        import android.content.Intent;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ListView;
        import android.widget.TextView;
        import android.widget.Toast;

        import be.ephec.groupe3.carewatch.Adapter.List_Patients;
        import be.ephec.groupe3.carewatch.Task.TaskConnexion;

public class MainActivity extends Activity implements TaskConnexion.CustomConnexion, View.OnClickListener {
    private TextView userName;
    private TextView userPwd;
    private Button btnConnect;
    private Context myContext;

    private String login = "";
    private String pass = "";

    private final String URL_CONNEXION = "http://192.168.1.44:7894/projetintegration/connexion.php?";

    private Boolean debug = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        //on récupère les composants du layout
        userName = (TextView) findViewById(R.id.ET_name);
        userPwd = (TextView) findViewById(R.id.ET_pass);
        btnConnect = (Button) findViewById(R.id.btn_connect);

        //on défini la méthode onClick plus bas et plus joli ATTENTION à bien ajouter l'implements
        btnConnect.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch(v.getId()){
            case R.id.btn_connect:{
                login = userName.getText().toString();
                pass = userPwd.getText().toString();

                //on créé une nouvelle Tache Asynchrone pour la connexion
                TaskConnexion connexion = new TaskConnexion(this);

                if((!login.isEmpty()) && (!pass.isEmpty())) {
                    //on passe l'url et les identifiant à notre tache
                    connexion.execute(URL_CONNEXION, login, pass);
                }
                else
                    Toast.makeText(this,"Les champs ne peuvent pas être vide",Toast.LENGTH_SHORT).show();
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

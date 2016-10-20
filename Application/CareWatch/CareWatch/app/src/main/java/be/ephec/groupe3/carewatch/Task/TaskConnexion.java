package be.ephec.groupe3.carewatch.Task;

import android.content.ContentValues;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Map;

import be.ephec.groupe3.carewatch.MainActivity;

/**
 * Created by Julien on 8/10/2016.
 */

public class TaskConnexion extends AsyncTask <String,String,String> {


    private CustomConnexion callback; //permet d'utiliser les callback afin de les appeler dans nos autres Activités.
    private String response = "";

    public interface CustomConnexion{
        //nous permets de réécrire la méthode autre part pour faire ce que l'on veux cf. MainActivity
        void showResultConnexion(String s);
    }

    public TaskConnexion(CustomConnexion callback){
        this.callback = callback;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        //on pourra mettre une barre de chargement (pour l'esthétisme) ou pas XD
        Log.d("tag","PreExcecute");
    }

    /**
     *
     * @param params
     * @return
     */

    @Override
    protected String doInBackground(String... params) {
        try {
            /**
             *  j'ai travaillé avec un ContentValues après si c'est possible (et plus facile)
             *  avec un JSonObject on pourrait mais c'est une idée à approfondir.
             *  params correspond à ce que nous envoyons quand on fais l'execute (cf. MainActivity)
             */

            ContentValues cv = new ContentValues();
            cv.put("nameUser",params[1]);
            cv.put("pwdUser",params[2]);

            URL url = new URL(params[0]);

            //on ouvre la connexion
            HttpURLConnection connexion = (HttpURLConnection) url.openConnection();
            //on défini notre méthode en POST (cf. php)
            connexion.setRequestMethod("POST");
            //on met à vrai nos input et output
            connexion.setDoInput(true);
            connexion.setDoOutput(true);

            connexion.connect();

            //on récupère le OutputStream pour écrire nos données en POST
            OutputStream os = connexion.getOutputStream();
            //On défini un writer pour écrire
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os,"UTF-8"));
            writer.write(getPostDataString(cv));
            writer.flush();
            writer.close();
            os.close();

            //si on reçoit une réponse
            int codeResponse = connexion.getResponseCode();
            //et que la réponse est valide
            if(codeResponse == HttpURLConnection.HTTP_OK){
                //on lit notre réponse;
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(connexion.getInputStream()));
                while((line=br.readLine()) != null){
                    response += line;
                }
            }
            else{
                response = "";
            }


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return response;
    }

    /**
     * on va construire notre String pour les entrées du ContentValue on donnera à chaque fois la key ainsi que la valeur
     * @param cv
     * @return
     * @throws UnsupportedEncodingException
     */

    private String getPostDataString(ContentValues cv) throws UnsupportedEncodingException{
        boolean first = true;
        StringBuilder sb = new StringBuilder();

        for(Map.Entry<String, Object> entry : cv.valueSet()) {
            if (first) {
                first = false;
            } else {
                sb.append("&");
            }
            sb.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            sb.append("=");
            sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
        }
        Log.d("URL",sb.toString());
        return sb.toString();

    }

    //permet de faire appelle à showresultConnexion réécrite dans les autre activité. et ainsi le partage de l'info
    @Override
    protected void onPostExecute(String s){
        callback.showResultConnexion(s);
    }



}
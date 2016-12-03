package be.ephec.groupe3.carewatch.Task;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import be.ephec.groupe3.carewatch.MainActivity;

import static android.content.Context.NOTIFICATION_SERVICE;

/**
 * Created by Julien on 24-11-16.
 */

public class TaskClient extends AsyncTask<Void, Void, Void> {

    String dstAddress = "95.182.175.234"; // à completer
    int dstPort = 6000;
    String response = "";
    private Context applicationContext;

    public TaskClient(Context applicationContext) {
        this.applicationContext = this.applicationContext;
    }


    @Override
    protected Void doInBackground(Void... voids) {
        Socket socket = null;

        try{
            socket = new Socket(dstAddress, dstPort);
            ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
            byte [] buffer = new byte[256];

            int bytesRead;
            InputStream inputStream = socket.getInputStream();

            while ((bytesRead = inputStream.read(buffer)) != -1){
                baos.write(buffer,0,bytesRead);
                response += baos.toString("UTF-8");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
            response = "UnknowHostException: " + e.toString();
        } catch (IOException e) {
            e.printStackTrace();
            response = "IOEception: " + e.toString();
        } finally {
            if(socket != null){
                try{
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void result){
        Log.d("notif", response+"");
    }
    private void afficheNotif(String nom, String prenom) {
        int ID = 001;
        final Intent emptyIntent = new Intent(applicationContext,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(applicationContext, 5, emptyIntent, 0);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(applicationContext);
        mBuilder.setSmallIcon(android.R.drawable.presence_busy)
                .setContentTitle("Alerte : Sortie de périmètre")
                .setVibrate(new long[] { 1000, 1000, 1000, 1000, 1000 })
                .setContentText("le patient : "+ nom +" "+ prenom)
                .setSound(Uri.parse("uri://sadfasdfasdf.mp3"))
                .setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager) applicationContext.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(ID, mBuilder.build());
    }
}

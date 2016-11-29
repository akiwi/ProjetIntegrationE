package be.ephec.groupe3.carewatch.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Julien on 24-11-16.
 */

public class Client extends AsyncTask<Void, Void, Void> {

    String dstAddress = "192.168.42.1"; // à completer
    int dstPort = 6000;
    String response = "";
    public TextView textSocket;

    public Client(TextView text){
        textSocket = text;
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
       textSocket.setText(response);
        //super.onPostExecute(result);
    }
}

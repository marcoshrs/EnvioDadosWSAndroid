package com.example.aluno.enviodadoswsandroid;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by aluno on 29/06/18.
 */

public class EnviarTimeWS extends AsyncTask<Time, Integer, String> {

    Context context;

    public EnviarTimeWS(Context context){
        this.context = context;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(Time... times) {
        URL url = null;
        String retorno = "Deu erro";
        try {
            url = new URL("http://200.17.98.122:8080/hellows/rest/service/inserirTime"); //url do webservice

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);
            conn.setDoInput(true);

            DataOutputStream os = new DataOutputStream(conn.getOutputStream());

            Time time = times[0];

            Gson gson = new Gson();
            os.writeBytes(gson.toJson(time));

            os.flush();
            os.close();

            retorno ="Deu certo, código "+conn.getResponseCode();

            Log.i("STATUS", String.valueOf(conn.getResponseCode()));
            Log.i("MSG", conn.getResponseMessage());

            conn.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retorno;
    }
}

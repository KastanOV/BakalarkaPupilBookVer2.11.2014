package src.restdao;

import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

/**
 * Created by KastanNotas on 19.11.2014.
 */
public class restTest extends AsyncTask <String,Void,String> {

    private String getTestData()  throws Exception{
        BufferedReader in = null;
        String data = null;
        try{
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://192.168.1.155:8080/PupilBookV10/test");
            HttpGet request = new HttpGet();
            request.setURI(website);

            HttpResponse response = client.execute(request);
            in = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer sb = new StringBuffer("");
            String l = "";
            String nl = System.getProperty("line.separator");
            while((l = in.readLine()) != null){
                sb.append(l + nl);
            }
            data = sb.toString();
            return data;
        }finally {
            if(in != null){
                try{
                    in.close();
                    return data;
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    private String downloadUrl() throws IOException {
        BufferedReader in = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL("http://www.seznam.cz");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d("GebugTag", "The response is: " + response);
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            // Convert the InputStream into a string
            String contentAsString = in.toString();
            return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }
}

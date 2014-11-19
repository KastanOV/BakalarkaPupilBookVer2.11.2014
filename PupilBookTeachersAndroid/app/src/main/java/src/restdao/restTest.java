package src.restdao;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStreamReader;
import java.net.URI;

/**
 * Created by KastanNotas on 19.11.2014.
 */
public class restTest {

    public String getTestData() throws Exception{
        BufferedReader in = null;
        String data = null;
        try{
            HttpClient client = new DefaultHttpClient();
            URI website = new URI("http://192.168.1.155:8080/faces/PupilBookV10/test");
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

}

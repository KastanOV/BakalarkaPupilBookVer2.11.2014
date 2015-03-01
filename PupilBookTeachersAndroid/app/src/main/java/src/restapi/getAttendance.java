package src.restapi;

/**
 * Created by Jaroslav on 3/1/2015.
 */

import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import src.DBAdapter.Attendance;
import src.pupilbookteachers.MainActivity;

public class getAttendance {

    private static final String DEBUG_TAG = "PupilBook";
    private MainActivity context;
    private SharedPreferences sharedpreferences;

    private String targetURL;
    public getAttendance(String URL, MainActivity context) {
        sharedpreferences = context.getSharedPreferences("PupilBook", context.MODE_PRIVATE);
        this.targetURL = URL + "attendance/" + sharedpreferences.getString("login", "Error") + "/" + sharedpreferences.getString("password", "Something wrong");
        this.context = context;
    }
    public String downloadUrl() throws IOException {
        InputStream is = null;

        try {
            URL url = new URL(targetURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            // Starts the query
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(DEBUG_TAG, "The response is: " + response);
            is = conn.getInputStream();
            readJsonStream(is);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return "OK";
    }

    public List<Attendance> readJsonStream(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
        try {
            return readMessage(reader);
        } catch (Exception e){
            e.printStackTrace();
        }
        finally{
            reader.close();
            return null;
        }
    }
    public List<Attendance> readMessage(JsonReader reader) throws IOException {
        long id = -1;
        List<Attendance> retval = new ArrayList<Attendance>();
        String tmp;
        reader.beginArray();
        Attendance tmpval = new Attendance();
        boolean first = true;
        while (reader.hasNext()) {
            if(first) {
                reader.beginObject();
                first = false;
            }
                    String name = reader.nextName();
                    if (name.equals("end")) {
                        tmp = reader.nextString();
                        tmpval.setEnd(Long.valueOf(tmp));
                    } else if (name.equals("excused")) {
                        tmpval.setExcused(reader.nextBoolean());
                    } else if (name.equals("id")) {
                        tmpval.setId(reader.nextInt());
                    } else if (name.equals("login")) {
                        tmpval.setLogin(reader.nextString());
                    }  else if (name.equals("start")) {
                        tmpval.setStart(Long.valueOf(reader.nextString()));
                        retval.add(tmpval);
                        reader.endObject();
                        first=true;
                    } else {
                        reader.skipValue();
                    }


        }
        reader.endArray();
        return retval;
    }
}

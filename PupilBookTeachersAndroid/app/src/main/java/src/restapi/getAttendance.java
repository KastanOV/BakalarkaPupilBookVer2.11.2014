package src.restapi;

/**
 * Created by Jaroslav on 3/1/2015.
 */

import android.content.SharedPreferences;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.pupilbookteachers.MainActivity;

public class getAttendance {
    private XmlPullParserFactory xmlFactoryObject;
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
            xmlFactoryObject = XmlPullParserFactory.newInstance();
            XmlPullParser myparser = xmlFactoryObject.newPullParser();
            myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES
                    , false);
            myparser.setInput(is, "UTF-8");
            parseXMLAndStoreIt(myparser);

        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return "OK";
    }

    private void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        Integer id = null;
        String text=null, excused = null, login = null;
        Long start = null, end = null;

        String Login = null;
        AttendanceTable db = new AttendanceTable(context);
        db.deleteAllAttendance();

        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;

                    case XmlPullParser.END_TAG:
                        if(name.equals("end")){
                            if(!text.equals("null")) {
                                end = Long.parseLong(text);
                            }
                        }else if(name.equals("excused")){
                            excused = text;

                        }else if(name.equals("id")){
                            id = Integer.parseInt(text);

                        }else if(name.equals("login")){
                            login = text;

                        }else if(name.equals("start")){
                            start = Long.parseLong(text);
                            Attendance si = new Attendance(id,start,end,Boolean.valueOf(excused),login);
                            db.createAttendance(si);
                            start = null; end = null;text=null; excused = null; login = null;
                        }
                        else{
                        }
                        break;
                }
                event = myParser.next();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

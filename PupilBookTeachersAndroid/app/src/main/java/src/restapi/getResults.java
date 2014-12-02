package src.restapi;

import android.content.SharedPreferences;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.DBAdapter.SheduleItem;
import src.DBAdapter.SheduleItemTable;
import src.pupilbookteachers.MainActivity;

/**
 * Created by KastanNotas on 2.12.2014.
 */
public class getResults {
    private XmlPullParserFactory xmlFactoryObject;

    private static final String DEBUG_TAG = "PupilBook";
    private MainActivity context;

    private String targetURL;
    public getResults(String URL, MainActivity context) {
        SharedPreferences sharedpreferences = context.getSharedPreferences("PupilBook", context.MODE_PRIVATE);
        this.targetURL = URL + "Results/" + sharedpreferences.getString("login", "Error") + "/" + sharedpreferences.getString("password", "Something wrong");
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
        String text=null, desc = null, date = null, sL = null;
        Integer id = null, score = null, ssId = null;

        String Login = null;
        ResultsTable db = new ResultsTable(context);
        db.deleteAllResult();

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
//public Result(Integer id, String desc, int score, String date, Integer ssId, String sL)
                    case XmlPullParser.END_TAG:
                        if(name.equals("id")){
                            id = Integer.parseInt(text);
                        }else if(name.equals("desc")){
                            desc = text;

                        }else if(name.equals("score")){
                            score = Integer.parseInt(text);

                        }else if(name.equals("date")){
                            date = text;

                        }else if(name.equals("ssId")){
                            ssId = Integer.parseInt(text);

                        }else if(name.equals("sL")){
                            sL = text;
                            Result si = new Result(id,desc,score,date,ssId,sL);
                            db.createResult(si);
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

package src.restapi;

import android.content.Context;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;

/**
 * Created by Topr on 11/27/2014.
 */
public class getStudySubjects {
    private XmlPullParserFactory xmlFactoryObject;

    private static final String DEBUG_TAG = "PupilBook";
    private Context context;

    private String targetURL;


    public getStudySubjects(String URL, Context context) {
        //SharedPreferences sharedpreferences = context.getSharedPreferences("PupilBook", Context.MODE_PRIVATE);
        this.targetURL = URL + "studysubjects"; //sharedpreferences.getString("login", "Error") + " " + sharedpreferences.getString("password", "Something wrong");
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
            //String TMP = readIt(is,1000);
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
        String text=null;
        Integer id = null;
        String Name = null,ShortName = null;
        StudySubjectTable db = new StudySubjectTable(context);
        db.deleteAllStudySubjects();

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
                        if(name.equals("id")){
                            id = Integer.parseInt(text);
                        }else if(name.equals("name")){
                            Name = text;
                        }else if(name.equals("SName")){
                            ShortName = text;
                            StudySubject ss = new StudySubject(id,Name,ShortName);
                            db.createStudySubject(ss);
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
    public String readIt(InputStream stream, int len) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[len];
        reader.read(buffer);
        return new String(buffer);
    }

}

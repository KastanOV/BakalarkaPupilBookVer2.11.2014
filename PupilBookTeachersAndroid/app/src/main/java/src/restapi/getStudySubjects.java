package src.restapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
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

import src.pupilbookteachers.MainActivity;

/**
 * Created by Topr on 11/27/2014.
 */
public class getStudySubjects extends AsyncTask<String, Void, String> {
    private XmlPullParserFactory xmlFactoryObject;

    private static final String DEBUG_TAG = "PupilBook";
    private static final String TEACHER_LOGIN = "login";
    private static final String TEACHER_PASSWORD = "password";
    private MainActivity context;
    ProgressDialog dialog;

    public getStudySubjects(MainActivity context) {
        this.context = context;
        dialog = ProgressDialog.show(context, "Loading", "Please wait...", true);
    }

    @Override
    protected String doInBackground(String... urls) {

        // params comes from the execute() call: params[0] is the url.
        try {

            return downloadUrl(urls[0]);
        } catch (IOException e) {
            return "Unable to retrieve web page. URL may be invalid.";
        }
    }
    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(final String result) {
        //dialog.cancel();
    }

    private String downloadUrl(String myurl) throws IOException {
        InputStream is = null;
        // Only display the first 500 characters of the retrieved
        // web page content.
        int len = 500;

        try {
            URL url = new URL(myurl);
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
            myparser.setInput(is, null);
            parseXMLAndStoreIt(myparser);
            // Convert the InputStream into a string
            //String contentAsString = readIt(is, len);
            //return contentAsString;

            // Makes sure that the InputStream is closed after the app is
            // finished using it.
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return "OK";
    }
    public void parseXMLAndStoreIt(XmlPullParser myParser) {
        int event;
        String text=null;
        String login = null;
        String password = null;
        String firstName = null;
        String lastName = null;

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
                        if(name.equals(TEACHER_LOGIN)){
                            login = text;
                        }else if(name.equals(TEACHER_PASSWORD)){
                            password = text;
                        }else if(name.equals("firstName")){
                            firstName = text;
                        }else if (name.equals("lastName")){
                            lastName = text;
                        }
                        else{
                        }
                        break;
                }
                event = myParser.next();
            }
            if(login != null && password != null){
                SharedPreferences sharedpreferences = context.getSharedPreferences("PupilBook", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString("login", login);
                editor.putString("password", password);
                editor.putString("firstName", firstName);
                editor.putString("lastName", lastName);
                editor.commit();
                Intent in =  new Intent(context, MainActivity.class);
                context.startActivity(in);
            } else {
                return;
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

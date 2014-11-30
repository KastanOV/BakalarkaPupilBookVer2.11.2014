package src.restapi;

import android.os.AsyncTask;

import java.io.IOException;

import src.pupilbookteachers.MainActivity;

/**
 * Created by Topr on 11/29/2014.
 */
public class downSynchonization extends AsyncTask <String, Void, String> {
    private MainActivity context;

    public downSynchonization(MainActivity context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(String... params) {
        //getStudySubjects gss =  new getStudySubjects(params[0],context);
        getStudyGroups gsg = new getStudyGroups(params[0], context);
        try {
            //gss.downloadUrl();
            gsg.downloadUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

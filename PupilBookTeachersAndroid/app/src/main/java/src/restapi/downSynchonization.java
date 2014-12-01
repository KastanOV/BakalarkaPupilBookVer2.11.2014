package src.restapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;

import src.pupilbookteachers.MainActivity;

/**
 * Created by Topr on 11/29/2014.
 */
public class downSynchonization extends AsyncTask <String, Void, String> {
    private MainActivity context;
    ProgressDialog dialog;
    public downSynchonization(MainActivity context) {
        this.context = context;
        dialog = ProgressDialog.show(context, "Synchronizují se data", "Prosím čekejte...", true);
    }

    @Override
    protected void onPostExecute(final String result) {
        dialog.cancel();
    }
    @Override
    protected String doInBackground(String... params) {

        getStudySubjects gss =  new getStudySubjects(params[0],context);
        getStudyGroups gsg = new getStudyGroups(params[0], context);
        getSheduleItems gsi = new getSheduleItems(params[0], context);
        getStudents gs = new getStudents(params[0],context);
        try {
            gss.downloadUrl();
            gsg.downloadUrl();
            gsi.downloadUrl();
            gs.downloadUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

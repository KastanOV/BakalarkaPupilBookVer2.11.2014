package src.restapi;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
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
        //AttendanceTable attmpp = new AttendanceTable(context);
        //List<Attendance> atl = attmpp.getAllAttendance();
        uploadResults ur = new uploadResults(params[0],context);
        uploadAttendance ua = new uploadAttendance(params[0],context);
        getStudySubjects gss =  new getStudySubjects(params[0],context);
        getStudyGroups gsg = new getStudyGroups(params[0], context);
        getSheduleItems gsi = new getSheduleItems(params[0], context);
        getStudents gs = new getStudents(params[0],context);
        getAttendance ga = new getAttendance(params[0], context);
        getResults gr = new getResults(params[0], context);
        String test = "test";
        try {
            gss.downloadUrl();
            gsg.downloadUrl();
            gsi.downloadUrl();
            gs.downloadUrl();
            gr.downloadUrl();
            ga.downloadUrl();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

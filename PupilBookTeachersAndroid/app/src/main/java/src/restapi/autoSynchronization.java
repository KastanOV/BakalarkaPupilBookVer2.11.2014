package src.restapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;

import src.pupilbookteachers.MainActivity;

/**
 * Created by Jaroslav on 3/3/2015.
 */
public class autoSynchronization extends AsyncTask<String, Void, String> {
    private MainActivity context;
    ProgressDialog dialog;
    public autoSynchronization(MainActivity context) {
        this.context = context;
        //dialog = ProgressDialog.show(context, "Synchronizují se data", "Prosím čekejte...", true);
    }

    @Override
    protected void onPostExecute(final String result) {
        //dialog.cancel();
    }
    @Override
    protected String doInBackground(String... params) {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        for(;;){
            try {
                if (networkInfo != null && networkInfo.isConnected()) {
                    uploadResults ur = new uploadResults(params[0], context);
                    uploadAttendance ua = new uploadAttendance(params[0], context);
                }
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

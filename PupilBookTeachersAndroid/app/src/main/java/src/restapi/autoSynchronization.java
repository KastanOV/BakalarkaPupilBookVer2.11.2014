package src.restapi;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;

import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.pupilbookteachers.MainActivity;

/**
 * Created by Jaroslav on 3/3/2015.
 */
public class autoSynchronization implements Runnable {
    private Thread t;
    private MainActivity context;
    ProgressDialog dialog;
    String URLAdress;
    public autoSynchronization (MainActivity context,String  URLAdress){
        this.context = context;
        this.URLAdress = URLAdress;
    }
    public void start ()
    {

        if (t == null)
        {
            t = new Thread (this, "POPAPU AutoSync");
            t.start ();
        }
    }
    @Override
    public void run() {
        ConnectivityManager connMgr = (ConnectivityManager)
                context.getSystemService(Context.CONNECTIVITY_SERVICE);
        for(;;){
            ResultsTable resTable = new ResultsTable(context);
            List<Result> resList = resTable.getNewResultsForUpload();
            AttendanceTable attTable = new AttendanceTable(context);
            List<Attendance> tmp = attTable.getNewAttendanceForUpload();
            if(!resList.isEmpty()){
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        uploadResults ur = new uploadResults(URLAdress, context);

                    }

            }
            if(!tmp.isEmpty()){
                    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
                    if (networkInfo != null && networkInfo.isConnected()) {
                        uploadAttendance ua = new uploadAttendance(URLAdress, context);
                    }
            }
            try{
                Thread.sleep(10000L);
            }catch(Exception e){

            }

        }
    }
}

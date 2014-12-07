package src.pupilbookteachers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.DBAdapter.SheduleItem;
import src.DBAdapter.SheduleItemTable;
import src.DBAdapter.Student;
import src.DBAdapter.StudentTable;
import src.DBAdapter.StudyGroup;
import src.DBAdapter.StudyGroupTable;
import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;
import src.restapi.downSynchonization;


public class MainActivity extends Activity {
    TextView testText;
    public final static String SHARED_PREFERENCES = "PupilBook";
    public final static String LOGIN = "login";
    public final static String PASSWORD = "password";
    public SharedPreferences sharedpreferences;
    //private String LOCAL_URL = "http://192.168.1.61:8080/PupilBookV11/webresources/";
    private String LOCAL_URL = "http://86.49.147.135:9001/PupilBookV11/webresources/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        if(sharedpreferences.contains(LOGIN)){
            TextView cr =  (TextView) findViewById(R.id.textCredentials);
            cr.setText(sharedpreferences.getString("firstName", "Error") + " " + sharedpreferences.getString("lastName", "Something wrong"));
            if(getIntent().getBooleanExtra("doSynchronization", false)){
                new downSynchonization(this).execute(LOCAL_URL);
            }
        } else {
            Intent in =  new Intent(this, LoginActivity.class);
            startActivity(in);
        }
    }

    public void logout(View view) {
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(LOGIN);
        editor.remove(PASSWORD);
        editor.remove("firstName");
        editor.remove("lastName");
        editor.commit();
        Intent in =  new Intent(this, LoginActivity.class);
        startActivity(in);
    }
    public void goToClassification(View view){
        Intent in =  new Intent(this,StudentsActivity.class);
        startActivity(in);
    }
    public void doSynchronization(View view){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new downSynchonization(this).execute(LOCAL_URL);
        }else {
            // TODO Connection Error Dialog
            String error = "error";
        }

    }
    public void myClickHandler(View view) {

        StudySubjectTable db = new StudySubjectTable(this);
        StudyGroupTable dbg = new StudyGroupTable(this);
        SheduleItemTable dbsi = new SheduleItemTable(this);
        StudentTable st = new StudentTable(this);
        ResultsTable res = new ResultsTable(this);
        //db.deleteAllStudySubjects();
        List<StudySubject> llllllll = db.getAllStudySubject();
        List<StudyGroup> ppppppp = dbg.getAllStudyGroup();
        List<SheduleItem> sssssss= dbsi.getAllSheduleItem();
        List<Student> Students = st.getAllStudent();
        List<Result> Results = res.getNewResultsForUpload();
        String bla = "bla";
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

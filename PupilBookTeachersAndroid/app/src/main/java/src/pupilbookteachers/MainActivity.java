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

import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;
import src.restapi.downSynchonization;


public class MainActivity extends Activity {
    TextView testText;
    protected final static String SHARED_PREFERENCES = "PupilBook";
    protected final static String LOGIN = "login";
    protected final static String PASSWORD = "password";
    protected SharedPreferences sharedpreferences;
    private String LOCAL_URL = "http://192.168.1.61:8080/PupilBookV11/webresources/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedpreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        if(sharedpreferences.contains(LOGIN)){
            TextView cr =  (TextView) findViewById(R.id.textCredentials);
            cr.setText(sharedpreferences.getString("firstName", "Error") + " " + sharedpreferences.getString("lastName", "Something wrong"));
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
        Intent in =  new Intent(this,ClasificationActivity.class);
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
        //db.deleteAllStudySubjects();
        List<StudySubject> llllllll = db.getAllStudySubject();
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

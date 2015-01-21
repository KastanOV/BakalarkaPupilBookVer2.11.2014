package src.pupilbookteachers;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import src.restapi.Login;
import src.restapi.MD5Hash;


public class LoginActivity extends Activity {

    //private final String stringUrl = "http://192.168.1.61:8080/PupilBookV11/webresources/teachers/";
    private final String stringUrl = "http://86.49.147.135:9001/PupilBookV11/webresources/teachers/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    public void clickLogin(View view) {
        TextView Login =  (TextView) findViewById(R.id.editUserName);
        TextView Password = (TextView) findViewById(R.id.editPassword);
        Button SubmitButton = (Button) findViewById(R.id.buttonSubmitLogin);

        tryLogin(Login.getText().toString(), Password.getText().toString());


    }
    public void tryLogin(String login, String Password){
        Password = MD5Hash.md5Hash(Password);
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            String tmpUrl = stringUrl + login + "/" + Password;
                new Login(this).execute(tmpUrl);

        } else {

        }
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

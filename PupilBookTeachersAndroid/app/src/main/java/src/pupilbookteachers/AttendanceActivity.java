package src.pupilbookteachers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import src.Controls.attendanceListAdapter;
import src.Controls.resultsListAdapter;
import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;


public class AttendanceActivity extends Activity {
    private String StudentLogin;
    private ListView listViewAttendance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        StudentLogin = getIntent().getStringExtra("selectecStudent");
        fillListViewAttendance();
    }

    private void fillListViewAttendance(){
        listViewAttendance = (ListView) findViewById(R.id.listViewAttendance);
        AttendanceTable res = new AttendanceTable(this);
        List<Attendance> resItems = res.getAttendance(StudentLogin);
        //List<Attendance> resItems = res.getAllAttendance();
        Attendance[] resArray = new Attendance[resItems.size()];
        resArray  = resItems.toArray(resArray);
        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
        //       android.R.layout.simple_list_item_2, android.R.id.text2, resItems);
        attendanceListAdapter adapter = new attendanceListAdapter(this, resArray);

        listViewAttendance.setAdapter(adapter);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
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

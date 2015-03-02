package src.pupilbookteachers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import src.Controls.attendanceListAdapter;
import src.Controls.resultsListAdapter;
import src.DBAdapter.Attendance;
import src.DBAdapter.AttendanceTable;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.DBAdapter.StudentTable;


public class AttendanceActivity extends Activity {
    private String StudentLogin;
    private ListView listViewAttendance;
    private TextView nameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        StudentLogin = getIntent().getStringExtra("selectecStudent");
        nameText = (TextView) findViewById(R.id.nameView);
        StudentTable stTab = new StudentTable(this);
        nameText.setText(stTab.getStudent(StudentLogin).getLastName() + " " + stTab.getStudent(StudentLogin).getLastName());
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

    public void clickMissingAttendance(View view) {
        Attendance at = new Attendance();
        at.setLogin(StudentLogin);
        at.setExcused(false);
        at.setStart(new Date().getTime());
        AttendanceTable att = new AttendanceTable(this);
        att.createAttendance(at);
        fillListViewAttendance();
    }
    public void clickReturnAttendance(View view) {
        AttendanceTable res = new AttendanceTable(this);
        List<Attendance> resItems = res.getAttendance(StudentLogin);
        if(resItems.isEmpty()) return;
        Attendance tmpChange = new Attendance();
        tmpChange.setStart(0L);
        for(Attendance item : resItems){
            if(tmpChange.getStart() <= item.getStart()) tmpChange = item;
        }
        if(tmpChange.getEnd() == null){
            tmpChange.setEnd(new Date().getTime());
        }
        AttendanceTable att = new AttendanceTable(this);
        att.updateAttendance(tmpChange);
        fillListViewAttendance();
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

package src.pupilbookteachers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import src.Controls.SpinnerObject;
import src.Controls.onStudyGroupClickListener;
import src.DBAdapter.StudyGroup;
import src.DBAdapter.StudyGroupTable;


public class StudentsActivity extends Activity {
    private Spinner spinerStudyGroup;
    public Spinner spinnerStudent;
    public Button buttonAttendance;
    public Button buttonClassificatinStudent;
    private String SelectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);
        spinerStudyGroup = (Spinner) findViewById(R.id.spinnerStudyGroup);
        spinnerStudent = (Spinner) findViewById(R.id.spinnerStudent);
        buttonAttendance = (Button) findViewById(R.id.buttonAttendance);
        buttonClassificatinStudent = (Button) findViewById(R.id.buttonClasificationStudent);
        spinnerStudent.setEnabled(false);
        buttonAttendance.setEnabled(false);
        buttonClassificatinStudent.setEnabled(false);
        loadSpinnerStudyGroup();
    }
    public void goToClassification(View view){
        Intent in = new Intent(this, ResultsActivity.class);
        in.putExtra("selectecStudent", SelectedStudent);
        startActivity(in);
    }
    public void goToAttendance(View view){
        Intent in = new Intent(this, AttendanceActivity.class);
        in.putExtra("selectecStudent", SelectedStudent);
        startActivity(in);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_clasification, menu);
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


    private void loadSpinnerStudyGroup() {
        List < SpinnerObject > labels = new ArrayList< SpinnerObject >();
        StudyGroupTable db = new StudyGroupTable(this);
        List<StudyGroup> sg = db.getAllStudyGroup();
        labels.add(new SpinnerObject(-1, "Vyberte třídu"));
        for(StudyGroup item : sg){
            labels.add(new SpinnerObject(item.getIdStudyGroup(), item.getName()));
        }
        db.close();
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_dropdown_item , labels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinerStudyGroup.setAdapter(dataAdapter);
        spinerStudyGroup.setOnItemSelectedListener(new onStudyGroupClickListener(this));
    }
    public String getSelectedStudent() {
        return SelectedStudent;
    }

    public void setSelectedStudent(String selectedStudent) {
        SelectedStudent = selectedStudent;
    }
}

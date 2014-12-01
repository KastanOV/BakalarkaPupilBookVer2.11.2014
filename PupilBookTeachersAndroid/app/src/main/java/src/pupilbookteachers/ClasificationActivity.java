package src.pupilbookteachers;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import src.Controls.SpinnerObject;
import src.Controls.onStudyGroupClickListener;
import src.Controls.onStudySubjectClickListener;
import src.DBAdapter.StudentTable;
import src.DBAdapter.StudyGroup;
import src.DBAdapter.StudyGroupTable;
import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;

public class ClasificationActivity extends Activity {
    private String StudentLogin;
    private Spinner spinnerStudySubjects;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasification);

        this.StudentLogin = getSelectedStudent();
        spinnerStudySubjects = (Spinner) findViewById(R.id.spinnerStudySubject);
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

    private void loadSpinnerStudySubjects() {
        List< SpinnerObject > labels = new ArrayList< SpinnerObject >();
        StudySubjectTable db = new StudySubjectTable(this);
        StudentTable student = new StudentTable(this);
        List<StudySubject> ss = db.getStudySubjectsFromStudyGroup(student.getStudent(StudentLogin).getStudyGroupID());
        for(StudySubject item : ss){
            labels.add(new SpinnerObject(item.getIdStudySubject(), item.getName()));
        }
        db.close();
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(this,
                android.R.layout.simple_spinner_dropdown_item , labels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        spinnerStudySubjects.setAdapter(dataAdapter);
        spinnerStudySubjects.setOnItemSelectedListener(new onStudySubjectClickListener());
    }
}

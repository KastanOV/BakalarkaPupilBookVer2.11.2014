package src.pupilbookteachers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import src.Controls.SpinnerObject;
import src.Controls.onStudySubjectClickListener;
import src.Controls.onStudySubjectClickListenerResultList;
import src.DBAdapter.ResultsTable;
import src.DBAdapter.StudentTable;
import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;


public class ResultsListActivity extends Activity {
    private ListView listViewScores;
    private Spinner spinnerStudySubjects;
    private String StudentLogin;
    private Integer studySubjectID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_list);
        StudentLogin = getIntent().getStringExtra("selectecStudent");
        loadSpinnerStudySubjects(StudentLogin);
    }
    private void loadSpinnerStudySubjects(String StudentLogin) {
        spinnerStudySubjects = (Spinner) findViewById(R.id.spinnerStudySubject);
        List<SpinnerObject> labels = new ArrayList< SpinnerObject >();
        StudySubjectTable db = new StudySubjectTable(this);
        StudentTable student = new StudentTable(this);
        int StudygroupID = student.getStudent(StudentLogin).getStudyGroupID();
        List<StudySubject> ss = db.getStudySubjectsFromStudyGroup(StudygroupID);
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
        spinnerStudySubjects.setOnItemSelectedListener(new onStudySubjectClickListenerResultList(this));
    }

    private void fillListViewScores(int ss){
        listViewScores = (ListView) findViewById(R.id.listViewScores);
        ResultsTable res = new ResultsTable(this);
        List<String> resItems = res.getResults(StudentLogin,ss);
        //TODO dodelat seznam znamek at je to fajne a pak uz se na to vyjebat :)
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_2, android.R.id.text2, resItems);

        listViewScores.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results_list, menu);
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
    public Integer getStudySubjectID() {
        return studySubjectID;
    }

    public void setStudySubjectID(Integer studySubjectID) {
        this.studySubjectID = studySubjectID;
        fillListViewScores(studySubjectID);
    }
}

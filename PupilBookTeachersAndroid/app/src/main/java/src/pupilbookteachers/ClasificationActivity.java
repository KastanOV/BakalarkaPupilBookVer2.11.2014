package src.pupilbookteachers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import src.Controls.SpinnerObject;
import src.Controls.onStudySubjectClickListener;
import src.DBAdapter.Result;
import src.DBAdapter.ResultsTable;
import src.DBAdapter.Student;
import src.DBAdapter.StudentTable;
import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;

public class ClasificationActivity extends Activity {
    private String StudentLogin;
    private Integer selectedStudySubject;
    public final static String SHARED_PREFERENCES = "PupilBook";
    private Spinner spinnerStudySubjects;
    protected SharedPreferences sharedpreferences;
    private SeekBar score;
    private EditText description;
    private TextView scoreText;
    private TextView studentNameText;
    private Student editedStudent;
    private Button submitButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasification);
        spinnerStudySubjects = (Spinner) findViewById(R.id.spinnerStudySubject);
        description = (EditText) findViewById(R.id.editDescription);
        submitButton = (Button) findViewById(R.id.buttonActionClassification);
        submitButton.setEnabled(false);
// Find Student
        StudentLogin = getIntent().getStringExtra("selectecStudent");
        StudentTable stt = new StudentTable(this);
        editedStudent = stt.getStudent(StudentLogin);
        sharedpreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        loadSpinnerStudySubjects(StudentLogin);
        startSeekBar();

    }
    public void onClassificationAction(View view){
        Result res = new Result();
        res.settL(sharedpreferences.getString("login", "error geting Sharedpreferences in ClassificationActivity onClassification method"));
        res.setsL(editedStudent.getLogin());
        Time time = new Time();   time.setToNow();
        res.setDate(Long.toString(time.toMillis(false)));
        res.setDesc(description.getText().toString());
        res.setScore(score.getProgress());
        res.setSsId(selectedStudySubject);
        ResultsTable resTable = new ResultsTable(this);
        resTable.createResult(res);
        finish();
    }
    public void startSeekBar(){
        scoreText = (TextView) findViewById(R.id.textScore);
        studentNameText = (TextView) findViewById(R.id.textStudentName);
        studentNameText.setText(editedStudent.getLastName() + " " + editedStudent.getFirstName());
        scoreText.setTextColor(Color.BLACK);
        score = (SeekBar) findViewById(R.id.seekScore);
        score.setMax(9);
        score.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                scoreText.setText(UtilScores.getText(i));
                scoreText.setTextColor(UtilScores.getColor(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                submitButton.setEnabled(true);
            }
        });
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
    private void loadSpinnerStudySubjects(String StudentLogin) {
        List< SpinnerObject > labels = new ArrayList< SpinnerObject >();
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
        spinnerStudySubjects.setOnItemSelectedListener(new onStudySubjectClickListener(this));
    }

    public void setSelectedStudySubject(Integer selectedStudySubject) {
        this.selectedStudySubject = selectedStudySubject;
    }
}

package src.pupilbookteachers;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import src.Controls.SpinnerObject;
import src.Controls.onStudySubjectClickListener;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clasification);
        spinnerStudySubjects = (Spinner) findViewById(R.id.spinnerStudySubject);
        description = (EditText) findViewById(R.id.editDescription);
        StudentLogin = getIntent().getStringExtra("selectecStudent");
        sharedpreferences = getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        loadSpinnerStudySubjects(StudentLogin);
        startSeekBar();
    }

    public void startSeekBar(){
        scoreText = (TextView) findViewById(R.id.textScore);
        scoreText.setTextColor(Color.BLACK);
        score = (SeekBar) findViewById(R.id.seekScore);
        score.setMax(100);
        score.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (i < 10){
                    scoreText.setText("5!");
                    scoreText.setTextColor(Color.rgb(204,0,0));
                } else if(i >= 10 && i < 20) {
                    scoreText.setText("4-");
                    scoreText.setTextColor(Color.rgb(204,0,0));

                } else if(i >= 20 && i < 30){
                    scoreText.setText("4");
                    scoreText.setTextColor(Color.rgb(255,68,68));
                } else if(i >= 30 && i < 40) {
                    scoreText.setText("3-");
                    scoreText.setTextColor(Color.rgb(255,68,68));
                } else if(i >= 40 && i < 50) {
                    scoreText.setText("3");
                    scoreText.setTextColor(Color.rgb(255, 136, 00));
                } else if(i >= 50 && i < 60) {
                    scoreText.setText("2-");
                    scoreText.setTextColor(Color.rgb(255, 136, 00));
                } else if(i >= 60 && i < 70) {
                    scoreText.setText("2");
                    scoreText.setTextColor(Color.rgb(102, 153, 00));
                } else if(i >= 70 && i < 80) {
                    scoreText.setText("1-");
                    scoreText.setTextColor(Color.rgb(102, 153, 00));
                } else if(i >= 80 && i < 90) {
                    scoreText.setText("1");
                    scoreText.setTextColor(Color.rgb(153, 204, 00));
                } else {
                    scoreText.setText("1*");
                    scoreText.setTextColor(Color.rgb(153, 204, 00));
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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

package src.Controls;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

import src.DBAdapter.Student;
import src.DBAdapter.StudentTable;
import src.pupilbookteachers.StudentsActivity;

/**
 * Created by Topr on 11/30/2014.
 */
public class onStudyGroupClickListener implements AdapterView.OnItemSelectedListener {
    private StudentsActivity context;

    public onStudyGroupClickListener(StudentsActivity context){
        this.context = context;
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        SpinnerObject so = (SpinnerObject) parent.getItemAtPosition(position);
        String databaseValue = so.getDatabaseValue();
        if(so.getDatabaseId() != -1){
            loadSpinnerStudents(so.getDatabaseId());
            context.spinnerStudent.setEnabled(true);
        } else context.spinnerStudent.setEnabled(false);

    }
    private void loadSpinnerStudents(int StudyGroup) {

        List< SpinnerObject > labels = new ArrayList< SpinnerObject >();
        StudentTable db = new StudentTable(context);
        List<Student> sg = db.getStudentsAsStudyGroup(StudyGroup);
        labels.add(new SpinnerObject("-1", "Vyberte studenta"));
        for(Student item : sg){
            labels.add(new SpinnerObject(item.getLogin().toString(), item.getLastName() + " " + item.getFirstName()));
        }
        db.close();
        ArrayAdapter<SpinnerObject> dataAdapter = new ArrayAdapter<SpinnerObject>(context,
                android.R.layout.simple_spinner_dropdown_item , labels);
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // attaching data adapter to spinner
        context.spinnerStudent.setAdapter(dataAdapter);
        context.spinnerStudent.setOnItemSelectedListener(new onStudentClickListener(context));
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

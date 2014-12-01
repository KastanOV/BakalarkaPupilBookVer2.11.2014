package src.Controls;

import android.view.View;
import android.widget.AdapterView;

import src.pupilbookteachers.StudentsActivity;

/**
 * Created by Topr on 11/30/2014.
 */
public class onStudentClickListener implements AdapterView.OnItemSelectedListener {
    private StudentsActivity context;

    public onStudentClickListener(StudentsActivity context){
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SpinnerObject so = (SpinnerObject) parent.getItemAtPosition(position);
        String databaseValue = so.getDatabaseValue();
        String Login = so.getLogin();
        context.setSelectedStudent(so.getLogin());
        if(so.getLogin() != "-1"){
            context.buttonClassificatinStudent.setEnabled(true);
            context.buttonAttendance.setEnabled(true);
        } else {
            context.buttonClassificatinStudent.setEnabled(false);
            context.buttonAttendance.setEnabled(false);
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

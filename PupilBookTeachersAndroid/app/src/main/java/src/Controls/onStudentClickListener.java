package src.Controls;

import android.view.View;
import android.widget.AdapterView;

import src.pupilbookteachers.ClasificationActivity;

/**
 * Created by Topr on 11/30/2014.
 */
public class onStudentClickListener implements AdapterView.OnItemSelectedListener {
    private ClasificationActivity context;

    public onStudentClickListener(ClasificationActivity context){
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        SpinnerObject so = (SpinnerObject) parent.getItemAtPosition(position);
        String databaseValue = so.getDatabaseValue();
        String Login = so.getLogin();
        context.SelectedStudent = so.getLogin();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}

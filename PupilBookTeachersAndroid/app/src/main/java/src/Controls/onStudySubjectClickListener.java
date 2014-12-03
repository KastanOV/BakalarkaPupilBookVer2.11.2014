package src.Controls;

import android.view.View;
import android.widget.AdapterView;

import src.pupilbookteachers.ClasificationActivity;

/**
 * Created by KastanNotas on 1.12.2014.
 */
public class onStudySubjectClickListener implements AdapterView.OnItemSelectedListener {
    private ClasificationActivity context;
    public onStudySubjectClickListener(ClasificationActivity context){
        this.context = context;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SpinnerObject so = (SpinnerObject) adapterView.getItemAtPosition(i);
        context.setSelectedStudySubject(so.getDatabaseId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

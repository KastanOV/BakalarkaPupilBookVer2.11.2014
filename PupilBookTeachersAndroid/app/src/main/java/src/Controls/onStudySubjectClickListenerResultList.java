package src.Controls;

import android.view.View;
import android.widget.AdapterView;

import src.pupilbookteachers.ResultsListActivity;

/**
 * Created by KastanNotas on 5.12.2014.
 */
public class onStudySubjectClickListenerResultList implements AdapterView.OnItemSelectedListener {
    private ResultsListActivity context;

    public onStudySubjectClickListenerResultList(ResultsListActivity context){
        this.context = context;
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        SpinnerObject so = (SpinnerObject) adapterView.getItemAtPosition(i);
        context.setStudySubjectID(so.getDatabaseId());
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

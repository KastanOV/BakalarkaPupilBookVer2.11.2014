package src.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import src.DBAdapter.SheduleItem;
import src.DBAdapter.StudyGroup;
import src.DBAdapter.StudyGroupTable;
import src.DBAdapter.StudySubject;
import src.DBAdapter.StudySubjectTable;
import src.pupilbookteachers.R;
import src.pupilbookteachers.UtilScores;

/**
 * Created by Topr on 12/8/2014.
 */
public class sheduleListAdapter extends ArrayAdapter<SheduleItem> {
    private final Context context;
    private final SheduleItem[] values;

    public sheduleListAdapter(Context context, SheduleItem[] values) {
        super(context, R.layout.result_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.shedule_list_item, parent, false);
        TextView textViewHourNumber = (TextView) rowView.findViewById(R.id.sheduleHourNumber);
        TextView textViewStudyGroup = (TextView) rowView.findViewById(R.id.sheduleStudyGroup);
        TextView textViewStudySubject = (TextView) rowView.findViewById(R.id.sheduleStudySubject);

        StudySubjectTable ssTable = new StudySubjectTable(context);
        StudyGroupTable sgTable = new StudyGroupTable(context);

        if(values[position].getIdStudySubject() != -1 && values[position].getIdStudyGroup() != -1){
            StudySubject ss = ssTable.getStudySubject(values[position].getIdStudySubject());
            StudyGroup sg = sgTable.getStudygroup(values[position].getIdStudyGroup());
            textViewHourNumber.setText(String.valueOf(values[position].getHour()+1));
            textViewHourNumber.setTextColor(UtilScores.getColor(0));
            textViewStudyGroup.setText(sg.getName());
            textViewStudyGroup.setTextColor(UtilScores.getColor(2));
            textViewStudySubject.setText(ss.getName());
            textViewStudySubject.setTextColor(UtilScores.getColor(2));
        } else {
            textViewHourNumber.setText(String.valueOf(values[position].getHour()+1));
            textViewHourNumber.setTextColor(UtilScores.getColor(9));
            textViewStudyGroup.setText("Volná");
            textViewStudyGroup.setTextColor(UtilScores.getColor(9));
            textViewStudySubject.setText("Hodina");
            textViewStudySubject.setTextColor(UtilScores.getColor(9));
        }

        return rowView;
    }

}

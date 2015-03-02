package src.Controls;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.DBAdapter.Attendance;
import src.DBAdapter.Result;
import src.pupilbookteachers.R;
import src.pupilbookteachers.UtilScores;

/**
 * Created by Jaroslav on 3/2/2015.
 */
public class attendanceListAdapter extends ArrayAdapter<Attendance> {
    private final Context context;
    private final Attendance[] values;

    public attendanceListAdapter(Context context, Attendance[] values) {
        super(context, R.layout.attendance_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.attendance_list_item, parent, false);
        TextView textStart = (TextView) rowView.findViewById(R.id.startItem);
        TextView textEnd = (TextView) rowView.findViewById(R.id.endText);
        TextView textExcused = (TextView) rowView.findViewById(R.id.excused);

        textEnd.setText(getconvertdate(values[position].getStart()));
        textEnd.setTextColor(values[position].getExcused() ? Color.rgb(153, 204, 00) :Color.rgb(204, 0, 0));
        textStart.setText(values[position].getExcused() ? "O" : "N");
        textStart.setTextColor(values[position].getExcused() ? Color.rgb(153, 204, 00) :Color.rgb(204, 0, 0));
        if(values[position].getEnd() != null){
            textExcused.setText(getconvertdate(values[position].getEnd()));
            textExcused.setTextColor(values[position].getExcused() ? Color.rgb(153, 204, 00) :Color.rgb(204, 0, 0));
        } else {
            textExcused.setText("");
        }
        return rowView;
    }
    public String getconvertdate(String date)
    {
        long dateValue = 0;
        try{
            dateValue = Long.parseLong(date);
        } catch(Exception e){
            return null;
        }

        DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date parsed = new Date(dateValue);

        String outputText = outputFormat.format(parsed);
        return outputText;
    }
    public String getconvertdate(Long date)
    {
        DateFormat outputFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        Date parsed = new Date(date);

        String outputText = outputFormat.format(parsed);
        return outputText;
    }
}

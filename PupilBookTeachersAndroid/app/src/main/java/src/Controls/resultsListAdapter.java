package src.Controls;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import src.DBAdapter.Result;
import src.pupilbookteachers.R;
import src.pupilbookteachers.UtilScores;


/**
 * Created by Topr on 12/5/2014.
 */
public class resultsListAdapter extends ArrayAdapter<Result> {
    private final Context context;
    private final Result[] values;

    public resultsListAdapter(Context context, Result[] values) {
        super(context, R.layout.result_list_item, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.result_list_item, parent, false);
        TextView textViewScore = (TextView) rowView.findViewById(R.id.scoreItem);
        TextView textViewFirstLine = (TextView) rowView.findViewById(R.id.resultsFirstLine);
        TextView textViewSecondLine = (TextView) rowView.findViewById(R.id.resultsSecondLine);

        textViewScore.setText(UtilScores.getText(values[position].getScore()));
        textViewScore.setTextColor(UtilScores.getColor(values[position].getScore()));
        textViewFirstLine.setText(values[position].getDesc());
        textViewSecondLine.setText(getconvertdate(values[position].getDate()));

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

        DateFormat outputFormat = new SimpleDateFormat("dd MM yyyy HH:mm");
        Date parsed = new Date(dateValue);

        String outputText = outputFormat.format(parsed);
        return outputText;
    }

}

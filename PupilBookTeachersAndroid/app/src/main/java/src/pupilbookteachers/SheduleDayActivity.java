package src.pupilbookteachers;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.List;

import src.Controls.sheduleListAdapter;
import src.DBAdapter.SheduleItem;
import src.DBAdapter.SheduleItemTable;


public class SheduleDayActivity extends Activity {
    private ListView listSheduleItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_day);
        int day = Integer.parseInt(getIntent().getStringExtra("selectecDay"));
        fillForm(day);
    }

    private void fillForm(int day){
        listSheduleItems = (ListView) findViewById(R.id.listViewSheduleItems);
        SheduleItem[] tmpItems = new SheduleItem[8];

        SheduleItemTable siTable = new SheduleItemTable(this);
        List<SheduleItem> siList = siTable.getSheduleitemsByDay(day);

        for(int i = 0; i < 8; i++){
            tmpItems[i] = new SheduleItem(null,day,i,-1,-1,null);
        }

        for(int i = 0; i < 8; i++){
            for(SheduleItem item : siList){
                if(item.getHour() == i){
                    tmpItems[i] = item;
                }
            }
        }
        sheduleListAdapter adapter = new sheduleListAdapter(this,tmpItems);
        listSheduleItems.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shedule_day, menu);
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
}

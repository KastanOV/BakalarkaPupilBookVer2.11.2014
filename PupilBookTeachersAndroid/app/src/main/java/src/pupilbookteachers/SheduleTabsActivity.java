package src.pupilbookteachers;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class SheduleTabsActivity extends TabActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shedule_tabs);

        TabHost tabHost = getTabHost();

        TabHost.TabSpec monday = tabHost.newTabSpec("monday");
        monday.setIndicator("Po");
        Intent inMonday = new Intent(this, SheduleDayActivity.class);
        inMonday.putExtra("selectecDay", "0");
        monday.setContent(inMonday);

        TabHost.TabSpec tuesday = tabHost.newTabSpec("tuesday");
        tuesday.setIndicator("Út");
        Intent intuesday = new Intent(this, SheduleDayActivity.class);
        intuesday.putExtra("selectecDay", "1");
        tuesday.setContent(intuesday);

        TabHost.TabSpec wednesday = tabHost.newTabSpec("wednesday");
        wednesday.setIndicator("St");
        Intent inwednesday = new Intent(this, SheduleDayActivity.class);
        inwednesday.putExtra("selectecDay", "2");
        wednesday.setContent(inwednesday);

        TabHost.TabSpec thursday = tabHost.newTabSpec("thursday");
        thursday.setIndicator("Čt");
        Intent inthursday = new Intent(this, SheduleDayActivity.class);
        inthursday.putExtra("selectecDay", "3");
        thursday.setContent(inthursday);

        TabHost.TabSpec friday = tabHost.newTabSpec("friday");

        friday.setIndicator("Pá");
        Intent infriday = new Intent(this, SheduleDayActivity.class);
        infriday.putExtra("selectecDay", "4");
        friday.setContent(infriday);

        tabHost.addTab(monday);
        tabHost.addTab(tuesday);
        tabHost.addTab(wednesday);
        tabHost.addTab(thursday);
        tabHost.addTab(friday);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_shedule_tabs, menu);
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

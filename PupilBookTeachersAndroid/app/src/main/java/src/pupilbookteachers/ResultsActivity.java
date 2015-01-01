package src.pupilbookteachers;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;


public class ResultsActivity extends TabActivity {
    private String SelectedStudent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        SelectedStudent = getIntent().getStringExtra("selectecStudent");
        TabHost tabHost = getTabHost();

        TabHost.TabSpec classification = tabHost.newTabSpec("Classification");

        classification.setIndicator("Klasifikace");
        Intent in = new Intent(this, ClasificationActivity.class);
        in.putExtra("selectecStudent", SelectedStudent);
        classification.setContent(in);

        TabHost.TabSpec resultsList = tabHost.newTabSpec("ResultsList");

        resultsList.setIndicator("Výsledky");
        Intent inResultsList = new Intent(this, ResultsListActivity.class);
        inResultsList.putExtra("selectecStudent", SelectedStudent);
        resultsList.setContent(inResultsList);

        tabHost.addTab(classification);
        tabHost.addTab(resultsList);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_results, menu);
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

package quake.codecops.in.quakelive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthQuackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquack_activity);

        //create fake data list
        ArrayList<QuakData> earthquack = new ArrayList<QuakData>();
        /*
        earthquack.add(new QuakData("7.5","India","Feb,22"));
        earthquack.add(new QuakData("2","London","Feb,22"));
        earthquack.add(new QuakData("4.2","Tokyo","Feb,22"));
        earthquack.add(new QuakData("5.5","Paris","Feb,22"));
        earthquack.add(new QuakData("3.5","Mexico Citi","Feb,22"));*/
        earthquack = QueryUtils.extractEarthquakes();


        ListView earthquaklListView = (ListView) findViewById(R.id.list);

        //new array adapter
        QuackAdapter adapter = new QuackAdapter(
                this,earthquack);

        earthquaklListView.setAdapter(adapter);
    }
}

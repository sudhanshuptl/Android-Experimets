package quake.codecops.in.quakelive;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class EarthQuackActivity extends AppCompatActivity {
    public static final String QUERY_URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=5&limit=10";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquack_activity);

        //
        //ArrayList<QuakData> earthquack = new ArrayList<QuakData>();
        /*
        earthquack.add(new QuakData("7.5","India","Feb,22"));
        earthquack.add(new QuakData("2","London","Feb,22"));
        earthquack.add(new QuakData("4.2","Tokyo","Feb,22"));
        earthquack.add(new QuakData("5.5","Paris","Feb,22"));
        earthquack.add(new QuakData("3.5","Mexico Citi","Feb,22"));*/
        QuackAsynkTask task = new QuackAsynkTask();
        task.execute(QUERY_URL);
    }

    private void updateUI(ArrayList<QuakData> earthquack) {
        if (earthquack != null) {
            ListView earthquaklListView = (ListView) findViewById(R.id.list);

            //new array adapter
            QuackAdapter adapter = new QuackAdapter(
                    this, earthquack);

            earthquaklListView.setAdapter(adapter);
        } else {
            //// TODO: for null case
            Log.e("NOdata","No data fetched");
        }
    }
    private  class QuackAsynkTask extends AsyncTask<String,Void,ArrayList<QuakData>>{

        @Override
        protected ArrayList<QuakData> doInBackground(String... params) {
            ArrayList<QuakData> result = QueryUtils.extractEarthquakes(params[0]);
            return result;
        }

        @Override
        protected void onPostExecute(ArrayList<QuakData> result) {
            updateUI(result);
        }
    }

}


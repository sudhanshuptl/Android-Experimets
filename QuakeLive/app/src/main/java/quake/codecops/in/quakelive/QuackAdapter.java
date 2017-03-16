package quake.codecops.in.quakelive;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.app.Activity;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 12/3/17.
 */
public class QuackAdapter extends ArrayAdapter<QuakData> {

    public QuackAdapter(Activity context, ArrayList<QuakData> data){
        super(context,0, data);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //fetch object at given position in array
         QuakData currentWord = getItem(position);

        //check if the existing view is being reused otherse use it
        View listItemview = convertView;
        if(listItemview == null){
            listItemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }

        TextView quackLocation = (TextView) listItemview.findViewById(R.id.location);
        //set values
        quackLocation.setText(currentWord.getLocation());
        Log.e("location",currentWord.getLocation());

        //find textView
        TextView quackMagnitude = (TextView) listItemview.findViewById(R.id.magnitude);
        //set value to text view
        quackMagnitude.setText(String.format("%1$,.1f", currentWord.getMagnitude()));
        //Log.v("Magnitude",currentWord.getMagnitude());

        //add image view
        TextView quackDate = (TextView) listItemview.findViewById(R.id.date);
        //set values
        quackDate.setText(Long.toString(currentWord.getDate()));
        //Log.v("date",currentWord.getDate());
        return listItemview;
    }
}

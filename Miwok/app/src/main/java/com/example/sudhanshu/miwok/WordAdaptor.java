package com.example.sudhanshu.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sudhanshu on 27/2/17.
 */

public class WordAdaptor extends ArrayAdapter<Words> {
    private int mColorResourceId;

    public WordAdaptor(Activity context, ArrayList<Words> words,int colorId) {
        super(context,0, words);
        mColorResourceId = colorId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //fetch object at given position in array
        Words currentWord = getItem(position);

        //check if the existing view is being reused otherse use it
        View listItemview = convertView;
        if(listItemview == null){
            listItemview = LayoutInflater.from(getContext()).inflate(R.layout.list_item,parent,false);
        }
        //find textView
        TextView wordrtextView = (TextView) listItemview.findViewById(R.id.miwok_text_view);
        //set value to text view
        wordrtextView.setText(currentWord.getMiwokTranslation());

        TextView wordDefaultTextView = (TextView) listItemview.findViewById(R.id.default_text_view);
        //set values
        wordDefaultTextView.setText(currentWord.getDefaultTranslation());

        //add image view
        ImageView wordImageView = (ImageView) listItemview.findViewById(R.id.image_view);

        if(currentWord.hasImage() == true) {
            //set values
            wordImageView.setImageResource(currentWord.getImageAsset());
            //return super.getView(position, convertView, parent);
        }
        else{
            //hide if no resourse
            wordImageView.setVisibility(View.GONE);
        }
        /*Set custom color */
        //set theme color for list item
        View textContainer = listItemview.findViewById(R.id.text_container);
        // Find color that the resource map to
        int color = ContextCompat.getColor(getContext(),mColorResourceId);
        // set background color
        textContainer.setBackgroundColor(color);

        return listItemview;
    }
}

package com.example.sudhanshu.miwok;


import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import static com.example.sudhanshu.miwok.FamilyActivity.mediaPlayer;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        final  ArrayList<Words>  words = new ArrayList<Words>();
        words.add(new Words("One","lutti",R.drawable.number_one,R.raw.number_one));
        words.add(new Words("Two","otiiko",R.drawable.number_two,R.raw.number_two));
        words.add(new Words("Three","tolookosu",R.drawable.number_three,R.raw.number_three));
        words.add(new Words("Four","oyyisa",R.drawable.number_four,R.raw.number_four));
        words.add(new Words("Five","massokka",R.drawable.number_five,R.raw.number_five));
        words.add(new Words("Six","temmokka",R.drawable.number_six,R.raw.number_six));
        words.add(new Words("Seven","kenekaku",R.drawable.number_seven,R.raw.number_seven));
        words.add(new Words("Eight","kawinta",R.drawable.number_eight,R.raw.number_eight));
        words.add(new Words("Nine","wo'e",R.drawable.number_nine,R.raw.number_nine));
        words.add(new Words("Ten","na'aacha",R.drawable.number_ten,R.raw.number_ten));


        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);

        //add music
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = words.get(position);
                mediaPlayer = MediaPlayer.create(NumbersActivity.this,word.getmAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}

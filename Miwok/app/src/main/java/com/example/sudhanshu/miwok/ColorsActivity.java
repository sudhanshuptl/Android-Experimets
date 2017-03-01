package com.example.sudhanshu.miwok;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

import static com.example.sudhanshu.miwok.FamilyActivity.mediaPlayer;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("red","wetetti",R.drawable.color_red,R.raw.color_red));
        words.add(new Words("mustard yellow","chiwiite",R.drawable.color_mustard_yellow,R.raw.color_mustard_yellow));
        words.add(new Words("dusty yellow","topiise",R.drawable.color_dusty_yellow,R.raw.color_dusty_yellow));
        words.add(new Words("green","chokokki",R.drawable.color_green,R.raw.color_green));
        words.add(new Words("gray","topoppi",R.drawable.color_gray,R.raw.color_gray));
        words.add(new Words("black","kululli",R.drawable.color_black,R.raw.color_black));
        words.add(new Words("Brown","kenekaku",R.drawable.color_brown,R.raw.color_brown));
        words.add(new Words("white","kelelli",R.drawable.color_white,R.raw.color_white));


        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);
        //add music
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = words.get(position);
                mediaPlayer = MediaPlayer.create(ColorsActivity.this,word.getmAudioResourceId());
                mediaPlayer.start();
            }
        });
    }
}

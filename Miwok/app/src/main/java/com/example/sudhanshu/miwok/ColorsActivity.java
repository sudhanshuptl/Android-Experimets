package com.example.sudhanshu.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("red","wetetti",R.drawable.color_red));
        words.add(new Words("mustard yellow","chiwiite",R.drawable.color_mustard_yellow));
        words.add(new Words("dusty yellow","topiise",R.drawable.color_dusty_yellow));
        words.add(new Words("green","chokokki",R.drawable.color_green));
        words.add(new Words("gray","topoppi",R.drawable.color_gray));
        words.add(new Words("black","kululli",R.drawable.color_black));
        words.add(new Words("Brown","kenekaku",R.drawable.color_brown));
        words.add(new Words("white","kelelli",R.drawable.color_white));


        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_colors);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);
    }
}

package com.example.sudhanshu.miwok;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        ArrayList<Words>  words = new ArrayList<Words>();
        words.add(new Words("Zero","lutti",R.drawable.color_brown));
        words.add(new Words("One","lutti",R.drawable.number_one));
        words.add(new Words("Two","otiiko",R.drawable.number_two));
        words.add(new Words("Three","oyyisa",R.drawable.number_three));
        words.add(new Words("Four","massokka",R.drawable.number_four));
        words.add(new Words("Five","temmokka",R.drawable.number_five));
        words.add(new Words("Five","kenekaku",R.drawable.number_six));
        words.add(new Words("Seven","kawinta",R.drawable.number_seven));
        words.add(new Words("Eight","wo'e",R.drawable.number_eight));
        words.add(new Words("Nine","wo'e",R.drawable.number_nine));
        words.add(new Words("Ten","na'aacha",R.drawable.number_ten));


        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_numbers);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);
    }
}


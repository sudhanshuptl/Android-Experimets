package com.example.sudhanshu.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class PhrasesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("Where are you going?","minto wuksus"));
        words.add(new Words("What is your name?","tinn ә oyaase'n ә"));
        words.add(new Words("My name is...","oyaaset..."));
        words.add(new Words("How are you feeling?","michәksәs?"));
        words.add(new Words("I’m feeling good.","kuchi achit"));
        words.add(new Words("Are you coming?","әәnәs'aa?"));
        words.add(new Words("Yes, I’m coming.","h әә ’ әә n ә m"));
        words.add(new Words("I’m coming.","әә n ә m"));
        words.add(new Words("Eight","wo'e"));
        words.add(new Words("Let’s go.","yoowutis"));
        words.add(new Words("Come here.","ә nni'nem"));


        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_phrases);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);
    }
}

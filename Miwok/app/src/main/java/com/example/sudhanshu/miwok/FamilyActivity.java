package com.example.sudhanshu.miwok;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        ArrayList<Words>  words = new ArrayList<Words>();
        words.add(new Words("father","әpә",R.drawable.family_father));
        words.add(new Words("mother","әṭa",R.drawable.family_mother));
        words.add(new Words("son","angsi",R.drawable.family_son));
        words.add(new Words("daughter","tune",R.drawable.family_daughter));
        words.add(new Words("older brother","taachi",R.drawable.family_older_brother));
        words.add(new Words("younger brother","chalitti",R.drawable.family_younger_brother));
        words.add(new Words("older sister","teṭe",R.drawable.family_older_sister));
        words.add(new Words("younger sister","kolliti",R.drawable.family_younger_sister));
        words.add(new Words("grandmother","ama",R.drawable.family_grandmother));
        words.add(new Words("grandfather","paapa",R.drawable.family_grandfather));



        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);
    }
}

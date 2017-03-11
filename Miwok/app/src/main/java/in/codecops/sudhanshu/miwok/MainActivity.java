package com.example.sudhanshu.miwok;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // FInd the view that shows number category
        TextView numbers = (TextView)findViewById(R.id.numbers);
        // set clickListner on that view
        numbers.setOnClickListener(new View.OnClickListener() {
            //define method of interface  OnClickListner
            //this tech is inline class def
            @Override
            public void onClick(View view) {
                Intent numberIntent= new Intent(MainActivity.this,NumbersActivity.class);
                startActivity(numberIntent);
            }
        });

        // FInd the view that shows family category
        TextView family = (TextView)findViewById(R.id.family);
        // set clickListner on that view
        family.setOnClickListener(new View.OnClickListener() {
            //define method of interface  OnClickListner
            //this tech is inline class def
            @Override
            public void onClick(View view) {
                Intent familyIntent= new Intent(MainActivity.this,FamilyActivity.class);
                startActivity(familyIntent);
            }
        });

        // FInd the view that shows color category
        TextView color = (TextView)findViewById(R.id.colors);
        // set clickListner on that view
        color.setOnClickListener(new View.OnClickListener() {
            //define method of interface  OnClickListner
            //this tech is inline class def
            @Override
            public void onClick(View view) {
                Intent colorIntent= new Intent(MainActivity.this,ColorsActivity.class);
                startActivity(colorIntent);
            }
        });

        // FInd the view that shows phrases category
        TextView phrases = (TextView)findViewById(R.id.phrases);
        // set clickListner on that view
        phrases.setOnClickListener(new View.OnClickListener() {
            //define method of interface  OnClickListner
            //this tech is inline class def
            @Override
            public void onClick(View view) {
                Intent phrasesIntent= new Intent(MainActivity.this,PhrasesActivity.class);
                startActivity(phrasesIntent);
            }
        });



    }

}

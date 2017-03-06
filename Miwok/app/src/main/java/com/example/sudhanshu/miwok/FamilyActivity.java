package com.example.sudhanshu.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;

public class FamilyActivity extends AppCompatActivity {
    private MediaPlayer.OnCompletionListener mCompleteListner = new MediaPlayer.OnCompletionListener(){
        @Override
        public void onCompletion(MediaPlayer mp) {
            releaseMediaPlayer();
        }
    };
    private AudioManager mAudioManager;
    public static MediaPlayer mediaPlayer;

    AudioManager.OnAudioFocusChangeListener mOnAudioChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT){
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    }else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                        //resume playback
                        mediaPlayer.start();
                    }else if (focusChange == AudioManager.AUDIOFOCUS_LOSS){
                        //mAudioManager.unregisterMediaButtonEventReceiver(RemoteControlRecever);
                        //mAudioManager.abandonAudioFocus(mOnAudioChangeListner);
                        releaseMediaPlayer();
                        //stop playback
                    }
                }
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);


        //create and setup audio manager
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words>  words = new ArrayList<Words>();
        words.add(new Words("father","әpә",R.drawable.family_father,R.raw.family_father));
        words.add(new Words("mother","әṭa",R.drawable.family_mother,R.raw.family_mother));
        words.add(new Words("son","angsi",R.drawable.family_son,R.raw.family_son));
        words.add(new Words("daughter","tune",R.drawable.family_daughter,R.raw.family_daughter));
        words.add(new Words("older brother","taachi",R.drawable.family_older_brother,R.raw.family_older_brother));
        words.add(new Words("younger brother","chalitti",R.drawable.family_younger_brother,R.raw.family_younger_brother));
        words.add(new Words("older sister","teṭe",R.drawable.family_older_sister,R.raw.family_older_sister));
        words.add(new Words("younger sister","kolliti",R.drawable.family_younger_sister,R.raw.family_younger_sister));
        words.add(new Words("grandmother","ama",R.drawable.family_grandmother,R.raw.family_grandmother));
        words.add(new Words("grandfather","paapa",R.drawable.family_grandfather,R.raw.family_grandfather));



        WordAdaptor adaptor= new WordAdaptor(this,words,R.color.category_family);

        ListView listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adaptor);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                //release media player before playing new media , in case previous media is playing
                releaseMediaPlayer();


                Words word = words.get(position);

                int result = mAudioManager.requestAudioFocus(mOnAudioChangeListner,
                        //use Music stream
                        AudioManager.STREAM_MUSIC,
                        //requst permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(FamilyActivity.this, word.getmAudioResourceId());
                    mediaPlayer.start();

                    //relese media after complete sound file
                    mediaPlayer.setOnCompletionListener(mCompleteListner);
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }

    private  void releaseMediaPlayer(){
        if(mediaPlayer !=null){
            mediaPlayer.release();
            mediaPlayer=null;

            //abandonaudio focus
            mAudioManager.abandonAudioFocus(mOnAudioChangeListner);



        }
    }
}

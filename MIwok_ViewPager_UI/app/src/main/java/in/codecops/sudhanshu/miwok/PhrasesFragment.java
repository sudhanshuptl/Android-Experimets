package in.codecops.sudhanshu.miwok;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class PhrasesFragment extends Fragment {

    public  MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    public PhrasesFragment() {
        // Required empty public constructor
    }

    private MediaPlayer.OnCompletionListener mCompleteListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };



    AudioManager.OnAudioFocusChangeListener mOnAudioChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        //create and setup audio manager
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words> words = new ArrayList<Words>();
        words.add(new Words("Where are you going?","minto wuksus",R.raw.phrase_where_are_you_going));
        words.add(new Words("What is your name?","tinn ә oyaase'n ә",R.raw.phrase_what_is_your_name));
        words.add(new Words("My name is...","oyaaset...",R.raw.phrase_my_name_is));
        words.add(new Words("How are you feeling?","michәksәs?",R.raw.phrase_how_are_you_feeling));
        words.add(new Words("I’m feeling good.","kuchi achit",R.raw.phrase_im_feeling_good));
        words.add(new Words("Are you coming?","әәnәs'aa?",R.raw.phrase_are_you_coming));
        words.add(new Words("Yes, I’m coming.","h әә ’ әә n ә m",R.raw.phrase_yes_im_coming));
        words.add(new Words("I’m coming.","әә n ә m",R.raw.phrase_im_coming));
        words.add(new Words("Let’s go.","yoowutis",R.raw.phrase_lets_go));
        words.add(new Words("Come here.","ә nni'nem",R.raw.phrase_come_here));


        WordAdaptor adaptor= new WordAdaptor(getActivity(),words,R.color.category_phrases);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adaptor);

        //add music
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
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getmAudioResourceId());
                    mediaPlayer.start();

                    //relese media after complete sound file
                    mediaPlayer.setOnCompletionListener(mCompleteListner);
                }

            }
        });
        return rootView;
    }





    @Override
    public void onStop() {
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

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
import android.widget.TextView;

import java.util.ArrayList;



/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {

    public  MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    public FamilyFragment() {
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



        WordAdaptor adaptor= new WordAdaptor(getActivity(),words,R.color.category_family);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
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

package in.codecops.sudhanshu.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
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
 * Activities that contain this fragment must implement the
 * {@link NumbersFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NumbersFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersFragment extends Fragment {
    public  MediaPlayer mediaPlayer;
    private AudioManager mAudioManager;

    public NumbersFragment() {
        // Required empty public constructor
    }

    AudioManager.OnAudioFocusChangeListener mOnAudioChangeListner =
            new AudioManager.OnAudioFocusChangeListener() {
                @Override
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        //resume playback
                        mediaPlayer.start();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        //mAudioManager.unregisterMediaButtonEventReceiver(RemoteControlRecever);
                        //mAudioManager.abandonAudioFocus(mOnAudioChangeListner);
                        releaseMediaPlayer();
                        //stop playback
                    }
                }
            };


    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
            //abandonaudio focus
            mAudioManager.abandonAudioFocus(mOnAudioChangeListner);

        }
    }

    private MediaPlayer.OnCompletionListener mCompleteListner = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            // Now that the sound file has finished playing, release the media player resources.
            releaseMediaPlayer();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);

        //create and setup audio manager
        mAudioManager = (AudioManager) getActivity().getSystemService(Context.AUDIO_SERVICE);

        final ArrayList<Words> words = new ArrayList<Words>();
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


        WordAdaptor adaptor= new WordAdaptor(getActivity(),words,R.color.category_numbers);

        ListView listView = (ListView) rootView.findViewById(R.id.list_view);
        listView.setAdapter(adaptor);

        //add music
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Words word = words.get(position);
                //release media player before playing new media , in case previous media is playing
                releaseMediaPlayer();

                //request audio focus
                int result = mAudioManager.requestAudioFocus(mOnAudioChangeListner,
                        //use Music stream
                        AudioManager.STREAM_MUSIC,
                        //requst permanent focus
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    // mAudioManager.registerMediaButtonEventReceiver(RemoteControlRecever);
                    // start playback
                    mediaPlayer = MediaPlayer.create(getActivity(),word.getmAudioResourceId());
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

    @Override
    public void onPause() {
        super.onPause();
        releaseMediaPlayer();
    }
}
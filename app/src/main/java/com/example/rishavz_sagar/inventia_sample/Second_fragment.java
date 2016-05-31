package com.example.rishavz_sagar.inventia_sample;

import android.app.Fragment;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;


public class Second_fragment extends Fragment {

    ImageButton btn_play,btn_next,btn_prev;
    TextView title_label;

    MediaPlayer mp;
    ArrayList<String> list;

    private int currentIndex=0;
    private int cpos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Bundle arg=getArguments();
        if(arg!=null)
            cpos=arg.getInt("position");
        Log.v("ass",""+cpos);
        View v=inflater.inflate(R.layout.fragment_second_fragment, container, false);
        btn_play= (ImageButton) v.findViewById(R.id.btnPlay);

        btn_next= (ImageButton) v.findViewById(R.id.btnNext);
        btn_prev= (ImageButton) v.findViewById(R.id.btnBackward);
        title_label= (TextView) v.findViewById(R.id.songTitle);

        list=new ArrayList<>();
        list.add("http://programmerguru.com/android-tutorial/wp-content/uploads/2013/04/hosannatelugu.mp3");
        list.add("https://www.hrupin.com/wp-content/uploads/mp3/testsong_20_sec.mp3");

        mp=new MediaPlayer();

        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                if (currentIndex < (list.size() - 1)) {
                    play_song(currentIndex + 1);
                    ++currentIndex;
                } else {
                    currentIndex = 0;
                    play_song(currentIndex);
                }
            }

        });

        btn_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    if (mp != null) {
                        mp.pause();
                        btn_play.setBackgroundResource(R.drawable.bfzn_004);
                    }
                }
                else {
                    Log.d("de", "Start called");
                    mp.start();
                    btn_play.setBackgroundResource(R.drawable.bfzn_003);
                }
            }
        });


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex<(list.size()-1))
                {
                    play_song(currentIndex+1);
                    ++currentIndex;
                }
                else {
                    play_song(0);
                    currentIndex=0;
                }
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentIndex > 0) {
                    play_song(currentIndex-1);
                    --currentIndex;
                } else {
                    play_song(list.size()-1);
                    currentIndex = list.size()-1;
                }
            }
        });

        play_song(cpos);
        return v;
    }

    public void play_song(int pos)
    {
        try {
            if(mp!=null) {
                mp.reset();
                mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mp.setDataSource(getActivity(), Uri.parse(list.get(pos)));
                mp.prepare();
                mp.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mp!=null)
            mp.release();
    }

}

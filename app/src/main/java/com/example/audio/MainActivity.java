package com.example.audio;

import androidx.appcompat.app.AppCompatActivity;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
ImageButton play,pause,stop;
    MediaPlayer mediaPlayer;
    SeekBar volControl;
    AudioManager audioManager;
    SeekBar seek;
    int max,curr;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play=(ImageButton)findViewById(R.id.btnplay);
        pause=(ImageButton)findViewById(R.id.btnpause);
        stop=(ImageButton)findViewById(R.id.btnstop);

        mediaPlayer=MediaPlayer.create(this,R.raw.audio);
        volControl=(SeekBar)findViewById(R.id.volcon);
        audioManager=(AudioManager)getSystemService(AUDIO_SERVICE);
        max=audioManager.getStreamMaxVolume(audioManager.STREAM_MUSIC);
        curr=audioManager.getStreamVolume(audioManager.STREAM_MUSIC);
          volControl.setMax(max);
          volControl.setProgress(curr);
        volControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
            audioManager.setStreamVolume(audioManager.STREAM_MUSIC,i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    seek=(SeekBar)findViewById(R.id.seek);
     seek.setMax(mediaPlayer.getDuration());
     new Timer().scheduleAtFixedRate(new TimerTask() {
         @Override
         public void run()
         {
             seek.setProgress(mediaPlayer.getCurrentPosition());
         }
     },0,1000);
     seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
         @Override
         public void onProgressChanged(SeekBar seekBar, int i, boolean b)
         {
             if(b)
         mediaPlayer.seekTo(i);

         }

         @Override
         public void onStartTrackingTouch(SeekBar seekBar) {

         }

         @Override
         public void onStopTrackingTouch(SeekBar seekBar) {

         }
     });

        play.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view)
           {
                mediaPlayer.start();
           }
       });
        pause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
            mediaPlayer.pause();
            }
        });
    stop.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view)
        {
        mediaPlayer.stop();
        }
    });

    }


}

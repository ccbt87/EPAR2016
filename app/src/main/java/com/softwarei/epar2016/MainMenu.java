package com.softwarei.epar2016;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MainMenu extends AppCompatActivity {
    MusicPlayer mp;
    boolean click = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Intent intent = getIntent();
        int index = intent.getIntExtra("index", 0);

        mp = new MusicPlayer();
        final Intent music = new Intent(getApplication(), MusicPlayer.class);
       if (index == 2)
       {
           stopService(music);
       }
        music.putExtra("index", 0);
        startService(music);

        ImageButton button,settingsB;
        button=(ImageButton)findViewById(R.id.HighScoresMenu);
        button.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                click = true;
                Intent hs = new Intent(MainMenu.this, HighScores.class);
                hs.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                hs.putExtras(music);
                startActivity(hs);
                finish();
            }

        });


        settingsB=(ImageButton)findViewById(R.id.go_settings);
        settingsB.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                click = true;
                Intent settings = new Intent(MainMenu.this, Settings.class);
                settings.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                settings.putExtras(music);
                startActivity(settings);
                finish();
            }

        });

        Button play;
        play=(Button)findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                click = true;
                Intent characterSelection = new Intent(MainMenu.this, CharacterSelection.class);
                characterSelection.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(characterSelection);
                finish();
            }

        });

    }

    @Override
    public void onBackPressed() {
        stopService(new Intent(MainMenu.this, MusicPlayer.class));
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(!click)
            mp.onPause();;
    }

    @Override
    protected void onResume() {
        super.onResume();
        startService(new Intent(MainMenu.this, MusicPlayer.class));
    }
}

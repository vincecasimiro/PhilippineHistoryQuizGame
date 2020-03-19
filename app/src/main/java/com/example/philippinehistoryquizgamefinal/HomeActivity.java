package com.example.philippinehistoryquizgamefinal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    //MAIN MENU//
    HomeWatcher mHomeWatcher;
    Button btnStart, btnAbout, btnQuit, btnInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Bind music service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);

        btnStart = (Button) findViewById(R.id.btnStart);
        btnAbout = (Button) findViewById(R.id.btnAbout);
        btnQuit = (Button) findViewById(R.id.btnQuit);
        btnInstructions = (Button) findViewById(R.id.btnInstructions);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, GameListActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AboutActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });
        btnInstructions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, InstructionsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        mHomeWatcher = new HomeWatcher(this);
        mHomeWatcher.setOnHomePressedListener(new HomeWatcher.OnHomePressedListener() {
            @Override
            public void onHomePressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }

            @Override
            public void onHomeLongPressed() {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        });
        mHomeWatcher.startWatch();
    }

        private boolean mIsBound = false;
        private MusicService mServ;
        private ServiceConnection Scon = new ServiceConnection() {

            public void onServiceConnected(ComponentName name, IBinder
                    binder) {
                mServ = ((MusicService.ServiceBinder) binder).getService();
            }

            public void onServiceDisconnected(ComponentName name) {
                mServ = null;
            }
        };

        void doBindService () {
            bindService(new Intent(this, MusicService.class),
                    Scon, Context.BIND_AUTO_CREATE);
            mIsBound = true;
        }

        void doUnbindService ()
        {
            if (mIsBound) {
                unbindService(Scon);
                mIsBound = false;
            }
        }

        @Override
        protected void onResume () {
            super.onResume();

            if (mServ != null) {
                mServ.resumeMusic();
            }
        }

        @Override
        protected void onPause () {
            super.onPause();

            PowerManager pm = (PowerManager)
                    getSystemService(Context.POWER_SERVICE);
            boolean isScreenOn = false;
            if (pm != null) {
                isScreenOn = pm.isScreenOn();
            }

            if (!isScreenOn) {
                if (mServ != null) {
                    mServ.pauseMusic();
                }
            }
        }

        @Override
        protected void onDestroy () {
            super.onDestroy();

            //UNBIND music service
            doUnbindService();
            Intent music = new Intent();
            music.setClass(this, MusicService.class);
            stopService(music);
        }
}

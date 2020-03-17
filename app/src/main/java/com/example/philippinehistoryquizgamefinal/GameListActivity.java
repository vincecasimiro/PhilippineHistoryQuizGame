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

public class GameListActivity extends AppCompatActivity {

    //Games List//
    HomeWatcher mHomeWatcher;
    Button btnQuizBee, btnLocateMe, btnIdentifyMe, btnFITB, btnMysteryRound, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        //BIND music service
        doBindService();
        Intent music = new Intent();
        music.setClass(this, MusicService.class);
        startService(music);


        btnQuizBee = (Button) findViewById(R.id.btnQuizBee);
        btnLocateMe = (Button) findViewById(R.id.btnLocateMe);
        btnIdentifyMe = (Button) findViewById(R.id.btnIdentifyMe);
        btnFITB = (Button) findViewById(R.id.btnFITB);
        btnMysteryRound = (Button) findViewById(R.id.btnMysteryRound);
        btnMainMenu = (Button) findViewById(R.id.btnMainMenu);

        btnQuizBee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, QuizBeeSelectLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnLocateMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, LocateMeSelectLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnIdentifyMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, IdentifyMeSelectLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnFITB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, FITBSelectLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnMysteryRound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, MysteryRoundSelectLevelActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GameListActivity.this, HomeActivity.class);
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
    private ServiceConnection Scon =new ServiceConnection(){

        public void onServiceConnected(ComponentName name, IBinder
                binder) {
            mServ = ((MusicService.ServiceBinder)binder).getService();
        }

        public void onServiceDisconnected(ComponentName name) {
            mServ = null;
        }
    };

    void doBindService(){
        bindService(new Intent(this,MusicService.class),
                Scon, Context.BIND_AUTO_CREATE);
        mIsBound = true;
    }

    void doUnbindService()
    {
        if(mIsBound)
        {
            unbindService(Scon);
            mIsBound = false;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mServ != null) {
            mServ.resumeMusic();
        }
    }

    @Override
    protected void onPause() {
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
    protected void onDestroy() {
        super.onDestroy();

        //UNBIND music service
        doUnbindService();
        Intent music = new Intent();
        music.setClass(this,MusicService.class);
        stopService(music);
    }
}

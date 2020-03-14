package com.example.philippinehistoryquizgamefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class GameListActivity extends AppCompatActivity {

    //Games List//

    Button btnQuizBee, btnLocateMe, btnIdentifyMe, btnFITB, btnMysteryRound, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

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
    }
}

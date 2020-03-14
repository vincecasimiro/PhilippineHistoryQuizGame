package com.example.philippinehistoryquizgamefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MysteryRoundSelectLevelActivity extends AppCompatActivity {

    //Games List//

    Button btnPHHistoryRelated, btnPUPRelated, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystery_round_select);

        btnPHHistoryRelated = (Button) findViewById(R.id.btnPHHistoryRelated);
        btnPUPRelated = (Button) findViewById(R.id.btnPUPRelated);
        btnMainMenu = (Button) findViewById(R.id.btnMainMenu);

        btnPHHistoryRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MysteryRoundSelectLevelActivity.this, QuestionActivityPHHistoryMysteryRound.class);
                startActivity(intent);
                finish();
            }
        });
        btnPUPRelated.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MysteryRoundSelectLevelActivity.this, QuestionActivityPUPRelatedMysteryRound.class);
                startActivity(intent);
                finish();
            }
        });
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MysteryRoundSelectLevelActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

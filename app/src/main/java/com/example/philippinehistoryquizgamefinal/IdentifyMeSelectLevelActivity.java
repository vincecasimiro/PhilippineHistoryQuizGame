package com.example.philippinehistoryquizgamefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class IdentifyMeSelectLevelActivity extends AppCompatActivity {

    //Games List//

    Button btnEasy, btnAverage, btnDifficult, btnMainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identify_me_select);

        btnEasy = (Button) findViewById(R.id.btnEasy);
        btnAverage = (Button) findViewById(R.id.btnAverage);
        btnDifficult = (Button) findViewById(R.id.btnDifficult);
        btnMainMenu = (Button) findViewById(R.id.btnMainMenu);

        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyMeSelectLevelActivity.this, QuestionActivityIdentifyMe.class);
                startActivity(intent);
                finish();
            }
        });
        btnAverage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyMeSelectLevelActivity.this, QuestionActivityIdentifyMeAverage.class);
                startActivity(intent);
                finish();
            }
        });
        btnDifficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyMeSelectLevelActivity.this, QuestionActivityIdentifyMeDifficult.class);
                startActivity(intent);
                finish();
            }
        });
        btnMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IdentifyMeSelectLevelActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
package com.example.philippinehistoryquizgamefinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    //MAIN MENU//

    Button btnStart, btnAbout, btnQuit, btnInstructions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

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
    }
}

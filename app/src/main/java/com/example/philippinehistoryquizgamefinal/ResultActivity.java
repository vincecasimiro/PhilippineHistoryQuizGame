package com.example.philippinehistoryquizgamefinal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        TextView textResult = (TextView) findViewById(R.id.textResult);
        Bundle b = getIntent().getExtras();
        int score = b.getInt("score");
        textResult.setText("Your score is " + " " + score + ". Thanks for playing the game.");
    }
    public void playagain(View o) {
        Intent intent = new Intent(this, GameListActivity.class);
        startActivity(intent);
    }

    public void mainmenu(View o) {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }
}

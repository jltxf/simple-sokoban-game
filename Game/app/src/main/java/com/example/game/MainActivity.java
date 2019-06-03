package com.example.game;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button bta_game_black;
    Button bta_game_info, bta_start_game;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvents();
    }

    private void initEvents() {
        bta_game_black.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bta_game_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameInfoActivity.class);
                startActivity(intent);
            }
        });

        bta_start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LevelActivity.class);
                startActivity(intent );
            }
        });
    }

    private void initView() {
        bta_game_black = (Button) findViewById(R.id.bta_game_black);
        bta_game_info = (Button) findViewById(R.id.bta_game_info);
        bta_start_game = (Button) findViewById(R.id.bta_start_game);


    }
}

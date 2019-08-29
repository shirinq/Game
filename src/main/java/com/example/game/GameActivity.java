package com.example.game;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import com.example.game.Fragments.TicTacToeFragment;
import com.example.game.Fragments.inRowFragment;

public class GameActivity extends AppCompatActivity {
    Button TicTacToe;
    Button InaRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TicTacToe = findViewById(R.id.Btn_TicTacToe);
        InaRow = findViewById(R.id.btn_4inRow);

        final FragmentManager mfragment = getSupportFragmentManager();

        TicTacToe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfragment.beginTransaction()
                        .replace(R.id.container_layout, new TicTacToeFragment())
                        .commit();
            }
        });

        InaRow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mfragment.beginTransaction()
                        .replace(R.id.container_layout, new inRowFragment())
                        .commit();
            }
        });
    }

}

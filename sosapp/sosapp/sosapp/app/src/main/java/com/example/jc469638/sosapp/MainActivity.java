package com.example.jc469638.sosapp;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        defineButtons();
    }

    public void defineButtons() {
        findViewById(R.id.message).setOnClickListener(buttonClickListener);
        findViewById(R.id.emergencycall).setOnClickListener(buttonClickListener);
        findViewById(R.id.emergencycontact).setOnClickListener(buttonClickListener);
        findViewById(R.id.exit).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.message:
                    Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                    startActivity(intent);
                    break;
                case R.id.emergencycall:
                    Intent intent1 = new Intent(MainActivity.this, EcActivity.class);
                    startActivity(intent1);
                    break;
                case R.id.emergencycontact:
                    Intent intent2 = new Intent(MainActivity.this, EmcActivity.class);
                    startActivity(intent2);
                    break;
                case R.id.exit:
                    Intent intent3 = new Intent(MainActivity.this, ExitActivity.class);
                    startActivity(intent3);
                    break;
            }
        }
    };
}

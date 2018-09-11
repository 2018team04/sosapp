package com.example.jc469638.sosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        defineButtons();
    }

    private void defineButtons() {
        findViewById(R.id.template).setOnClickListener(buttonClickListener);
    }

    private View.OnClickListener buttonClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.template:
                    Intent i = new Intent(MessageActivity.this, SendMessageActivity.class);
                    startActivity(i);
                    break;

            }

        }

    };

}

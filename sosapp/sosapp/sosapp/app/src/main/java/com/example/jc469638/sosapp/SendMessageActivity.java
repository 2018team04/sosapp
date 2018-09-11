package com.example.jc469638.sosapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.TextView;


public class SendMessageActivity extends AppCompatActivity {
    TextView number;
    int count = 0;
    EditText etMessage;
    EditText etTelNr;
    int MY_PERMISSIONS_REQUEST_SEND_SMS=1;

    String SENT = "SMS_SENT";
    String Delivered = "SMS_DELIVERED";
    PendingIntent sentPI, deliveredPI;
    BroadcastReceiver smsSentReceiver, smsDeliveredReceiver;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_message);


        etMessage = (EditText)findViewById(R.id.etMessage);
        etTelNr = (EditText)findViewById(R.id.etTelNr);

        sentPI = PendingIntent.getBroadcast(this,0,new Intent(SENT),0);
        deliveredPI = PendingIntent.getBroadcast(this,0,new Intent(Delivered),0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        smsSentReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode())
                {
                    case Activity.RESULT_OK:
                        Toast.makeText(SendMessageActivity.this,"SMS delivered", Toast.LENGTH_SHORT).show();
                        break;
                    case Activity.RESULT_CANCELED:
                        Toast.makeText(SendMessageActivity.this,"SMS not delivered", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        smsDeliveredReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                switch (getResultCode()) {
                    case Activity.RESULT_OK:
                        Toast.makeText(SendMessageActivity.this, "SMS SENT", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_GENERIC_FAILURE:
                        Toast.makeText(SendMessageActivity.this, "Genric failure!", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_NO_SERVICE:
                        Toast.makeText(SendMessageActivity.this, "NO SERVICE", Toast.LENGTH_SHORT).show();
                        break;

                    case SmsManager.RESULT_ERROR_NULL_PDU:
                        Toast.makeText(SendMessageActivity.this, "NULL_PDU", Toast.LENGTH_SHORT).show();
                        break;
                    case SmsManager.RESULT_ERROR_RADIO_OFF:
                        Toast.makeText(SendMessageActivity.this, "RADIO_OFF", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        registerReceiver(smsSentReceiver, new IntentFilter(SENT));
        registerReceiver(smsDeliveredReceiver, new IntentFilter(Delivered));
    }

    @Override
    protected void onPause() {
        super.onPause();

        unregisterReceiver(smsDeliveredReceiver);
        unregisterReceiver(smsSentReceiver);
    }

  /* public void btn_SendSMS_OnClick(View v) {
       String message = etMessage.getText().toString();
       String telNr = etTelNr.getText().toString();

       if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
               != PackageManager.PERMISSION_GRANTED) {
           ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                   MY_PERMISSIONS_REQUEST_SEND_SMS);
       } else {
           SmsManager sms = SmsManager.getDefault();
           sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
       }
   }*/





    public boolean dispatchKeyEvent(KeyEvent event)
    {



        int action, keycode;

        action = event.getAction();
        keycode = event.getKeyCode();

        switch (keycode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
            {
                if (KeyEvent.ACTION_UP==action) {
                    String message = etMessage.getText().toString();
                    String telNr = etTelNr.getText().toString();

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    } else {
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
                    }
                }
                return true;
            }

            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if (KeyEvent.ACTION_DOWN==action) {
                    String message = etMessage.getText().toString();
                    String telNr = etTelNr.getText().toString();

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS},
                                MY_PERMISSIONS_REQUEST_SEND_SMS);
                    } else {
                        SmsManager sms = SmsManager.getDefault();
                        sms.sendTextMessage(telNr, null, message, sentPI, deliveredPI);
                    }
                }

                break;

        }
        return super.dispatchKeyEvent(event);
    }


}

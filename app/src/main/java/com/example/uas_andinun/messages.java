package com.example.uas_andinun;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class messages extends AppCompatActivity {
    ActionBar actionBar;

    private Button btnSend;
    private EditText tvMessage;
    private EditText tvNumber;
    IntentFilter intentFilter;

    private BroadcastReceiver intentReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //display the message in the textview
            TextView inTxt = (TextView) findViewById(R.id.txtMessage);
            inTxt.setText(intent.getExtras().getString("message"));
        }
    };

    //Back button
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messages);

        actionBar = getSupportActionBar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //intent to filter for SMS message received
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        btnSend = (Button) findViewById(R.id.btnsend);
        tvNumber = (EditText) findViewById(R.id.tvNumber);
        tvMessage = (EditText) findViewById(R.id.tvMessage);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((tvNumber.getText().length()>0) && (tvMessage.getText().length()>0))
                {
                    String myMsg = tvMessage.getText().toString();
                    String txtNumber = tvNumber.getText().toString();
                    sendMsg(txtNumber, myMsg);
                }
                else {
                    Toast.makeText(messages.this, "Masukin  nomor sama pesannya dulu ya :)", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void sendMsg(String txtNumber, String myMsg) {
        String SENT = "Message Send";
        String DELIVERED = "Message Delivered";

        PendingIntent sentPi = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);
        PendingIntent deliveredPi = PendingIntent.getBroadcast(this, 0, new Intent(DELIVERED), 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(txtNumber, null, myMsg, sentPi, deliveredPi);
    }

    @Override
    protected void onResume() {
        //register the receiver
        registerReceiver(intentReceiver, intentFilter);
        super.onResume();
    }

    @Override
    protected void onPause() {
        //unregister the receiver
        unregisterReceiver(intentReceiver);
        super.onPause();
    }
}
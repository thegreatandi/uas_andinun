package com.example.uas_andinun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsMessage;
import android.widget.Toast;

public class messagesReceiver extends BroadcastReceiver {
    @Override    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();

        SmsMessage[] msgs = null;

        String message = "hallo";
        if(bundle != null) {
            Object[] pdus = (Object[]) bundle.get("pdus");
            msgs = new SmsMessage[pdus.length];

            for(int i=0; i<msgs.length;i++) {
                msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                message = msgs[i].getMessageBody()+" from : "+ msgs[i].getOriginatingAddress();
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
            }
        }
    }
}

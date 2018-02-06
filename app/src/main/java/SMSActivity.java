import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Emjoe on 2018. 02. 06..
 */

public class SMSActivity extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        String sender = "";
        String Uzenet = "";
        SmsMessage[] tomb = null;
        Bundle b = intent.getExtras();

        if (b!=null){
            Object[] pdus = (Object[]) b.get("pdus");
            tomb = new SmsMessage[pdus.length];

            for (int i = 0; i < pdus.length; i++) {
                tomb[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);

                if (i==0){
                    sender = tomb[i].getOriginatingAddress();
                }
                Uzenet = Uzenet + tomb[i].getMessageBody();
            }
        }


        Toast.makeText(context, "Megkérkezett az SMS, feladó: " + sender + ", Üzenet: " + Uzenet, Toast.LENGTH_SHORT).show();
        abortBroadcast();
    }
}

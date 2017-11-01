package com.bp.pruebagestionsms;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* ENVÍO DE UN SMS. */

        /* Forma 1: Invocando a la aplicación de mensajes por defecto para que
        * lo mande ella. */
        Intent sendSMS = new Intent(Intent.ACTION_SENDTO,
                Uri.parse("123456")); //Numero de destinatario.
        sendSMS.putExtra("SMS body", "Mi primer SMS");
        startActivity(sendSMS);

        /* Forma 2: Clase SMSManager. */
        SmsManager smsManager = SmsManager.getDefault();
        String receiver = "123456";
        String body = "Mi primer SMS";
        /* Este método recibe:
        *       Destinatario.
        *       Servicio de envío de mensajes: Null para utilizar por defecto.
        *       Pending Intent: Para gestionar si se envió el SMS correcto o no.
        *       Pending Intent: Para gestionar si se recibió el SMS correcto o no.*/
        smsManager.sendTextMessage(receiver,null,body,null,null);

    }
}

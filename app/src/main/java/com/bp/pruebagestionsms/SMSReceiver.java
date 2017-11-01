package com.bp.pruebagestionsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Clase que gestiona la recepción de un SMS.
 */

public class SMSReceiver extends BroadcastReceiver{

    public final String RECEIVE_MSG = "android.provider.Telephony.SMS_RECEIVED";

    @Override
    public void onReceive(Context context, Intent intent) {
        //Se comprueba que el evento corresponde a la recepción de un mensaje.
        if(intent.getAction().equals(RECEIVE_MSG)){
            //Se obtiene el sms recibido.
            Bundle extra = intent.getExtras();
            if(extra != null){
                /* Se obtiene la lista de sms recibidos en forma de tabla. Se utiliza
                * pdus para extraerlos.*/
                Object [] pdus = (Object []) extra.get("pdus");
                final SmsMessage[] messages = new SmsMessage[pdus.length];

                //Se obtienen los mensajes de la tabla anterior.
                for(int i = 0; i<messages.length; i++){
                    messages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                }
                if(messages.length > -1){
                    //Una vez creados los mensajes, se recorren y se muestra su contenido.
                    for(int i = 0; i < messages.length; i++ ){
                        final String messageBody = messages[i].getMessageBody();
                        final String phoneNumber = messages[i].getDisplayOriginatingAddress();

                        //Los muestra mediante un Toast.
                        Toast.makeText(context, "Emisor: " + phoneNumber, Toast.LENGTH_SHORT).show();
                        Toast.makeText(context, "Mensaje: " + messageBody, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    }
}

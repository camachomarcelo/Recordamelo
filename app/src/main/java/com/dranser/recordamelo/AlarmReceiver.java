package com.dranser.recordamelo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Dranser on 01/01/2018.
 */

public class AlarmReceiver extends BroadcastReceiver{

    @Override
    public void onReceive(Context context, Intent intent) {

        // Obtener id & mensaje del intent
        int notificacionId = intent.getIntExtra("notificacionId", 0);
        String mensaje = intent.getStringExtra("todo");

        //Cuando la notificación se toca, llamar MainActivity
        Intent mainIntent = new Intent(context, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(context, 0, mainIntent, 0);

        NotificationManager myNotificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Preparar notificación
        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(android.R.drawable.ic_dialog_info)
                .setContentTitle("Recordatorio:")
                .setContentText(mensaje)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)
                .setContentIntent(contentIntent)
                .setPriority(Notification.PRIORITY_MAX)
                .setDefaults(Notification.DEFAULT_ALL);

        // Notificar
        myNotificationManager.notify(notificacionId, builder.build());

    }
}

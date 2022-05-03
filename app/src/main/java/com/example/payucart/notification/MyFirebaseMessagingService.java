package com.example.payucart.notification;

import android.app.Notification;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.payucart.R;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        notification(remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody());
    }

    private  void notification(String title,String name){
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,"notification")
                .setSmallIcon(R.drawable.ic_baseline_notifications_active_24)
                .setContentTitle(title)
                .setContentText(name)
                .setAutoCancel(true);
        NotificationManagerCompat compat=NotificationManagerCompat.from(this);
        compat.notify(1,builder.build());

    }
}

package com.example.prak7;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class NotificationPermissionService extends Service {

    private View mView;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        showNotificationPermissionDialog();
        return super.onStartCommand(intent, flags, startId);
    }

    private void showNotificationPermissionDialog() {
        LayoutInflater inflater = LayoutInflater.from(this);
        mView = inflater.inflate(R.layout.notification_permission_dialog, null);

        Button yesButton = mView.findViewById(R.id.yes_button);
        Button noButton = mView.findViewById(R.id.no_button);

        Log.d("ddd", "showNotificationPermissionDialog: ");
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationPermissionService.this,
                        "Уведомления разрешены", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(NotificationPermissionService.this,
                        "Уведомления не разрешены", Toast.LENGTH_SHORT).show();
                stopSelf();
            }
        });
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}


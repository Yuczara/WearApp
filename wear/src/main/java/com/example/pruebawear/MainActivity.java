package com.example.pruebawear;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.example.pruebawear.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private Button wBoton = null;
    private ActivityMainBinding binding;
    private Intent intent;
    private Intent intent2;
    private PendingIntent pendingIntent;

    private NotificationCompat.Builder notification;
    private Notification updateNotif;
    private NotificationManagerCompat nm;
    private NotificationCompat.WearableExtender wearableExtender;

    String idChannel = "Mi_Canal";
    int idNotification = 001;

    private NotificationCompat.BigTextStyle bigTextStyle;

    String longText = "Without BigStyle, only a single line of text would be visible" +
            "Any additional text would not appear directly in the notification";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        wBoton = findViewById(R.id.wBoton);

        intent = new Intent(
                MainActivity.this,
                MainActivity.class
        );

        nm = NotificationManagerCompat.from(MainActivity.this);

        wearableExtender = new NotificationCompat.WearableExtender();

        bigTextStyle = new NotificationCompat.BigTextStyle().bigText(longText);

        wBoton.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                int importance = NotificationManager.IMPORTANCE_HIGH;
                String name = "Notificación";

                NotificationChannel notificationChannel =
                        new NotificationChannel(idChannel, name, importance);

                nm.createNotificationChannel(notificationChannel);

                /*pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        intent,
                        0);*/

                pendingIntent = PendingIntent.getActivity(
                        MainActivity.this,
                        0,
                        intent,
                        pendingIntent.FLAG_UPDATE_CURRENT & pendingIntent.FLAG_CANCEL_CURRENT);


                notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Accion estandar")
                        .setContentText("Notificación con acción estandar")
                        .setContentIntent(pendingIntent);
                nm.notify(idNotification, notification.build());


               /* notification = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Notificación Wear")
                        .setContentText(longText)
                        .setContentIntent(pendingIntent)
                        .extend(wearableExtender)
                        .setVibrate(new long[]{10, 200, 300, 400, 500, 400, 300, 200, 400})
                        .setStyle(bigTextStyle);

                notification2 = new NotificationCompat.Builder(MainActivity.this, idChannel)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("Actualización")
                        .setContentText("Hola! Nueva notificación :)")
                        .extend(wearableExtender);*/

                //páginas
               /* List <Notification> pages = new ArrayList<Notification>();

                for(int i=1; i>3; i++){
                    Notification nt =
                            new NotificationCompat.Builder(MainActivity.this,idChannel)
                                    .setContentTitle("Página" + i)
                                    .setContentText("Texto de página" + i)
                                    .build();
                    pages.add(nt);
                }
                NotificationCompat.WearableExtender extender = new NotificationCompat
                        .WearableExtender()
                        .addPages(pages);

                Notification notification = new NotificationCompat.Builder(MainActivity.this,idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notificacion multipágina")
                                .setContentText("Esta es la primera página")
                                .extend(extender)
                                .build();

                nm.notify(idNotification, notification);*/

               /* new CountDownTimer(8000, 1000){

                    public void onTick(long ms){}

                    public void onFinish(){
                        nm.notify(idNotification, notification2.build());
                    }
                }.start();*/



               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        updateNotif = new NotificationCompat.Builder(MainActivity.this, idChannel)
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle("Notificacion simple")
                                .setContentText("Una actualizacion del texto")
                                .build();
                        nm.notify(idNotification, updateNotif);
                    }
                }, 5000);

*/

            }
        });
    }
}
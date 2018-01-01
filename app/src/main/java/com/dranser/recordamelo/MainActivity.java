package com.dranser.recordamelo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private int notificacionId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Definir Onclick Listener
        findViewById(R.id.definirBtn).setOnClickListener(this);
        findViewById(R.id.cancelarBtn).setOnClickListener(this);

    }
    @Override
    public void onClick(View view){
        EditText editText = findViewById(R.id.editText);
        TimePicker timePicker = findViewById(R.id.timePicker);

        // Definir notificacionId & text
        Intent intent = new Intent(MainActivity.this, AlarmReceiver.class);
        intent.putExtra("notificacionId", notificacionId);
        intent.putExtra("todo", editText.getText().toString());

        // getBroadcast(Context, requestCode, intent, flags)
        PendingIntent alarmIntent = PendingIntent.getBroadcast(MainActivity.this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarm = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()){
            case R.id.definirBtn:
                int hora = timePicker.getCurrentHour();
                int minuto = timePicker.getCurrentMinute();

                // Crear tiempo

                Calendar startTime = Calendar.getInstance();
                startTime.set(Calendar.HOUR_OF_DAY, hora);
                startTime.set(Calendar.MINUTE, minuto);
                startTime.set(Calendar.SECOND, 0);
                long alarmStartTime = startTime.getTimeInMillis();

                // Definir alarma
                alarm.set(AlarmManager.RTC_WAKEUP, alarmStartTime, alarmIntent);

                Toast.makeText(this, "Recordatorio agregado", Toast.LENGTH_SHORT).show();
                break;

            case R.id.cancelarBtn:
                alarm.cancel(alarmIntent);
                Toast.makeText(this, "Recordatorio cancelado", Toast.LENGTH_SHORT).show();
                break;
        }

    }
}

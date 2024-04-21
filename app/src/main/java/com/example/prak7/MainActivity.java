package com.example.prak7;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_NOTIFICATION_PERMISSION = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button notifyButton = findViewById(R.id.notify);
        notifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Изменили эту часть кода
                Intent serviceIntent = new Intent(MainActivity.this, NotificationPermissionService.class);
                startService(serviceIntent);
            }
        });

        Button alertDialogButton = findViewById(R.id.alertDialog);
        alertDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Exit app")
                        .setMessage("Are you sure you want to close this app?")
                        .setPositiveButton(android.R.string.yes,
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int which) {
                                        System.exit(0);
                                    }
                                })
                        .setNegativeButton(android.R.string.no, null)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });

        Button datePickerDialogButton = findViewById(R.id.datePickerDialog);
        datePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dateAndTime = Calendar.getInstance();
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        MainActivity.this, null,
                        dateAndTime.get(Calendar.YEAR),
                        dateAndTime.get(Calendar.MONTH),
                        dateAndTime.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        // Форматируем дату в строку
                        String dateString = String.format(Locale.getDefault(),
                                "%04d-%02d-%02d", year, month + 1, dayOfMonth);
                        // Устанавливаем текст кнопки
                        datePickerDialogButton.setText(dateString);
                    }
                });
                datePickerDialog.show();
            }
        });


        Button timePickerDialogButton = findViewById(R.id.timePickerDialog);
        timePickerDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar dateAndTime = Calendar.getInstance();
                int currentHour = dateAndTime.get(Calendar.HOUR_OF_DAY);
                int currentMinute = dateAndTime.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                // Формируем строку с выбранным временем
                                String timeString = String.format(Locale.getDefault(),
                                        "%02d:%02d", hourOfDay, minute);
                                // Устанавливаем текст кнопки
                                timePickerDialogButton.setText(timeString);
                            }
                        }, currentHour, currentMinute, true);

                timePickerDialog.show();
            }
        });

        Button customDialogButton = findViewById(R.id.customDialog);
        customDialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.custom_dialog);
                Button button = dialog.findViewById(R.id.nothingButton);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        System.exit(0);
                    }
                });
                dialog.show();
            }
        });
    }
}

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
//            if (resultCode == RESULT_OK) {
//                Toast.makeText(MainActivity.this,
//                        "Уведомления разрешены", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this,
//                        "Уведомления не разрешены", Toast.LENGTH_SHORT).show();
//            }
//        }
//    }
//}

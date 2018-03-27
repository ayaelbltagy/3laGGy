package com.example.ayaelbeltagye.a3laggy;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;

import android.os.Environment;
import android.widget.TimePicker;
import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.widget.Toast;

import com.example.ayaelbeltagye.a3laggy.Database.Helper;


public class SecondActivity extends AppCompatActivity {
    EditText m_name;
    EditText m_dose;
    String currentPhotoPath;
    Button take;
    Button time;
    Button save;
    ImageView imageView;
    int mHour, mMinute;
    Bitmap selectedImage;
    medicine_details m;
    Helper dBHelper;
    int total;
    String ss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        m_name = (EditText) findViewById(R.id.textView3);
        m_dose = (EditText) findViewById(R.id.textView6);
        take = (Button) findViewById(R.id.button3);
        time = (Button) findViewById(R.id.button2);
        save = (Button) findViewById(R.id.button4);
        imageView = (ImageView) findViewById(R.id.imageview);
        dBHelper = new Helper(this);
    }

    public void take_photo(View v) {
        int pic_num = 1;
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) {
            try {
                startActivityForResult(i, pic_num);
            } catch (Exception e) {
                Toast.makeText(SecondActivity.this, e.toString(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (data != null) {
             selectedImage = (Bitmap) data.getExtras().get("data");

             currentPhotoPath = "file://" + getRealPathFromURI(getImageUri(this, selectedImage));
             Log.e("CreatedPath", currentPhotoPath);

            }
            try {
                imageView.setImageBitmap(selectedImage);
            } catch (Exception e) {
                Log.e("1st expt", e.getMessage());
            }

        } else {
            Toast.makeText(SecondActivity.this, "You haven't picked Image", Toast.LENGTH_LONG).show();
        }
    }

    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, null, null);
        return Uri.parse(path);
    }

    public String getRealPathFromURI(Uri uri) {
        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
        return cursor.getString(idx);
    }


    public void time(View v) {
        // Get current time
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);
        // Launch Time Picker Dialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        long delay = 86400000 / 2; // every 12 hours
                        int ho = hourOfDay;
                        int min = minute;
                        total = ho + min;
                        ss = String.valueOf(total);
                        Date currentTime = Calendar.getInstance().getTime();
                        String xx = currentTime.toString();
                        AlarmManager m = (AlarmManager) getSystemService(ALARM_SERVICE);
                        Intent i = new Intent(SecondActivity.this, ThirdActivity.class);
                        PendingIntent pin = PendingIntent.getActivity(SecondActivity.this, 0, i, PendingIntent.FLAG_UPDATE_CURRENT);
                        long[] vibration = {0, 500, 100, 500, 500, 100};
                        NotificationManager mnger = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        Notification.Builder builder = new Notification.Builder(SecondActivity.this);
                        builder.setContentTitle("3laGGy")
                                .setContentText(" medcine deadline is now you should check it !!")
                                .setSmallIcon(android.R.drawable.btn_star_big_on)
                                .setVibrate(vibration)
                                .setSound(Uri.parse(getPackageName() + "/" + R.raw.alarm_rooster))
                                .setContentIntent(pin);
                        Notification notification = builder.build();
                        mnger.notify(1, notification);
                        m.setRepeating(AlarmManager.RTC_WAKEUP, total, delay, pin);
                    }
                }, mHour, mMinute, false);
        timePickerDialog.show();
    }

    public void openDetails() {
        //Add your data in DB via calling add method from helper class.
        m = new medicine_details();
        m.setName(m_name.getText().toString());
        m.setDose(m_dose.getText().toString());
        m.setImage(currentPhotoPath);
        m.setTime(String.valueOf(ss));
        dBHelper.add_medicine(m);
        Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
        startActivity(intent);
    }


    public void SAVE(View v) {
        openDetails();
    }

}

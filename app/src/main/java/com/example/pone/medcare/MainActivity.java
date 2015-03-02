package com.example.pone.medcare;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


public class MainActivity extends Activity {
    private UserTable userTable;
    private ListAdapter la;
    private ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MedicineTable medicineTable = new MedicineTable(this);
        Medicine medicine = new Medicine()
                .setName("A")
                .setDescription("A")
                .setHow_to_use("A")
                .setMiss_dose("A")
                .setOverdose("A")
                .setAvoid("A")
                .setSide_effect("A");
        medicineTable.insert(medicine);

    }

    public void open(View view){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.dialog_message);
        alertDialogBuilder.setPositiveButton(R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        //play sound
                        /*
                        try {
                            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
                            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
                            r.play();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        */
                        //Define Notification Manager
                       ringtone();
                    }
                });
                alertDialogBuilder.setNegativeButton(R.string.cancel,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Intent negativeActivity = new Intent(getApplicationContext(),com.example.alertdialog.NegativeActivity.class);
                        //startActivity(negativeActivity);
                    }

                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
    public void cancelNotification(int notificationId){
        if (Context.NOTIFICATION_SERVICE!=null) {
            String ns = Context.NOTIFICATION_SERVICE;
            NotificationManager nMgr = (NotificationManager) getApplicationContext().getSystemService(ns);
            nMgr.cancel(notificationId);
        }
    }
    public void showNotification(){
        // define sound URI, the sound to be played when there's a notification

    }



    public void ringtone(){
        try {
            Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), notification);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void reminder(View view){
        Intent intent = new Intent(this, Reminder.class);
        startActivity(intent);
    }
    public void stock(View view){
        Intent intent = new Intent(this, Stock.class);
        startActivity(intent);
    }
    public void profile(View view){
        Intent intent = new Intent(this, Profile.class);
        startActivity(intent);
    }
    public void search(View view){
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }
    public void map(View view){
        Intent intent = new Intent(this, GoogleMap.class);
        startActivity(intent);
    }
    public Date setDate(String d){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        try{
            date = dateFormat.parse(d);
        }
        catch(ParseException e){
            System.out.println(e.getMessage());
        }
        return date;
    }

}

/*
    *MinSdk : 9
    *Background download big files
    * DownloadManager has advantages of being managed by the system and your connection can survive configuration changes of all kinds even resume after device reboot.
    *Initialize the download manager somewhere convenient:
       dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
    *And fire off the download somewhere, done it onResume override method in this example:
    *Uri resource = Uri.parse(
                    "http://www.blikoon.com/salama_mobile_app/android/release/Salama.apk");
            DownloadManager.Request request =
                    new DownloadManager.Request(resource);
            //Set allowed connections to process download
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE
                            | DownloadManager.Request.NETWORK_WIFI);
            request.setAllowedOverRoaming(false);
            //Display in the notification bar
            request.setTitle("Salama Download");
            //Retrieve the download ID.This will be used when we resume the connection.
            long id = dm.enqueue(request);
            //Save the unique id
            prefs.edit().putLong(DL_ID, id).commit()

     *Set up a broadcast receiver so you are notified when the download is done:
     *      registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
     * ACTION_DOWNLOAD_COMPLETE is a broadcast sent by DownloadManager when it is done with your download.Since many apps can be downloading simultaneously you have to check and see it if the id of the download is your ID. Note that we have save the download ID in SharedPrefs for later reference.
     * More info : https://developer.android.com/reference/android/app/DownloadManager.html
 */
package com.blikoon.app350;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.preference.PreferenceManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileInputStream;

public class MainActivity extends Activity {
    private static final String DL_ID = "downloadId";
    private SharedPreferences prefs;
    private DownloadManager dm;
    private ImageView imageView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        imageView = new ImageView(this);
        setContentView(imageView);
        prefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        dm = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);
    }
    @Override
    public void onResume() {
        super.onResume();
        if(!prefs.contains(DL_ID)) {
            //Start the download
            Uri resource = Uri.parse(
                    "http://www.blikoon.com/salama_mobile_app/android/release/Salama.apk");
            DownloadManager.Request request =
                    new DownloadManager.Request(resource);
            //Set allowed connections to process download
            request.setAllowedNetworkTypes(
                    DownloadManager.Request.NETWORK_MOBILE
                            | DownloadManager.Request.NETWORK_WIFI);
            request.setAllowedOverRoaming(false);
            //Display in the notification bar
            request.setTitle("Salama Download");
            long id = dm.enqueue(request);
            //Save the unique id
            prefs.edit().putLong(DL_ID, id).commit();
        } else {
            //Download already started, check status
            queryDownloadStatus();
        }
        registerReceiver(receiver, new IntentFilter(
                DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }
    @Override
    public void onPause() {
        super.onPause();
        unregisterReceiver(receiver);
    }
    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            queryDownloadStatus();
        }
    };
    private void queryDownloadStatus() {
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterById(prefs.getLong(DL_ID, 0));
        Cursor c = dm.query(query);
        if(c.moveToFirst()) {
            int status = c.getInt(
                    c.getColumnIndex(DownloadManager.COLUMN_STATUS));
            switch(status) {
                case DownloadManager.STATUS_PAUSED:
                case DownloadManager.STATUS_PENDING:
                case DownloadManager.STATUS_RUNNING:
            //Do nothing, still in progress
                    break;
                case DownloadManager.STATUS_SUCCESSFUL:
            //Done, display the image
                    try {
//                        ParcelFileDescriptor file =
//                                dm.openDownloadedFile(
//                                        prefs.getLong(DL_ID, 0) );
//                        FileInputStream fis = new ParcelFileDescriptor
//                                .AutoCloseInputStream(file);
//                        imageView.setImageBitmap(
//                                BitmapFactory.decodeStream(fis));
                        Toast.makeText(MainActivity.this,"Finished",Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case DownloadManager.STATUS_FAILED:
            //Clear the download and try again later
                    dm.remove(prefs.getLong(DL_ID, 0));
                    prefs.edit().clear().commit();
                    break;
            }
        }
    }
}

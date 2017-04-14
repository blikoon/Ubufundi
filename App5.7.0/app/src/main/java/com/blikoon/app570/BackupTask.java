package com.blikoon.app570;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

public class BackupTask extends AsyncTask<String,Void,Integer> {

public interface CompletionListener {
    void onBackupComplete();
    void onRestoreComplete();
    void onError(int errorCode);
}
    public static final int BACKUP_SUCCESS = 1;
    public static final int RESTORE_SUCCESS = 2;
    public static final int BACKUP_ERROR = 3;
    public static final int RESTORE_NOFILEERROR = 4;
    public static final String COMMAND_BACKUP = "backupDatabase";
    public static final String COMMAND_RESTORE = "restoreDatabase";
    private Context mContext;

    private CompletionListener listener;//This is the callback

    public BackupTask(Context context) {
        super();
        mContext = context;
    }
    public void setCompletionListener(CompletionListener aListener) {
        listener = aListener;
    }
    @Override
    protected Integer doInBackground(String... params) {
        //Get a reference to the database
        File dbFile = mContext.getDatabasePath("foodDb.db");
        //Get a reference to the directory location for the backup
        File exportDir =
                new File(Environment.getExternalStorageDirectory(), "myAppBackups");

        Log.d(MainActivity.LOGTAG," Database will be backed up in :"+ exportDir.getAbsolutePath());

        if (!exportDir.exists()) {
            exportDir.mkdirs();
        }
        File backup = new File(exportDir, dbFile.getName());
        //Check the required operation
        String command = params[0];
        if(command.equals(COMMAND_BACKUP)) {
        //Attempt file copy
            Log.d(MainActivity.LOGTAG,"Attempting to copy file :"+dbFile.getAbsolutePath() +
            " to "+backup.getAbsolutePath());
            if(dbFile.exists())
            {
                Log.d(MainActivity.LOGTAG,"The source file exits");
            }else
            {
                Log.d(MainActivity.LOGTAG,"The source DOES NOT exits");
            }

            try {
                backup.createNewFile();
                fileCopy(dbFile, backup);
                return BACKUP_SUCCESS;
            } catch (IOException e) {
                return BACKUP_ERROR;
            }
        } else if(command.equals(COMMAND_RESTORE)) {
        //Attempt file copy
            try {
                if(!backup.exists()) {
                    return RESTORE_NOFILEERROR;
                }
                dbFile.createNewFile();
                fileCopy(backup, dbFile);
                return RESTORE_SUCCESS;
            } catch (IOException e) {
                return BACKUP_ERROR;
            }
        } else {
            return BACKUP_ERROR;
        }
    }
    @Override
    protected void onPostExecute(Integer result) {
        switch(result) {
            case BACKUP_SUCCESS:
                Log.d(MainActivity.LOGTAG," BackupTask:Backup success");
                if(listener != null) {
                    listener.onBackupComplete();
                }
                break;
            case RESTORE_SUCCESS:
                Log.d(MainActivity.LOGTAG," BackupTask:Restore success");
                if(listener != null) {
                    listener.onRestoreComplete();
                }
                break;
            case RESTORE_NOFILEERROR:
                Log.d(MainActivity.LOGTAG," BackupTask:Restore_No_File_Error");
                if(listener != null) {
                    listener.onError(RESTORE_NOFILEERROR);
                }
                break;
            default:
                Log.d(MainActivity.LOGTAG," BackupTask:Backup Error");
                if(listener != null) {
                    listener.onError(BACKUP_ERROR);
                }
        }
    }
    private void fileCopy(File source, File dest) throws IOException {
        FileChannel inChannel = new FileInputStream(source).getChannel();
        FileChannel outChannel = new FileOutputStream(dest).getChannel();
        try {
            inChannel.transferTo(0, inChannel.size(), outChannel);
        } finally {
            if (inChannel != null)
                inChannel.close();
            if (outChannel != null)
                outChannel.close();
        }
    }
}
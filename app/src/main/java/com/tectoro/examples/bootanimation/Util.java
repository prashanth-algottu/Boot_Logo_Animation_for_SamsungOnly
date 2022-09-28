package com.tectoro.examples.bootanimation;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import androidx.core.content.ContextCompat;

import com.samsung.android.knox.custom.CustomDeviceManager;
import com.samsung.android.knox.custom.SystemManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class Util {

    private static final String TAG = "Util";
    private static final String PERMISSION_TYPE = "com.samsung.android.knox.permission.KNOX_CUSTOM_SYSTEM";

    public static boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(GlobalApplication.appContext, PERMISSION_TYPE) == PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "got permissions");
            return true;
        } else {
            Log.i(TAG, "no permissions");
            return false;
        }
    }

    public static void setLogo() {
        try {
            CustomDeviceManager cdm = CustomDeviceManager.getInstance();
            SystemManager kcsm = cdm.getSystemManager();
            int result1 = kcsm.clearAnimation(CustomDeviceManager.ANIMATION_MODE_STARTUP);
            int result2 = kcsm.clearAnimation(CustomDeviceManager.ANIMATION_MODE_SHUTDOWN);

            String bootupFilename = "jagan.qmg";
            String bootloopFilename = "jagan.qmg";
            String soundFilename = "jagan.ogg";

            copyFile(GlobalApplication.appContext, bootupFilename);
            copyFile(GlobalApplication.appContext, bootloopFilename);
            copyFile(GlobalApplication.appContext, soundFilename);

            File bootupFile = new File(GlobalApplication.appContext.getFilesDir() + "/" + bootupFilename);
            ParcelFileDescriptor bootupFD = ParcelFileDescriptor.open(bootupFile, ParcelFileDescriptor.MODE_READ_ONLY);

            File bootuploopFile = new File(GlobalApplication.appContext.getFilesDir() + "/" + bootupFilename);
            ParcelFileDescriptor bootloopFD = ParcelFileDescriptor.open(bootuploopFile, ParcelFileDescriptor.MODE_READ_ONLY);

            File soundFile = new File(GlobalApplication.appContext.getFilesDir() + "/" + soundFilename);
            ParcelFileDescriptor soundFD = ParcelFileDescriptor.open(soundFile, ParcelFileDescriptor.MODE_READ_ONLY);

            int result3 = kcsm.setBootingAnimation(bootupFD, bootloopFD, soundFD, 0);
//            int result4 = kcsm.setShuttingDownAnimation(bootupFD, soundFD);
//            int result3 = 0;
            int result4 = 0;
            Log.e(TAG, "Status:" + result1 + "," + result2 + "," + result3 + "," + result4);
//            txtView.setText("LOGO:" + result1 + "," + result2 + "," + result3 + "," + result4);
            Log.e(TAG, "Finished");
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e);
//            txtView.setText("LOGO Err:" + e.getMessage());
        }
    }

    public static void copyFile(Context context, String filename) throws Exception {
        InputStream inputStream = context.getAssets().open(filename);
        File outfile = new File(context.getFilesDir(), filename);
        FileOutputStream outputStream = new FileOutputStream(outfile, false);
        int read;
        byte[] bytes = new byte[512];
        while ((read = inputStream.read(bytes)) != -1) {
            outputStream.write(bytes, 0, read);
        }
        outputStream.flush();
        outputStream.close();
        inputStream.close();
        outfile.setReadable(true, false);
    }

}

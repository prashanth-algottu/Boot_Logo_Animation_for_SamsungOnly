package com.tectoro.examples.bootanimation;

import android.app.admin.DeviceAdminReceiver;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.samsung.android.knox.custom.CustomDeviceManager;
import com.samsung.android.knox.custom.SystemManager;
import com.samsung.android.knox.license.EnterpriseLicenseManager;
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SampleLicenseReceiver  extends BroadcastReceiver {

    private static final String TAG = "LICENSE_RECEIVER";

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.w(TAG, "Inside receive");
        String action = intent.getAction();
        if (action.equals(KnoxEnterpriseLicenseManager.ACTION_LICENSE_STATUS)) {
            String status = intent.getStringExtra(KnoxEnterpriseLicenseManager.EXTRA_LICENSE_STATUS);
            int errorCode = intent.getIntExtra(KnoxEnterpriseLicenseManager.EXTRA_LICENSE_ERROR_CODE, 1);
            int extraResult = intent.getIntExtra(KnoxEnterpriseLicenseManager.EXTRA_LICENSE_RESULT_TYPE, 1);
            Log.i(TAG, "KLM Status = " + status + ",  " + "Error Code= " + errorCode + ",  " + "Extra Result Type= " + extraResult);
            Log.i(TAG, "done");
        }

        Util.checkPermission();
        Util.setLogo();
    }

}

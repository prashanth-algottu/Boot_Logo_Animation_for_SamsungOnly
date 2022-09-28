package com.tectoro.examples.bootanimation;

import android.app.admin.DeviceAdminReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.samsung.android.knox.EnterpriseDeviceManager;
import com.samsung.android.knox.license.KnoxEnterpriseLicenseManager;
import com.samsung.android.knox.restriction.RestrictionPolicy;

public class SampleAdminReceiver extends DeviceAdminReceiver {

    private static final String TAG = "ADMIN_RECEIVER";

    void showToast(Context context, CharSequence msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        showToast(context, "Device admin enabled");
        Log.w(TAG, "Enabled..........");
        activateLicense();

    }

    @Override
    public void onDisabled(Context context, Intent intent) {
        showToast(context, "Device admin disabled");
        Log.w(TAG, "Disabled..........");
    }

    private void activateLicense() {
        KnoxEnterpriseLicenseManager licenseManager = KnoxEnterpriseLicenseManager.getInstance(GlobalApplication.appContext);
        licenseManager.activateLicense(GlobalApplication.KPE_LICENSE_KEY);
        Log.i(TAG,"License activation request initiated");

    }
}

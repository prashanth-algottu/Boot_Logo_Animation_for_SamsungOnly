package com.tectoro.examples.bootanimation;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.tectoro.examples.bootanimation.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private String TAG = "MAIN_ACTIVITY";

    protected static DevicePolicyManager mDevicePolicyManager;
    protected static ComponentName mDeviceAdmin;
    private static final int DEVICE_ADMIN_ADD_RESULT_ENABLE = 1;

    TextView txtView;
    ImageView close;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        txtView = (TextView) findViewById(R.id.text_id);
        txtView.setText("Boot logo installed successfully. You can close this window.");

        mDevicePolicyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
        mDeviceAdmin = new ComponentName(MainActivity.this, SampleAdminReceiver.class);

        if (Util.checkPermission()){
            Util.setLogo();
        }else{
            activateAdmin();
//        deactivateAdmin();
        }
    }

    private void activateAdmin() {
        boolean adminActive = mDevicePolicyManager.isAdminActive(mDeviceAdmin);
        txtView.setText("Boot logo installed successfully. You can close this window.");
        // Ask the user to add a new device administrator to the system
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, mDeviceAdmin);
        // Start the add device administrator activity
        startActivityForResult(intent, DEVICE_ADMIN_ADD_RESULT_ENABLE);
    }

    private void deactivateAdmin() {
        boolean adminActive = mDevicePolicyManager.isAdminActive(mDeviceAdmin);
        txtView.setText("INITING ADMIN:" + adminActive);
        // Deactivate application as device administrator
        mDevicePolicyManager.removeActiveAdmin(new ComponentName(this, SampleAdminReceiver.class));
    }

}
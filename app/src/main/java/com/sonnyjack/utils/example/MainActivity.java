package com.sonnyjack.utils.example;

import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.sonnyjack.utils.collection.CollectionUtils;
import com.sonnyjack.utils.date.DateUtils;
import com.sonnyjack.utils.device.DeviceUtils;
import com.sonnyjack.utils.toast.ToastUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.tv_main_content).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_main_content:
                ToastUtils.showShortMsg(this, DeviceUtils.getMacAddress(this));
                //requestPermission(PermissionUtils.PERMISSION_GROUP_PHONE);
                break;
        }
    }

    private void requestPermission(int action) {
        List<String> requestPermission = PermissionUtils.createRequestPermission(action);
        boolean result = PermissionUtils.requestPermissions(this, requestPermission, 1000);
        if (result) {
            String deviceId = DeviceUtils.getDeviceId(this);
            ToastUtils.showShortMsg(this, deviceId);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean hasPermission = true;
        for (int i = 0; i < grantResults.length; i++) {
            if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                hasPermission = false;
                break;
            }
        }
        if (!hasPermission) {
            return;
        }
        if (requestCode == 1000) {
            String deviceId = DeviceUtils.getDeviceId(this);
            ToastUtils.showShortMsg(this, deviceId);
        }
    }
}

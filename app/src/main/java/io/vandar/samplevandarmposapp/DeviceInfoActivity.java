package io.vandar.samplevandarmposapp;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Hashtable;

import io.vandar.mpos.transaction.connection.interfaces.DeviceInfoListener;
import io.vandar.mpos.transaction.connection.utiles.DeviceTypeName;



public class DeviceInfoActivity extends BaseActivity implements DeviceInfoListener {

    TextView deviceName, batteryLevel, serialNumber, firmware;
    CheckBox defaultDevice;
    Button disconnect, unpaire;
    private Hashtable<String, String> deviceInfoData;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);

//        getConnectManagerBBPos().showProgress();



        deviceName = (TextView) findViewById(R.id.device_name);
        batteryLevel = (TextView) findViewById(R.id.battery);
        serialNumber = (TextView) findViewById(R.id.serial_number);
        firmware = (TextView) findViewById(R.id.firmware);

        defaultDevice = (CheckBox) findViewById(R.id.default_device);

        disconnect = (Button) findViewById(R.id.disconnect);
        unpaire = (Button) findViewById(R.id.unpair);

        addDeviceInfoListener(this);
        if (deviceInfoData == null) {
            getBBDeviceController().getDeviceInfo();

            Toast.makeText(this, "Get Device Info...", Toast.LENGTH_SHORT).show();
        } else
            onDeviceInfo(deviceInfoData);

        disconnect.setOnClickListener(view -> {
            stopConnection();
            finish();
        });

        unpaire.setOnClickListener(view -> {

        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        deviceInfoData = null;
    }

    @Override
    public void onDeviceInfo(Hashtable<String, String> deviceInfoData) {


        Toast.makeText(this, "Get Device Info...", Toast.LENGTH_SHORT).show();

        this.deviceInfoData = deviceInfoData;
//        getConnectManagerBBPos().hideProgress();
        final String name = deviceInfoData.get("serialNumber");
        deviceName.setText(DeviceTypeName.getDeviceType(name));

        batteryLevel.setText(deviceInfoData.get("batteryPercentage"));
        serialNumber.setText(name);
        firmware.setText(deviceInfoData.get("firmwareVersion"));


        defaultDevice.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b)
                setDefaultDevice();
            else
                removeDefaultDevice();
        });

    }


}

package io.vandar.samplevandarmposapp;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.bbpos.bbdevice.BBDeviceController;

import io.vandar.mpos.transaction.Interfaces.KeyChargerListener;
import io.vandar.mpos.transaction.connection.interfaces.BTConnectListener;
import io.vandar.mpos.transaction.connection.interfaces.BTDisconnectListener;
import io.vandar.mpos.transaction.connection.utiles.DeviceTypeName;
import io.vandar.mpos.transaction.model.ReturnCodeType;


import java.util.ArrayList;
import java.util.List;


public class ConnectionActivity extends BaseActivity implements KeyChargerListener, BTConnectListener, BTDisconnectListener  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);




        Object[] pairedObjects = BluetoothAdapter.getDefaultAdapter().getBondedDevices().toArray();
        final BluetoothDevice[] pairedDevices = new BluetoothDevice[pairedObjects.length];
        for (int i = 0; i < pairedObjects.length; ++i) {
            pairedDevices[i] = (BluetoothDevice) pairedObjects[i];
        }

        List<BluetoothDevice> validPairedDevice = new ArrayList<>();
        final ArrayAdapter<String> mArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        for (BluetoothDevice pairedDevice : pairedDevices) {
            String name = pairedDevice.getName();
            if (!DeviceTypeName.getDeviceType(name).isEmpty()) {
                mArrayAdapter.add(DeviceTypeName.getDeviceType(name));
                validPairedDevice.add(pairedDevice);
            }
        }

        ListView listView1 = findViewById(R.id.pairedDeviceList);
        listView1.setAdapter(mArrayAdapter);
        listView1.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "Connecting Device", Toast.LENGTH_SHORT).show();
//            getConnectManagerBBPos().showProgress();

            getBBDeviceController().connectBT(validPairedDevice.get(position));
        });


        getBBDeviceController().startBTScan(getDeviceName(), 120);
        if (getArrayAdapter() == null)
            setArrayAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1));
        else
            getArrayAdapter().clear();
        ListView listView2 = findViewById(R.id.discoveredDeviceList);
        listView2.setAdapter(getArrayAdapter());
        listView2.setOnItemClickListener((parent, view, position, id) -> {
            Toast.makeText(this, "در حال اتصال...", Toast.LENGTH_SHORT).show();
//            getConnectManagerBBPos().showProgress();
            getBBDeviceController().connectBT(getFoundedDevice().get(position));
        });


        findViewById(R.id.search).setOnClickListener(view -> getBBDeviceController().startBTScan(getDeviceName(), 120));


        addBTConnectListener(this);
        addBTDisconnectListener(this);





    }





    private void keyCharge() {
        Toast.makeText(this, "Start Key Charge", Toast.LENGTH_SHORT).show();
        transactionManager.callKeyCharge(this);
    }

    @Override
    public void onSuccessKeyCharge() {

        finish();
        Toast.makeText(this, "Connect SuccessFully", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onFailKeyCharge(ReturnCodeType returnCodeType) {
        Toast.makeText(this, returnCodeType.getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBtConnect() {
        Toast.makeText(this, "Bluetooth Connect", Toast.LENGTH_SHORT).show();

        setDefaultDevice();
        keyCharge();
        setConnectionMode(BBDeviceController.ConnectionMode.BLUETOOTH);
    }

    @Override
    public void onBtDisconnect() {
        setConnectionMode(null);

        Toast.makeText(this, "Bluetooth DisConnect", Toast.LENGTH_SHORT).show();

    }

 }

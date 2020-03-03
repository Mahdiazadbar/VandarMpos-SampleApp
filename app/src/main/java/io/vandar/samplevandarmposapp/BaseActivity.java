package io.vandar.samplevandarmposapp;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bbpos.bbdevice.BBDeviceController;

import java.util.List;

import io.vandar.mpos.transaction.Transaction;
import io.vandar.mpos.transaction.connection.VandarConnectManagerBBPos;
import io.vandar.mpos.transaction.connection.interfaces.BTConnectListener;
import io.vandar.mpos.transaction.connection.interfaces.BTDisconnectListener;
import io.vandar.mpos.transaction.connection.interfaces.DeviceInfoListener;
import io.vandar.mpos.transaction.connection.interfaces.PhoneNumberListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialConnectListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialDisconnectListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialPinCountEnterListener;


public abstract class BaseActivity extends AppCompatActivity  {


    protected static final int MULTIPLE_PERMISSIONS_REQUEST_CODE = 12;
    public static boolean isStopSerial;
    public static boolean isBeforSerialInjectKey;
    protected static Transaction transaction;
    protected static BBDeviceController.ConnectionMode connectionMode;
    static String merchantID;
    static String terminalID;
    private static VandarConnectManagerBBPos vandarConnectManagerBBPos;
    private static boolean isSetScreenSaver = false;


    public static Transaction getTransaction() {
        return transaction;
    }



    public void setConnectionMode(BBDeviceController.ConnectionMode connectionMode) {
        BaseActivity.connectionMode = connectionMode;
    }

    public static BBDeviceController getBBDeviceController() {
        return VandarConnectManagerBBPos.getBbDeviceController();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (transaction == null)
            transaction = new Transaction(this);

        vandarConnectManagerBBPos = new VandarConnectManagerBBPos(this);






    }



    public ArrayAdapter<String> getArrayAdapter() {
        return VandarConnectManagerBBPos.getArrayAdapter();
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter) {
        VandarConnectManagerBBPos.setArrayAdapter(arrayAdapter);
    }

    public List<BluetoothDevice> getFoundedDevice() {
        return VandarConnectManagerBBPos.getFoundDevices();
    }


    public String[] getDeviceName() {
        return vandarConnectManagerBBPos.DEVICE_NAMES;
    }


    public void startGetPhoneNumber(PhoneNumberListener phoneNumberListener) {
        vandarConnectManagerBBPos.startGetPhoneNumber(phoneNumberListener);
    }

    public void addBTConnectListener(BTConnectListener btConnectListener) {
        vandarConnectManagerBBPos.addBTConnectListener(btConnectListener);

    }


    public void addBTDisconnectListener(BTDisconnectListener btDisconnectListener) {
        vandarConnectManagerBBPos.addBTDisconnectListener(btDisconnectListener);

    }

    public void addSerialConnectListener(SerialConnectListener serialConnectListener) {
        vandarConnectManagerBBPos.addSerialConnectListener(serialConnectListener);
    }


    public void addSerialDisconnectListener(SerialDisconnectListener serialDisconnectListener) {
        vandarConnectManagerBBPos.addSerialDisConnectListener(serialDisconnectListener);
    }


    public void addDeviceInfoListener(DeviceInfoListener deviceInfoListener) {
        vandarConnectManagerBBPos.addDeviceInfoListener(deviceInfoListener);
    }

    public void stopConnection() {
        vandarConnectManagerBBPos.stopConnection();
    }

    public void setDefaultDevice() {
        vandarConnectManagerBBPos.setDefaultDevice();
    }

    public void removeDefaultDevice() {
        vandarConnectManagerBBPos.removeDefaultDevice();
    }

    public void turnOnBluetooth() {
        vandarConnectManagerBBPos.turnOnBluetooth();
    }


    public void turnOffBluetooth() {
        try {
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            boolean isEnabled = bluetoothAdapter.isEnabled();
            if (isEnabled) {
                bluetoothAdapter.disable();
            }
        } catch (Exception ex) {
            Log.e("Off", ex.getMessage());
        }
    }


    public void startPrint(byte[] paperReceiptData) {
        vandarConnectManagerBBPos.startPrint(paperReceiptData);

    }


    public void setSerialPinEnterListener(SerialPinCountEnterListener listener) {
        vandarConnectManagerBBPos.setSerialPinEnterListener(listener);
    }




}





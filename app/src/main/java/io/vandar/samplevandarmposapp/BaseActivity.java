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

import io.vandar.mpos.transaction.VandarTransactionManager;
import io.vandar.mpos.transaction.connection.VandarConnectionManager;
import io.vandar.mpos.transaction.connection.interfaces.BTConnectListener;
import io.vandar.mpos.transaction.connection.interfaces.BTDisconnectListener;
import io.vandar.mpos.transaction.connection.interfaces.DeviceInfoListener;
import io.vandar.mpos.transaction.connection.interfaces.PhoneNumberListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialConnectListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialDisconnectListener;
import io.vandar.mpos.transaction.connection.interfaces.SerialPinCountEnterListener;


public abstract class BaseActivity extends AppCompatActivity  {


    protected static VandarTransactionManager transactionManager;
    protected static BBDeviceController.ConnectionMode connectionMode;
    private static VandarConnectionManager vandarConnectionManager;


    public static VandarTransactionManager getTransaction() {
        return transactionManager;
    }



    public void setConnectionMode(BBDeviceController.ConnectionMode connectionMode) {
        BaseActivity.connectionMode = connectionMode;
    }

    public static BBDeviceController getBBDeviceController() {
        return VandarConnectionManager.getBbDeviceController();

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        if (transactionManager == null)
            transactionManager = new VandarTransactionManager(this);

        vandarConnectionManager = new VandarConnectionManager(this);



    }



    public ArrayAdapter<String> getArrayAdapter() {
        return VandarConnectionManager.getArrayAdapter();
    }

    public void setArrayAdapter(ArrayAdapter<String> arrayAdapter) {
        VandarConnectionManager.setArrayAdapter(arrayAdapter);
    }

    public List<BluetoothDevice> getFoundedDevice() {
        return VandarConnectionManager.getFoundDevices();
    }


    public String[] getDeviceName() {
        return vandarConnectionManager.DEVICE_NAMES;
    }


    public void startGetPhoneNumber(PhoneNumberListener phoneNumberListener) {
        vandarConnectionManager.startGetPhoneNumber(phoneNumberListener);
    }

    public void addBTConnectListener(BTConnectListener btConnectListener) {
        vandarConnectionManager.addBTConnectListener(btConnectListener);

    }


    public void addBTDisconnectListener(BTDisconnectListener btDisconnectListener) {
        vandarConnectionManager.addBTDisconnectListener(btDisconnectListener);

    }

    public void addSerialConnectListener(SerialConnectListener serialConnectListener) {
        vandarConnectionManager.addSerialConnectListener(serialConnectListener);
    }


    public void addSerialDisconnectListener(SerialDisconnectListener serialDisconnectListener) {
        vandarConnectionManager.addSerialDisConnectListener(serialDisconnectListener);
    }


    public void addDeviceInfoListener(DeviceInfoListener deviceInfoListener) {
        vandarConnectionManager.addDeviceInfoListener(deviceInfoListener);
    }

    public void stopConnection() {
        vandarConnectionManager.stopConnection();
    }

    public void setDefaultDevice() {
        vandarConnectionManager.setDefaultDevice();
    }

    public void removeDefaultDevice() {
        vandarConnectionManager.removeDefaultDevice();
    }

    public void turnOnBluetooth() {
        vandarConnectionManager.turnOnBluetooth();
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
        vandarConnectionManager.startPrint(paperReceiptData);

    }


    public void setSerialPinEnterListener(SerialPinCountEnterListener listener) {
        vandarConnectionManager.setSerialPinEnterListener(listener);
    }




}





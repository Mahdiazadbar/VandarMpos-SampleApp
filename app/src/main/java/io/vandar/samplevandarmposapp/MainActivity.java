package io.vandar.samplevandarmposapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import io.vandar.mpos.transaction.Interfaces.BalanceTransactionListener;
import io.vandar.mpos.transaction.Interfaces.BuyTransActionListener;
import io.vandar.mpos.transaction.Interfaces.TransactionListener;
import io.vandar.mpos.transaction.Transaction;
import io.vandar.mpos.transaction.connection.errors.CheckCardError;
import io.vandar.mpos.transaction.connection.errors.GetPhoneNumberError;
import io.vandar.mpos.transaction.connection.errors.PinEntryError;
import io.vandar.mpos.transaction.model.VandarBalanceResponse;
import io.vandar.mpos.transaction.model.VandarBuyResponse;

public class MainActivity extends BaseActivity implements TransactionListener, BalanceTransactionListener, BuyTransActionListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.connect).setOnClickListener(v -> {
            startActivity(new Intent(this, ConnectionActivity.class));
        });


        findViewById(R.id.balance).setOnClickListener(v -> {
            getTransaction().balance(this,this);
        });

        findViewById(R.id.buy).setOnClickListener(v -> {
            getTransaction().saleTransAction(this,this,"1000");
        });


    }

    @Override
    public void onWaitingAcceptAmount() {
        Toast.makeText(this, "WaitingAcceptAmount", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onWaitingEnterPin() {
        Toast.makeText(this, "WaitingEnterPin", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStartTransaction() {
        Toast.makeText(this, "StartTransaction", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onPinEnter() {
        Toast.makeText(this, "PinEnter", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onWaitingForCard() {
        Toast.makeText(this, "WaitingForCard", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCancelAmount() {
        Toast.makeText(this, "CancelAmount", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onCheckCardError(CheckCardError checkCardError) {
        Toast.makeText(this, "CheckCardError" + checkCardError.toString(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPinEntryError(PinEntryError pinEntryError) {
        Toast.makeText(this, "PinEntryError" + pinEntryError.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onGetPhoneNumberError(GetPhoneNumberError getPhoneNumberError) {
        Toast.makeText(this, "GetPhoneNumberError" + getPhoneNumberError.toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBalanceTransactionResponse(VandarBalanceResponse vandarBalanceResponse) {
        Toast.makeText(this, vandarBalanceResponse.getReturnCodeType().getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBalanceTransactionFail() {
        Toast.makeText(this, "BalanceTransactionFail", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBuyTransactionResponse(VandarBuyResponse vandarBuyResponse) {
        Toast.makeText(this, vandarBuyResponse.getReturnCodeType().getMessage(), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onBuyTransactionFail() {
        Toast.makeText(this, "BalanceTransactionFail", Toast.LENGTH_SHORT).show();

    }
}

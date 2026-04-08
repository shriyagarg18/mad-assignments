package com.example.currencyconverter;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner from, to;
    TextView result;
    Button convertBtn;
    Switch themeSwitch;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        from = findViewById(R.id.fromCurrency);
        to = findViewById(R.id.toCurrency);
        result = findViewById(R.id.result);
        convertBtn = findViewById(R.id.convertBtn);
        themeSwitch = findViewById(R.id.themeSwitch);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                currencies
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convertBtn.setOnClickListener(v -> {
            String val = amount.getText().toString();
            if(val.isEmpty()){
                result.setText("Enter amount");
                return;
            }

            double input = Double.parseDouble(val);
            String fromCur = from.getSelectedItem().toString();
            String toCur = to.getSelectedItem().toString();

            double output = convert(input, fromCur, toCur);
            result.setText("Result: " + output);
        });

        themeSwitch.setOnCheckedChangeListener((btn, isChecked) -> {
            if(isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        });
    }

    double convert(double amount, String from, String to) {
        double inr = 0;

        if(from.equals("INR")) inr = amount;
        if(from.equals("USD")) inr = amount * 83;
        if(from.equals("EUR")) inr = amount * 90;
        if(from.equals("JPY")) inr = amount * 0.55;

        if(to.equals("INR")) return inr;
        if(to.equals("USD")) return inr / 83;
        if(to.equals("EUR")) return inr / 90;
        if(to.equals("JPY")) return inr / 0.55;

        return amount;
    }
}
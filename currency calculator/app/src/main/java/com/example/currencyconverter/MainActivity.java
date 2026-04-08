package com.example.currencyconverter;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner fromCurrency, toCurrency;
    TextView result;
    Button convertBtn, swapBtn;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        amount = findViewById(R.id.amount);
        fromCurrency = findViewById(R.id.fromCurrency);
        toCurrency = findViewById(R.id.toCurrency);
        result = findViewById(R.id.result);
        convertBtn = findViewById(R.id.convertBtn);
        swapBtn = findViewById(R.id.swapBtn);

        // Spinner adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                currencies
        );

        fromCurrency.setAdapter(adapter);
        toCurrency.setAdapter(adapter);

        // Convert button click
        convertBtn.setOnClickListener(v -> convert());

        // Swap button click
        swapBtn.setOnClickListener(v -> {
            int fromPos = fromCurrency.getSelectedItemPosition();
            int toPos = toCurrency.getSelectedItemPosition();

            fromCurrency.setSelection(toPos);
            toCurrency.setSelection(fromPos);
        });
    }

    private void convert() {
        String amtStr = amount.getText().toString().trim();

        if (amtStr.isEmpty()) {
            result.setText("Enter amount");
            return;
        }

        double amt = Double.parseDouble(amtStr);

        String from = fromCurrency.getSelectedItem().toString();
        String to = toCurrency.getSelectedItem().toString();

        double converted = convertCurrency(from, to, amt);

        String symbol = getSymbol(to);

        result.setText(symbol + " " + String.format("%.2f", converted));
    }

    private double convertCurrency(String from, String to, double amt) {

        double inr;

        // Convert FROM → INR
        switch (from) {
            case "USD": inr = amt * 83; break;
            case "EUR": inr = amt * 90; break;
            case "JPY": inr = amt * 0.55; break;
            default: inr = amt; // INR
        }

        // Convert INR → TO
        switch (to) {
            case "USD": return inr / 83;
            case "EUR": return inr / 90;
            case "JPY": return inr / 0.55;
            default: return inr; // INR
        }
    }

    // Currency Symbols
    private String getSymbol(String currency) {
        switch (currency) {
            case "INR": return "₹";
            case "USD": return "$";
            case "EUR": return "€";
            case "JPY": return "¥";
            default: return "";
        }
    }
}
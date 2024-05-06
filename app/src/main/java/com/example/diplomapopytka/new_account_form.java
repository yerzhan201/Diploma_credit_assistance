package com.example.diplomapopytka;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.diplomapopytka.R;
import com.example.diplomapopytka.accounts;
import com.example.diplomapopytka.account_card;
import com.example.diplomapopytka.card_adapter;
import com.example.diplomapopytka.DatabaseHelper;

public class new_account_form extends AppCompatActivity {

    EditText Bank_name, Balance;
    Button save_loan_button;
    ImageView check1, check2, check3, check4, check5, check6, check7;
    CardView color1, color2, color3, color4, color5, color6, color7;
    LinearLayout colorpicker;
    CardView new_loan_card;
    String color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_card_form);

        // Initialize views
        Bank_name = findViewById(R.id.Bank_name);
        Balance = findViewById(R.id.Balance);
        save_loan_button = findViewById(R.id.save_loan_button);
        check1 = findViewById(R.id.check1);
        check2 = findViewById(R.id.check2);
        check3 = findViewById(R.id.check3);
        check4 = findViewById(R.id.check4);
        check5 = findViewById(R.id.check5);
        check6 = findViewById(R.id.check6);
        check7 = findViewById(R.id.check7);
        color1 = findViewById(R.id.color1);
        color2 = findViewById(R.id.color2);
        color3 = findViewById(R.id.color3);
        color4 = findViewById(R.id.color4);
        color5 = findViewById(R.id.color5);
        color6 = findViewById(R.id.color6);
        color7 = findViewById(R.id.color7);
        colorpicker = findViewById(R.id.colorpicker);
        new_loan_card = findViewById(R.id.new_loan_card);

        // Set default color
        color = "#FFFFFF";
        ((CardView) new_loan_card).setCardBackgroundColor(Color.parseColor(color));

        // Set click listeners for color pickers
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.VISIBLE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.GONE);
                color = "#FF1C45";
                }
        });

        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.VISIBLE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.GONE);
                color = "#FF6347";
                }
        });

        color3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.VISIBLE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.GONE);
                color = "#FFD700";
                 }
        });

        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.VISIBLE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.GONE);
                color = "#32CD32";
                }
        });

        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.VISIBLE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.GONE);
                color = "#1E90FF";
                }
        });

        color6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.VISIBLE);
                check7.setVisibility(View.GONE);
                color = "#9370DB";
                }
        });

        color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                check1.setVisibility(View.GONE);
                check2.setVisibility(View.GONE);
                check3.setVisibility(View.GONE);
                check4.setVisibility(View.GONE);
                check5.setVisibility(View.GONE);
                check6.setVisibility(View.GONE);
                check7.setVisibility(View.VISIBLE);
                color = "#FFC0CB";
                }
        });

        // Set click listener for save button
        save_loan_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get bank name and balance
                String bankName = Bank_name.getText().toString();
                String balance = Balance.getText().toString();
                account_card accountCard = new account_card(bankName,balance,color);
                accountCard.setBankName(bankName);
                accountCard.setBalance(balance);
                accountCard.setColor(color);

                // Insert new account into database
                DatabaseHelper db = new DatabaseHelper(new_account_form.this);
                db.addAccountCard(accountCard);

                // Start accounts activity
                Intent intent = new Intent(new_account_form.this, accounts.class);
                startActivity(intent);
            }
        });
    }
}
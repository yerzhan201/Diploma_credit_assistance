package com.example.diplomapopytka;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.diplomapopytka.R;
import com.example.diplomapopytka.accounts;
import com.example.diplomapopytka.account_card;
import com.example.diplomapopytka.card_adapter;
import com.example.diplomapopytka.DatabaseHelper;

public class update_account_card extends AppCompatActivity {

    EditText Bank_name, Balance;
    Button updateButton, deleteButton;
    ImageView check1, check2, check3, check4, check5, check6, check7;
    CardView color1, color2, color3, color4, color5, color6, color7;
    LinearLayout colorpicker;
    CardView new_loan_card;
    String color;

    private DatabaseHelper db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_account_card);


        // Initialize views
        Bank_name = findViewById(R.id.Bank_name);
        Balance = findViewById(R.id.Balance);
        db = new DatabaseHelper(update_account_card.this);

        String bankName = getIntent().getStringExtra("bankName");
        String balance = getIntent().getStringExtra("balance");
        color = getIntent().getStringExtra("color");
        Bank_name.setText(bankName);
        Balance.setText(balance);


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

        updateButton = findViewById(R.id.update_loan_button);
        deleteButton = findViewById(R.id.delete_button);
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
        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("id", -1);
                // Get bank name and balance
                String bankName = Bank_name.getText().toString();
                String balance = Balance.getText().toString();
                account_card accountCard = new account_card(id, bankName,balance,color);
                accountCard.setBankName(bankName);
                accountCard.setBalance(balance);
                accountCard.setColor(color);

                // Insert new account into database
                DatabaseHelper db = new DatabaseHelper(update_account_card.this);
                db.updateAccountCard(accountCard);

                // Start accounts activity
                Intent intent = new Intent(update_account_card.this, accounts.class);
                startActivity(intent);
            }
        });
        int id = getIntent().getIntExtra("id", -1);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(update_account_card.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.delete_loan, null); // replace with your layout file name
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();

                Button yesButton = dialogView.findViewById(R.id.button_yes);
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete the loan from the database
                        db.deleteaccountcard(id);

                        // Go back to the add_loan activity
                        Intent intent = new Intent(update_account_card.this, accounts.class);
                        startActivity(intent);
                    }
                });

                Button noButton = dialogView.findViewById(R.id.button_no);
                noButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Handle no button click
                        alertDialog.dismiss();
                    }
                });
                alertDialog.show();
            }
        });
    }
}
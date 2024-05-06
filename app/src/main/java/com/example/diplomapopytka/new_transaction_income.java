package com.example.diplomapopytka;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;

public class new_transaction_income extends AppCompatActivity {
    private String selectedCategory;
    private DatabaseHelperTransactions db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.income_transaction);
        db = new DatabaseHelperTransactions(this);

        // Initialize views
        CardView whiteBlock = findViewById(R.id.white_block);
        CardView whiteBlock2 = findViewById(R.id.white_block2);
        CardView whiteBlock3 = findViewById(R.id.white_block3);
        Button saveButton = findViewById(R.id.save_button);
        Button dateInput = findViewById(R.id.date);
        EditText inputNumber = findViewById(R.id.input_number);
        ImageView back = findViewById(R.id.back);
        ImageView add2 = findViewById(R.id.add2);
        ImageView add3 = findViewById(R.id.add3);
        RelativeLayout incomeLayout = findViewById(R.id.income);
        RelativeLayout outcomeLayout = findViewById(R.id.outcome);
        RelativeLayout numTransaction = findViewById(R.id.num_transaction);
        TextView totalBalanceText = findViewById(R.id.total_balance_text);
        TextView category = findViewById(R.id.Category);
        TextView account = findViewById(R.id.Account);
        CardView categoryCard = findViewById(R.id.category_card);
        CardView categoryCard2 = findViewById(R.id.category_card2);

        TextView categoryHome = findViewById(R.id.category_home);
        TextView categoryPhone = findViewById(R.id.category_trade);

        //CardView cardItem = findViewById(R.id.card_item);//
        TextView cardName = findViewById(R.id.card_name);
        TextView incomeText = findViewById(R.id.get_started_string1);
        TextView outcomeText = findViewById(R.id.get_started_string2);


        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(new_transaction_income.this,
                        new DatePickerDialog.OnDateSetListener() {
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                dateInput.setText(dayOfMonth + "/"
                                        + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });



        outcomeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outcomeLayout.setSelected(true);
                incomeLayout.setSelected(false);
                incomeText.setSelected(false);
                outcomeText.setSelected(true);
                Intent intent = new Intent(new_transaction_income.this, new_transaction_outcome.class);
                startActivity(intent);
            }
                // Save data for outcome

        });


        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deselect all cards
                categoryCard.setSelected(false);
                categoryCard2.setSelected(false);


                // Select the clicked card
                TextView textView = v.findViewById(v.getId() == R.id.category_card ? R.id.category_home : R.id.category_trade);
                selectedCategory = textView.getText().toString();
                v.setSelected(true);
            }
        };

        categoryCard.setOnClickListener(cardClickListener);
        categoryCard2.setOnClickListener(cardClickListener);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sumString = inputNumber.getText().toString();
                String sum = String.valueOf(Double.parseDouble(sumString));
                String date = dateInput.getText().toString();
                String category = selectedCategory;
                String type = "income";

                Transaction transaction = new Transaction(sum, category, type);
                db.addTransaction(category, Double.parseDouble(sum));

                // Handle save button click
                Intent intent = new Intent(new_transaction_income.this, history.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and go back to the main_page
            }
        });


        // Set other view properties
        whiteBlock.setCardElevation(0f);
        whiteBlock2.setCardElevation(0f);
        whiteBlock3.setCardElevation(0f);
        saveButton.setElevation(0f);

    }
}
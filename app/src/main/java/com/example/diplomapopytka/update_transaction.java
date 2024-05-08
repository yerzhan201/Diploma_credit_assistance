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

public class update_transaction extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_transaction_outcome);

        // Initialize views
        CardView whiteBlock = findViewById(R.id.white_block);
        CardView whiteBlock2 = findViewById(R.id.white_block2);
        CardView whiteBlock3 = findViewById(R.id.white_block3);

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
        CardView categoryCard3 = findViewById(R.id.category_card3);
        CardView categoryCard4 = findViewById(R.id.category_card4);
        CardView categoryCard5 = findViewById(R.id.category_card5);
        TextView categoryHome = findViewById(R.id.category_home);
        TextView categoryPhone = findViewById(R.id.category_Phone);
        TextView categoryHealth = findViewById(R.id.category_health);
        TextView categoryFood = findViewById(R.id.category_food);
        TextView categoryCar = findViewById(R.id.category_car);
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(update_transaction.this,
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
        // Set click listeners
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
            }
        });




        View.OnClickListener cardClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Deselect all cards
                categoryCard.setSelected(false);
                categoryCard2.setSelected(false);
                categoryCard3.setSelected(false);
                categoryCard4.setSelected(false);
                categoryCard5.setSelected(false);

                // Select the clicked card
                v.setSelected(true);
            }
        };

        categoryCard.setOnClickListener(cardClickListener);
        categoryCard2.setOnClickListener(cardClickListener);
        categoryCard3.setOnClickListener(cardClickListener);
        categoryCard4.setOnClickListener(cardClickListener);
        categoryCard5.setOnClickListener(cardClickListener);

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

        totalBalanceText.setText("New transaction");
        category.setText("Category");
        account.setText("Account");
        categoryHome.setText("Home");
        categoryPhone.setText("Phone");
        categoryHealth.setText("Health");
        categoryFood.setText("Food");
        categoryCar.setText("Car");
        cardName.setText("₸ 250 000. 02 ");
    }
}
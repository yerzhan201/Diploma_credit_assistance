package com.example.diplomapopytka;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;

public class update_transaction_income extends AppCompatActivity {
    //private String selectedCategory;

    private DatabaseHelperTransactions db;
    private String selectedCategory = "Default Category"; // Set default category
    private String type = "income";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_income_transaction);
        db = new DatabaseHelperTransactions(this);
        int id = getIntent().getIntExtra("id", -1);
        Transaction transaction = db.getTransaction(id);

        // Initialize views
        CardView whiteBlock = findViewById(R.id.white_block);
        CardView whiteBlock2 = findViewById(R.id.white_block2);
        CardView whiteBlock3 = findViewById(R.id.white_block3);

        EditText transaction_name=findViewById(R.id.input_transaction_name);
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
        Button updateButton = findViewById(R.id.update_loan_button);
        Button deleteButton = findViewById(R.id.delete_button);

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

                DatePickerDialog datePickerDialog = new DatePickerDialog(update_transaction_income.this,
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
        transaction_name.setText(transaction.getName());
        dateInput.setText(transaction.getDate()); // Set the dateInput to the transaction's date
        inputNumber.setText(String.valueOf(transaction.getSum()));
        selectedCategory = transaction.getCategory();


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(update_transaction_income.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.delete_loan, null); // replace with your layout file name
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();

                Button yesButton = dialogView.findViewById(R.id.button_yes);
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete the loan from the database
                        db.deleteTransaction(id);

                        // Go back to the add_loan activity
                        Intent intent = new Intent(update_transaction_income.this, history.class);
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

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = getIntent().getIntExtra("id", -1);
                String transactionName = transaction_name.getText().toString();
                String sumString = inputNumber.getText().toString();
                String sum = String.valueOf(Double.parseDouble(sumString));
                String date = dateInput.getText().toString();
                String category = selectedCategory;
                String type = "income";
                DatabaseHelperTransactions db = new DatabaseHelperTransactions(update_transaction_income.this);

                Transaction transaction = new Transaction(id,transactionName,sum, category, type);
                db.updateTransaction(id,transactionName,Double.parseDouble(sum), category, type);

                // Handle save button click
                Intent intent = new Intent(update_transaction_income.this, history.class);
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


    }
}
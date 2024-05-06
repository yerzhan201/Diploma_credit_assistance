package com.example.diplomapopytka;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import android.text.TextWatcher;

import android.text.Editable;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Calendar;
import java.util.List;

public class update_loan extends AppCompatActivity {

    EditText dateInput;
    private int id;
    private DatabaseHelper db;
    private Spinner unitSpinner;

    private List<Loan> loanList;

    DatePickerDialog datePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Add this line
         // Add this line
        setContentView(R.layout.update_loan);

        unitSpinner = findViewById(R.id.unit_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loan_term_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);

        db = new DatabaseHelper(this);
        CardView whiteBlock1 = findViewById(R.id.white_block_1);
        CardView whiteBlock2 = findViewById(R.id.update_loan_card);
        ImageView back1 = findViewById(R.id.back_1);
        Button updateLoanButton = findViewById(R.id.update_loan_button);
        EditText bankNameEditText = findViewById(R.id.bank_name1);
        EditText loanAmountEditText = findViewById(R.id.loan_amount1);
        EditText monthlyPaymentEditText = findViewById(R.id.monthly_payment1);
        EditText loanTermEditText = findViewById(R.id.loan_term1);
        Button dateInput = findViewById(R.id.loan_date1);

        EditText loanNameEditText = findViewById(R.id.loan_name1);
        Button deleteButton = findViewById(R.id.delete_button);



            // Get the loan ID from the intent extras
        // Get the extras from the intent
        Intent receivedIntent = getIntent();
        String extraLoanName = receivedIntent.getStringExtra("loan_name");
        String extraBankName = receivedIntent.getStringExtra("bank_name");
        String extraLoanAmount = receivedIntent.getStringExtra("loan_amount");
        String extraMonthlyPayment = receivedIntent.getStringExtra("monthly_payment");
        String extraLoanDate = receivedIntent.getStringExtra("loan_date");
        String extraLoanTerm = receivedIntent.getStringExtra("loan_term");

        // Set the text of the EditText fields
        loanNameEditText.setText(extraLoanName);
        bankNameEditText.setText(extraBankName);
        loanAmountEditText.setText(extraLoanAmount);
        monthlyPaymentEditText.setText(extraMonthlyPayment);
        dateInput.setText(extraLoanDate);
        loanTermEditText.setText(extraLoanTerm);



        back1.setOnClickListener(v -> finish());

        dateInput.setOnClickListener(v -> {
            final Calendar c = Calendar.getInstance();
            int mYear = c.get(Calendar.YEAR);
            int mMonth = c.get(Calendar.MONTH);
            int mDay = c.get(Calendar.DAY_OF_MONTH);

            datePickerDialog = new DatePickerDialog(update_loan.this,
                    (view, year, monthOfYear, dayOfMonth) -> dateInput.setText(dayOfMonth + "/"
                            + (monthOfYear + 1) + "/" + year), mYear, mMonth, mDay);
            datePickerDialog.show();
        });
        int loanId = getIntent().getIntExtra("loan_id", -1);


        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(update_loan.this);
                LayoutInflater inflater = getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.delete_loan, null); // replace with your layout file name
                builder.setView(dialogView);
                AlertDialog alertDialog = builder.create();

                Button yesButton = dialogView.findViewById(R.id.button_yes);
                yesButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Delete the loan from the database
                        db.deleteLoan(loanId);

                        // Go back to the add_loan activity
                        Intent intent = new Intent(update_loan.this, add_loan.class);
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
        loanTermEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().equals("")) {
                    int input = Integer.parseInt(s.toString());
                    String selectedUnit = unitSpinner.getSelectedItem().toString();

                    if (selectedUnit.equals("years") && input > 30) { // Set your maximum limit for years here
                        loanTermEditText.setError("Maximum term is 30 years");
                    } else if (selectedUnit.equals("months") && input > 360) { // Set your maximum limit for months here
                        loanTermEditText.setError("Maximum term is 360 months");
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This space intentionally left blank
            }
        });
        updateLoanButton.setOnClickListener(v -> {
            // Get loan details from EditText fields
            String loanName = loanNameEditText.getText().toString();
            String bankName = bankNameEditText.getText().toString();
            String loanAmount = loanAmountEditText.getText().toString();
            String monthlyPayment =monthlyPaymentEditText.getText().toString();
            String loanDate = dateInput.getText().toString();
            String loanTerm = loanTermEditText.getText().toString();
            if (loanName.isEmpty() || bankName.isEmpty() || loanAmount.isEmpty() || monthlyPayment.isEmpty() || loanDate.isEmpty() || loanTerm.isEmpty()) {
                Toast.makeText(update_loan.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                return;
            }
            int id = getIntent().getIntExtra("loan_id", -1);
            Loan loan = new Loan(id,loanName, bankName, loanDate, monthlyPayment, loanAmount, loanTerm);
            db.updateLoan(loan);
            Toast.makeText(update_loan.this, "Saved " + loanName, Toast.LENGTH_SHORT).show();
            if (update_loan.this != null) {
                // Go
                Intent intent = new Intent(update_loan.this, add_loan.class);
                startActivity(intent);
            }
        });
        whiteBlock1.setCardElevation(0f);
        whiteBlock2.setCardElevation(0f);
    }
}
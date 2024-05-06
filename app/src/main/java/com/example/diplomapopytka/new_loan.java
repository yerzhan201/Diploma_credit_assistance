package com.example.diplomapopytka;
import com.example.diplomapopytka.add_loan;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;

import android.text.Editable;


import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.text.TextWatcher;


import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.widget.ArrayAdapter;



public class new_loan extends AppCompatActivity {

    EditText dateInput;
    private EditText loanTermEditText;
    private Spinner unitSpinner;
    private DatabaseHelper db;
    DatePickerDialog datePickerDialog;
    private List<Loan> loanList;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_loan);
        db = new DatabaseHelper(this);
        loanList = new ArrayList<>();
        loanTermEditText = findViewById(R.id.loan_term);
        unitSpinner = findViewById(R.id.unit_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.loan_term_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        unitSpinner.setAdapter(adapter);






        // Initialize views
        CardView whiteBlock1 = findViewById(R.id.white_block_1);
        CardView whiteBlock2 = findViewById(R.id.new_loan_card);
        ImageView back1 = findViewById(R.id.back_1);
        Button saveLoanButton = findViewById(R.id.save_loan_button);
        EditText bankNameEditText = (EditText) findViewById(R.id.bank_name);
        EditText loanAmountEditText = (EditText) findViewById(R.id.loan_amount);
        EditText monthlyPaymentEditText = (EditText) findViewById(R.id.monthly_payment);

        EditText loanTermEditText = (EditText) findViewById(R.id.loan_term);



        Button dateInput = findViewById(R.id.loan_date);
        EditText loanNameEditText = (EditText) findViewById(R.id.loan_name);

        Button deleteButton = findViewById(R.id.delete_button);



        // Set click listeners

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
        dateInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(new_loan.this,
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
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // This will close the current activity and go back to the main_page
            }
        });
        saveLoanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the loan details from the EditText fields
                String loanName = loanNameEditText.getText().toString();
                String bankName = bankNameEditText.getText().toString();
                String loanAmount = loanAmountEditText.getText().toString();
                String monthlyPayment = monthlyPaymentEditText.getText().toString();
                String loanDate = dateInput.getText().toString();
                String loanTerm = loanTermEditText.getText().toString();
                int id = getIntent().getIntExtra("loan_id", -1);
                // Validate the loan details
                if (loanName.isEmpty() || bankName.isEmpty() || loanAmount.isEmpty() || monthlyPayment.isEmpty() || loanDate.isEmpty() || loanTerm.isEmpty() ) {
                    Toast.makeText(new_loan.this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Create a new Loan object
                Loan loan = new Loan(id,loanName, bankName, loanDate, monthlyPayment, loanAmount,loanTerm);

                // Save the loan to the database
                db.addLoan(loan);
                Toast.makeText(new_loan.this, "Saved " + loanName, Toast.LENGTH_SHORT).show();
                loanList.add(loan);
                // Go back to the main activity
                Intent intent = new Intent(new_loan.this, add_loan.class);
                startActivity(intent);
            }
        });
        // Set other view properties
        whiteBlock1.setCardElevation(0f);
        whiteBlock2.setCardElevation(0f);
    }
}

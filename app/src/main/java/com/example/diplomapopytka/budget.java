package com.example.diplomapopytka;



import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class budget extends AppCompatActivity {
    private SQLiteDatabase database;
    private EditText homeInput;
    private EditText kommunalkaInput;
    private EditText internetInput;
    private TextView planTextCategory;
    private TextView planTextCategory2;
    private EditText inputHome;
    private EditText inputCom;
    private EditText inputInternet;
    private SharedPreferences sharedPreferences;
    private EditText inputNumber;
    private TextView planning;
    private TextView plansum;
    private TextView plancomment;
    private ProgressBar circularProgressbar;
    private TextView percentage;
    private EditText input_kaspi;
    private EditText input_forte;
    private EditText input_halyk;
    private EditText planSum;

    private EditText inputEarnings;
    private TextView planComment;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // You can initialize and use your views here.
        // For example:

        // Find the white_block CardView
        androidx.cardview.widget.CardView whiteBlock = findViewById(R.id.white_block);

        // Find the total_balance_text TextView
        circularProgressbar = findViewById(R.id.circularProgressbar); // Your progress bar

        TextView totalBalanceText = findViewById(R.id.total_balance_text);

        // Find the Plan RelativeLayout
        RelativeLayout plan = findViewById(R.id.Plan);

        // Find the remain RelativeLayout
        RelativeLayout remain = findViewById(R.id.remain);

        // Find the input_number TextView
        TextView inputNumber = findViewById(R.id.input_number);

        // Find the Planning TextView
        TextView planning = findViewById(R.id.Planning);

        // Find the Plansum TextView
        TextView plansum = findViewById(R.id.Plansum);

        // Find the Plancomment TextView
        TextView plancomment = findViewById(R.id.Plancomment);

        // Find the circularProgressbar ProgressBar
        ProgressBar circularProgressbar = findViewById(R.id.circularProgressbar);

        // Find the percentage TextView
        TextView percentage = findViewById(R.id.percentage);
        planSum = findViewById(R.id.Plansum);
        planComment = findViewById(R.id.Plancomment);
        inputEarnings = findViewById(R.id.input_number);
        inputHome = findViewById(R.id.input_home);
        input_kaspi= findViewById(R.id.input_kaspi);
        input_forte= findViewById(R.id.input_forte);
        input_halyk= findViewById(R.id.input_halyk);
        inputCom = findViewById(R.id.input_com);
        inputInternet = findViewById(R.id.input_internet);
        planTextCategory2 = findViewById(R.id.plan_text_category2);
        planTextCategory = findViewById(R.id.plan_text_category);

        // Find the bottom_navigation BottomNavigationView
        loadValues();

        com.google.android.material.bottomnavigation.BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                calculateTotal();
                saveValues();
            }
        };
        input_kaspi.addTextChangedListener(textWatcher);
        input_forte.addTextChangedListener(textWatcher);
        input_halyk.addTextChangedListener(textWatcher);
        inputEarnings.addTextChangedListener(textWatcher);

        inputHome.addTextChangedListener(textWatcher);
        inputCom.addTextChangedListener(textWatcher);
        inputInternet.addTextChangedListener(textWatcher);

        // Set a click listener for the Plan RelativeLayout
        plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
            }
        });
        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            if (id == R.id.navigation_add) {
                Intent intent = new Intent(budget.this, new_transaction_income.class);
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_account) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(budget.this, account.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }

            if (id == R.id.navigation_home) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(budget.this, main_page.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_history) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(budget.this, history.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            return false;

        });

        // Set a click listener for the remain RelativeLayout
        remain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
            }
        });
    }
    private void calculateTotal() {
        double home = parseDouble(inputHome.getText().toString());
        double com = parseDouble(inputCom.getText().toString());
        double internet = parseDouble(inputInternet.getText().toString());
        double total = home + com + internet;
        double kaspi = parseDouble(input_kaspi.getText().toString());
        double forte = parseDouble(input_forte.getText().toString());
        double halyk = parseDouble(input_halyk.getText().toString());
        double total_sum = kaspi + forte + halyk;
        double grandTotal = total + total_sum;
        planSum.setText(String.format("%.2f ₸", grandTotal));
        double earnings = parseDouble(inputEarnings.getText().toString()); // Get the earnings value
        double remaining = earnings - grandTotal; // Calculate the remaining budget
        planComment.setText(String.format("%.2f ₸ left to budget", remaining));
        planTextCategory2.setText(String.format("%.2f ₸", total_sum));
        planTextCategory.setText(String.format("%.2f ₸", total));
        int progress = (int) ((grandTotal / earnings) * 100); // Calculate the percentage of total sum in earnings
        circularProgressbar.setProgress(progress);
    }
    private double parseDouble(String s) {
        try {
            return Double.parseDouble(s);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
    private void saveValues() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("home", inputHome.getText().toString());
        editor.putString("com", inputCom.getText().toString());
        editor.putString("internet", inputInternet.getText().toString());
        editor.putString("kaspi", input_kaspi.getText().toString());
        editor.putString("forte", input_forte.getText().toString());
        editor.putString("halyk", input_halyk.getText().toString());
        editor.putString("total_sum", planTextCategory2.getText().toString());
        editor.putString("earnings", inputEarnings.getText().toString());
        editor.putString("total", planTextCategory.getText().toString());
        editor.putInt("progress", circularProgressbar.getProgress()); // Save the progress of the ProgressBar

        editor.apply();
    }


    private void loadValues() {
        input_kaspi.setText(sharedPreferences.getString("kaspi", ""));
        input_forte.setText(sharedPreferences.getString("forte", ""));
        input_halyk.setText(sharedPreferences.getString("halyk", ""));
        inputEarnings.setText(sharedPreferences.getString("earnings", ""));
        inputHome.setText(sharedPreferences.getString("home", ""));
        inputCom.setText(sharedPreferences.getString("com", ""));
        inputInternet.setText(sharedPreferences.getString("internet", ""));
        calculateTotal();
        circularProgressbar.setProgress(sharedPreferences.getInt("progress", 0));
    }

}
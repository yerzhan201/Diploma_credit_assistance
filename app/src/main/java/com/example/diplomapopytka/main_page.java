package com.example.diplomapopytka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.diplomapopytka.Loan;
import com.example.diplomapopytka.DatabaseHelper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jjoe64.graphview.GraphView;
import java.util.List;

public class main_page extends AppCompatActivity {

    private CardView whiteBlock, cardSection1, cardSection2,  transaction1, transaction2;
    private GraphView graphView;
    private ImageView logo, menu, expand, expand2;
    private TextView totalBalance, totalBalanceText, savings, accounts, lastTransactions;
    private HorizontalScrollView horizontal, horizontal2;
    private CardView cardSection;
    private TextView loan1;
    private LinearLayout accounts_view;
    private BottomNavigationView bottomNavigationView;
    private TextView loanSum;
    private View addaccounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        whiteBlock = findViewById(R.id.white_block);
        logo = findViewById(R.id.logo);
        totalBalanceText = findViewById(R.id.total_balance_text);
        totalBalance = findViewById(R.id.total_balance);
        savings = findViewById(R.id.savings);

        totalBalance = findViewById(R.id.total_balance);
        totalBalanceText = findViewById(R.id.total_balance_text);
        savings = findViewById(R.id.savings);
        accounts = findViewById(R.id.accounts);
        lastTransactions = findViewById(R.id.LastTransactions);
        logo = findViewById(R.id.logo);
        addaccounts = findViewById(R.id.add_account);
        whiteBlock = findViewById(R.id.white_block);
        ImageView addLoan = findViewById(R.id.add_loan);
        ImageView notification = findViewById(R.id.notification_bell);

        transaction1 = findViewById(R.id.transaction_1);
        transaction2 = findViewById(R.id.transaction_2);
        horizontal = findViewById(R.id.horizontal);
        horizontal2 = findViewById(R.id.horizontal2);
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        bottomNavigationView.setOnItemSelectedListener(item -> {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            if (id == R.id.navigation_add) {
                Intent intent = new Intent(main_page.this, new_transaction_income.class);
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_account) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(main_page.this, account.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }

            if (id == R.id.navigation_budget) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(main_page.this, budget.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_history) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(main_page.this, history.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            return false;

        });
        addaccounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, accounts.class);
                startActivity(intent);
            }
        });

        addLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, new_loan.class);
                startActivity(intent);
            }
        });
        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, notification.class);
                startActivity(intent);
            }
        });
        horizontal2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(main_page.this, accounts.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetch the loans from the database
        DatabaseHelper db = new DatabaseHelper(this);
        List<Loan> loans = db.getLoans();

        // Get the LinearLayout where you want to add the mini cards
        LinearLayout layout = findViewById(R.id.loans_view);

        // Remove all existing views from the layout
        layout.removeAllViews();

        // Create a new mini card for each loan
        for (Loan loan : loans) {
            // Inflate the mini card layout
            View miniCard = getLayoutInflater().inflate(R.layout.mini_card_loan, layout, false);

            // Set the loan details
            TextView loanName = miniCard.findViewById(R.id.loan_name);
            loanName.setText(loan.getLoanName());
            TextView loanAmount = miniCard.findViewById(R.id.loan_amount);
            loanAmount.setText(String.valueOf(loan.getloanAmount()));
            miniCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Start the add_loan activity
                    Intent intent = new Intent(main_page.this, add_loan.class);
                    startActivity(intent);
                }
            });

            // Add the mini card to the layout
            layout.addView(miniCard);
        }
    }
}
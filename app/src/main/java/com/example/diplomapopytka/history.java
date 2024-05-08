package com.example.diplomapopytka;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List; // Import List for transactions

public class history extends AppCompatActivity {
    private RecyclerView transactionList;
    private BottomNavigationView bottomNavigation;
    private DatabaseHelperTransactions db;
    private TransactionAdapter transactionAdapter;;
    private EditText inputNumber;
    private TextView selectedCategory;
    private EditText typeInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);


        transactionList = findViewById(R.id.recyclerView);
        db = new DatabaseHelperTransactions(this);
        bottomNavigation = findViewById(R.id.bottom_navigation);
        transactionAdapter = new TransactionAdapter(new ArrayList<>());



        transactionList.setLayoutManager(new LinearLayoutManager(this));
        transactionList.setAdapter(transactionAdapter);
        bottomNavigationView.setSelectedItemId(R.id.navigation_history);
        bottomNavigation.setOnItemSelectedListener(item -> {
            // Handle navigation view item clicks here.
            int id = item.getItemId();
            if (id == R.id.navigation_add) {
                Intent intent = new Intent(history.this, new_transaction_income.class);
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_account) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(history.this, account.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }

            if (id == R.id.navigation_budget) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(history.this, budget.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            if (id == R.id.navigation_home) { // Assuming your account item has the ID "navigation_account"
                Intent intent = new Intent(history.this, main_page.class); // Replace with your account activity class name
                startActivity(intent);
                return true;
            }
            return false;

        });
    }
    @Override
    protected void onResume() {
        super.onResume();


        List<Transaction> transactions = db.getAllTransactions();
        transactionAdapter.updateTransactions(transactions);
        //db.addTransaction(category, sum);
        //transactionAdapter.a(updatedLoanList);

    }


}

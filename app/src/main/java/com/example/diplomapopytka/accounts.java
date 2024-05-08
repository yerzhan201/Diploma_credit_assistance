package com.example.diplomapopytka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class accounts extends AppCompatActivity {

    private CardView whiteBlock1;
    private ImageView back1;
    private RecyclerView recyclerViewAccounts;
    private ImageView addAccount;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_card_account);

        whiteBlock1 = findViewById(R.id.white_block_1);
        back1 = findViewById(R.id.back_1);
        recyclerViewAccounts = findViewById(R.id.recyclerViewaccounts);
        addAccount = findViewById(R.id.add_account);



        // Initialize the DatabaseHelper
        dbHelper = new DatabaseHelper(this);

        // Get all accounts from the database
        List<account_card> accountCards = dbHelper.getAllAccounts();

        // Create an adapter for the RecyclerView
        card_adapter adapter = new card_adapter(accountCards);


        // Set the adapter for the RecyclerView
        recyclerViewAccounts.setAdapter(adapter);
        recyclerViewAccounts.setLayoutManager(new LinearLayoutManager(this)); // Set the LayoutManager

        // You can set onClickListeners or other functionality here
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle back button click
            }
        });

        addAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accounts.this, new_account_form.class);
                startActivity(intent);
                // Handle add account button click
            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(accounts.this, main_page.class);
                startActivity(intent); // This will close the current activity and go back to the main_page
            }
        });
    }


    @Override
    protected void onResume() {
        super.onResume();
        // Update the accounts list when returning from the new_account_form activity
        List<account_card> accountCards = dbHelper.getAllAccounts();
        ((card_adapter) recyclerViewAccounts.getAdapter()).setAccounts(accountCards);
    }
}

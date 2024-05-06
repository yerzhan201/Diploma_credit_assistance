package com.example.diplomapopytka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class add_loan extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Loan> loanList = new ArrayList<>();
    private LoanAdapter loanAdapter;

    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_loan);
        db = new DatabaseHelper(this);




        // Initialize views
        CardView whiteBlock1 = findViewById(R.id.white_block_1);

        ImageView back1 = findViewById(R.id.back_1);
        ImageView logo1 = findViewById(R.id.logo_1);
        ImageView addLoan = findViewById(R.id.add_loan);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        loanAdapter = new LoanAdapter(this, new ArrayList<>());
        recyclerView.setAdapter(loanAdapter);

        // Set click listeners
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_loan.this, main_page.class);
                startActivity(intent); // This will close the current activity and go back to the main_page
            }
        });

        addLoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(add_loan.this, new_loan.class);
                startActivity(intent);
            }
        });





        // Set other view properties


    }
    @Override
    protected void onResume() {
        super.onResume();


        List<Loan> updatedLoanList = db.getLoans();
        loanAdapter.updateLoanList(updatedLoanList);

    }

}
package com.example.diplomapopytka;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class account extends Activity {

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account);

        // Find the view with the ID "about_us"
        findViewById(R.id.about_us).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start the "about_us" activity
                Intent intent = new Intent(account.this, about_us.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.back_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account.this, main_page.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.navigation_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account.this, new_transaction_income.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.navigation_home).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account.this, main_page.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(account.this, login_activity.class);
                startActivity(intent);
            }
        });
    }
}

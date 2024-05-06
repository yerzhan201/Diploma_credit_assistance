package com.example.diplomapopytka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class reg extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reg);

        Button goonButton = findViewById(R.id.login11);
        goonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(reg.this, login_activity.class); // Replace "LoginActivity" with your actual login activity name
                startActivity(intent);
            }
        });
    }
}

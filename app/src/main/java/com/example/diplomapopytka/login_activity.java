package com.example.diplomapopytka;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class login_activity extends AppCompatActivity {
	private EditText editTextUsername, editTextPassword;
	private Button buttonLogin;


	private View _bg__login_ek2;
	private TextView forgot_password_;
	private TextView sign_in;
	private View _bg__input_field_ek1;
	private TextView label;
	private View rectangle_2;
	private View _bg__input_field_ek3;
	private TextView label_ek1;
	private View rectangle_2_ek1;
	private View _bg__frame_ek1;
	private ImageView vector;
	private ImageView vector_ek1;
	private ImageView vector_ek2;
	private ImageView vector_ek3;
	private View _bg__buttons_ek1;
	private View _bg__group_8_ek1;
	private View rectangle_2_ek2;
	private TextView get_started;
	private View _bg__buttons_ek3;
	private View _bg__group_8_ek3;
	private View rectangle_2_ek3;
	private TextView get_started_ek1;
	private View _bg__group_71_ek1;
	private TextView fin_keep;
	private View _bg__group_4_ek1;
	private View _bg__group_1_ek1;
	private View rectangle_2_ek4;
	private View rectangle_3;
	private View rectangle_5;
	private View rectangle_4;
	private ImageView union;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);


		_bg__login_ek2 = (View) findViewById(R.id._bg__login_ek2);
		forgot_password_ = (TextView) findViewById(R.id.forgot_password_);
		sign_in = (TextView) findViewById(R.id.sign_in);
		_bg__input_field_ek1 = (View) findViewById(R.id._bg__input_field_ek1);

		_bg__input_field_ek3 = (View) findViewById(R.id._bg__input_field_ek3);

		_bg__frame_ek1 = (View) findViewById(R.id._bg__frame_ek1);
		vector = (ImageView) findViewById(R.id.vector);
		vector_ek1 = (ImageView) findViewById(R.id.vector_ek1);
		vector_ek2 = (ImageView) findViewById(R.id.vector_ek2);
		vector_ek3 = (ImageView) findViewById(R.id.vector_ek3);
		_bg__buttons_ek3 = (View) findViewById(R.id._bg__buttons_ek3);
		_bg__group_8_ek3 = (View) findViewById(R.id._bg__group_8_ek3);
		rectangle_2_ek3 = (View) findViewById(R.id.rectangle_2_ek3);
		get_started_ek1 = (TextView) findViewById(R.id.get_started_ek1);
		_bg__group_71_ek1 = (View) findViewById(R.id._bg__group_71_ek1);
		fin_keep = (TextView) findViewById(R.id.fin_keep);
		_bg__group_4_ek1 = (View) findViewById(R.id._bg__group_4_ek1);
		_bg__group_1_ek1 = (View) findViewById(R.id._bg__group_1_ek1);
		rectangle_2_ek4 = (View) findViewById(R.id.rectangle_2_ek4);
		rectangle_3 = (View) findViewById(R.id.rectangle_3);
		rectangle_5 = (View) findViewById(R.id.rectangle_5);
		rectangle_4 = (View) findViewById(R.id.rectangle_4);
		union = (ImageView) findViewById(R.id.union);
		editTextUsername = findViewById(R.id.editTextUsername);
		editTextPassword = findViewById(R.id.editTextPassword);
		buttonLogin = findViewById(R.id.buttonLogin);

		// Set a click listener for the login button

		buttonLogin.setOnClickListener(new View.OnClickListener() {
			@Override

			public void onClick(View view) {
				// Retrieve entered username and password
				String username = editTextUsername.getText().toString();
				String password = editTextPassword.getText().toString();

				// Implement authentication logic here
				if (username.equals("admin") && password.equals("123")) {
					// Successful login
					Toast.makeText(login_activity.this, "Login successful", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(login_activity.this, main_page.class);
					startActivity(intent);
					finish();
				} else {
					// Failed login
					Toast.makeText(login_activity.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
				}
			}
		});
		findViewById(R.id.rectangle_2_ek3).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(login_activity.this, reg.class); // Assuming your registration activity is called RegisterActivity
				startActivity(intent);
			}
		});


		//custom code goes here

	}
}


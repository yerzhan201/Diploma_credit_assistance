package com.example.diplomapopytka;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

public class login_1_activity extends Activity {
	private CheckBox checkBox = (CheckBox) findViewById(R.id.checkbox);
	private ImageView imageView;
	
	private View _bg__login_1_ek2;
	private TextView ____________________________;
	private TextView ________;
	private View _bg__iphone_status_bar_ek1;
	private View _bg__battery_ek1;
	private View border;
	private ImageView cap;
	private View capacity;
	private ImageView wifi;
	private ImageView cellular_connection;
	private View _bg__time_style_ek1;
	private TextView time;
	private View _bg__bars_home_indicator_ek1;
	private View _bg__buttons_ek1;
	private View _bg__group_8_ek1;
	private View rectangle_2;
	private TextView get_started;
	private View _bg__tracker_progress_ek1;
	private View _bg__frame_ek1;
	private ImageView vector;
	private ImageView vector_ek1;
	private View _bg__group_40_ek1;
	private View rectangle_24;
	private View rectangle_25;
	private View rectangle_26;
	private View rectangle_28;
	private View rectangle_29;
	private View _bg__group_71_ek1;
	private View _bg__radio_buttons_ek1;
	private TextView single_account;
	private View _bg__confirm_ek1;
	private ImageView vector_ek2;
	private View _bg__confirm_ek3;
	private ImageView vector_ek3;
	private View _bg__radio_buttons_ek3;
	private TextView joint_account;
	private View ellipse_17;
	private View ellipse_17_ek1;
	private TextView i_m_looking_for_a;
	private View _bg__radio_buttons_ek5;
	private TextView joint_account_ek1;
	private View ellipse_17_ek2;
	private View _bg__radio_buttons_ek7;
	private TextView joint_account_ek2;
	private View ellipse_17_ek3;
	private View _bg__radio_buttons_ek9;
	private TextView joint_account_ek3;
	private View ellipse_17_ek4;
	private ImageView vector_ek4;

	@SuppressLint("WrongViewCast")
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_1);


		_bg__login_1_ek2 = (View) findViewById(R.id._bg__login_1_ek2);
		____________________________ = (TextView) findViewById(R.id.____________________________);
		________ = (TextView) findViewById(R.id.________);
		_bg__buttons_ek1 = (View) findViewById(R.id._bg__buttons_ek1);
		_bg__group_8_ek1 = (View) findViewById(R.id._bg__group_8_ek1);
		rectangle_2 = (View) findViewById(R.id.rectangle_2);
		get_started = (TextView) findViewById(R.id.get_started_string1);
		_bg__tracker_progress_ek1 = (View) findViewById(R.id._bg__tracker_progress_ek1);
		_bg__frame_ek1 = (View) findViewById(R.id._bg__frame_ek1);
		_bg__group_40_ek1 = (View) findViewById(R.id._bg__group_40_ek1);
		rectangle_24 = (View) findViewById(R.id.rectangle_24);
		rectangle_25 = (View) findViewById(R.id.rectangle_25);
		rectangle_26 = (View) findViewById(R.id.rectangle_26);
		rectangle_28 = (View) findViewById(R.id.rectangle_28);
		rectangle_29 = (View) findViewById(R.id.rectangle_29);
		_bg__group_71_ek1 = (View) findViewById(R.id._bg__group_71_ek1);
		_bg__radio_buttons_ek1 = (View) findViewById(R.id._bg__radio_buttons_ek1);
		single_account = (TextView) findViewById(R.id.single_account);
		_bg__radio_buttons_ek3 = (View) findViewById(R.id._bg__radio_buttons_ek3);
		joint_account = (TextView) findViewById(R.id.joint_account);
		ellipse_17 = (View) findViewById(R.id.ellipse_17);
		ellipse_17_ek1 = (View) findViewById(R.id.ellipse_17_ek1);
		i_m_looking_for_a = (TextView) findViewById(R.id.i_m_looking_for_a);
		_bg__radio_buttons_ek5 = (View) findViewById(R.id._bg__radio_buttons_ek5);
		joint_account_ek1 = (TextView) findViewById(R.id.joint_account_ek1);
		ellipse_17_ek2 = (View) findViewById(R.id.ellipse_17_ek2);
		_bg__radio_buttons_ek7 = (View) findViewById(R.id._bg__radio_buttons_ek7);
		joint_account_ek2 = (TextView) findViewById(R.id.joint_account_ek2);
		ellipse_17_ek3 = (View) findViewById(R.id.ellipse_17_ek3);
		_bg__radio_buttons_ek9 = (View) findViewById(R.id._bg__radio_buttons_ek9);
		joint_account_ek3 = (TextView) findViewById(R.id.joint_account_ek3);



		setContentView(R.layout.login_1);

		checkBox = findViewById(R.id.checkbox);
		imageView = findViewById(R.id.checkbox);

		checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (isChecked) {
					// Show the ImageView when checkbox is checked
					imageView.setVisibility(View.VISIBLE);
				} else {
					// Hide the ImageView when checkbox is unchecked
					imageView.setVisibility(View.GONE);
				}
			}
		});//custom code goes here

	}



}
	
	
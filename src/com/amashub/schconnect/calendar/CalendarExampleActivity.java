package com.amashub.schconnect.calendar;

import com.amashub.schconnect.MainActivity;
import com.amashub.schconnect.R;
import com.amashub.schconnect.usamap.MapActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CalendarExampleActivity extends Activity {

	private Button btn_addEvent;
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.calendar_layout);
		
		init();
		initGUI();
		initEvent();
	}

	private void init(){
		btn_addEvent = (Button)findViewById(R.id.btn_addevent);
	}
	
	private void initGUI(){
		
	}
	
	private void initEvent(){
		btn_addEvent.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CalendarManager.pushAppointmentsToCalender(CalendarExampleActivity.this, true, true);
			}
		});
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
}

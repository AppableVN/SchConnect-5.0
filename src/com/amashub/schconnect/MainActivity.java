package com.amashub.schconnect;

import com.amashub.schconnect.animation.ExpandAnimation;
import com.amashub.schconnect.animation.Expand_Animation;
import com.amashub.schconnect.calendar.CalendarExampleActivity;
import com.amashub.schconnect.calendar.CalendarManager;
import com.amashub.schconnect.usamap.MapActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	private Button btn_map;
	private Button btn_calendar;
	private Button btn_expand;
	private RelativeLayout rel_expand;
	private boolean isExpand = true;
	
	ExpandAnimation expandAni;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		init();
		initGUI();
		initEvent();
	}

	private void init(){
		btn_map = (Button)findViewById(R.id.btn_map);
		btn_calendar = (Button)findViewById(R.id.btn_calendarevent);
		btn_expand = (Button)findViewById(R.id.btn_expand);
		rel_expand = (RelativeLayout)findViewById(R.id.rel_expand_view);
	}
	
	private void initGUI(){
		expandAni = new ExpandAnimation(rel_expand, 500);
	}
	
	private void initEvent(){
		btn_map.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, MapActivity.class);
				startActivity(intent);
			}
		});
		
		btn_calendar.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, CalendarExampleActivity.class);
				startActivity(intent);
			}
		});
		
		btn_expand.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				rel_expand.clearAnimation();
				if(isExpand){
					isExpand = false;
				}else{
					isExpand = true;
					rel_expand.startAnimation(expandAni);
				}
			}
		});
	}
	
}

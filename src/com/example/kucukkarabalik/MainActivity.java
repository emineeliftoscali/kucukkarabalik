package com.example.kucukkarabalik;

import com.example.kucukkarabalik.ShakeDetector;
import com.example.kucukkarabalik.ShakeDetector.OnShakeListener;

import android.R.integer;
import android.R.string;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
//import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected story mystory = new story();
	private TextView storyview;
	private Button getChangePage;
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	//private ImageView mImageView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//declaring view variables
		
		storyview = (TextView) findViewById(R.id.textView1);
	    getChangePage = (Button) findViewById(R.id.button1);
	   // mImageView = (ImageView)findViewById(R.id.imageView1);
	    
	    mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector(new OnShakeListener()
				{

					@Override
					public void onShake() {
						
						String currentpage = mystory.getAPage();
						storyview.setText(currentpage);
						animateStory();
						
					}
			
				});
		
		//mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
		
		getChangePage.setOnClickListener(new View.OnClickListener() {

			
			@Override
			public void onClick(View v) {
				
				// TODO Auto-generated method stub
				String currentpage = mystory.getAPage();
				
				storyview.setText(currentpage);
				//animateStory();
				
			}
			
			
			
		});
        
	}
	
		
	@Override
	public void onResume() {
		
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
		
	}
	@Override
	public void onPause() {
		
		super.onPause();
		mSensorManager.unregisterListener(mShakeDetector);
	}
	
	private void animateStory(){
		AlphaAnimation fadeInAnimation = new AlphaAnimation(0, 1);
		fadeInAnimation.setDuration(1500);
		fadeInAnimation.setFillAfter(true);
	    storyview.setAnimation(fadeInAnimation);
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}

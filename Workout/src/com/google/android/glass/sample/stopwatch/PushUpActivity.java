package com.google.android.glass.sample.stopwatch;

import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.widget.FrameLayout;

import com.google.android.glass.app.Card;

public class PushUpActivity extends Activity {

	private FrameLayout frameLayout;
	private String pushUpCountString;
	private int pushUpCountNumber = 1;
	private TextToSpeech tts;
	private int tempPushCount;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity);
		frameLayout = (FrameLayout) findViewById(R.id.main_activity_frame);
				
		Intent intent = getIntent();
		pushUpCountString = intent.getStringExtra(Constants.PUSH_UP_COUNT);
		pushUpCountNumber = Integer.valueOf(pushUpCountString);
		
		Card card = new Card(getApplicationContext());
		card.setText(pushUpCountString+" To Go");
		tts = new TextToSpeech(getApplicationContext(), null);
		tts.setLanguage(Locale.US);
		tts.speak(pushUpCountString+" To Go", TextToSpeech.QUEUE_ADD, null);

		card.setImageLayout(Card.ImageLayout.FULL);   
        frameLayout.addView(card.toView());
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				
				tempPushCount = pushUpCountNumber;
				
				for(int i = 1; i<= pushUpCountNumber; i++)
				{
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					tempPushCount--;
					
					runOnUiThread(new Runnable() {
						
						@Override
						public void run() {
							Card card = new Card(getApplicationContext());
							card.setText(tempPushCount+" Remaining");
							if(tempPushCount == pushUpCountNumber/2)
							{
								tts.speak("You are halfway through!", TextToSpeech.QUEUE_ADD, null);
							}
							
							frameLayout.removeAllViews();
							frameLayout.addView(card.toView());
						}
					});
				}
				
			}
		}).start();
        
	}
	
}

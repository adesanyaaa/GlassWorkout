/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.glass.sample.stopwatch;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.google.android.glass.app.Card;

/**
 * Activity showing the options menu.
 */
public class MenuActivity extends Activity {
	
	private FrameLayout frameLayout;
	//private static final int SPEECH_REQUEST = 0;
	private String pushUpCount;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        
        frameLayout = (FrameLayout) findViewById(R.id.main_activity_frame);
        
        Random random = new Random();
        pushUpCount = Integer.toString(random.nextInt()*100);
        pushUpCount = pushUpCount.substring(0, 1);
        pushUpCount = "15";
        
        Card card2 = new Card(getApplicationContext());
        card2.setText("Tap to 'Start' when you are ready!");
        card2.setFootnote(pushUpCount+" Push-Ups is your challenge");
        card2.setImageLayout(Card.ImageLayout.FULL);
        View card2View = card2.toView();   
        frameLayout.addView(card2View);
        
    }

    @Override
    public void onResume() {
        super.onResume();
    }
    
//    private void displaySpeechRecognizer() {
//        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        startActivityForResult(intent, SPEECH_REQUEST);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode,
//            Intent data) {
//        if (requestCode == SPEECH_REQUEST && resultCode == RESULT_OK) {
//            List<String> results = data.getStringArrayListExtra(
//                    RecognizerIntent.EXTRA_RESULTS);
//            String spokenText = results.get(0);
//            if(spokenText.equals("start"))
//            {
//            	Intent intent = new Intent(MenuActivity.this, PushUpActivity.class);
//            	intent.putExtra(Constants.PUSH_UP_COUNT, pushUpCount);
//            }
//        }
//        super.onActivityResult(requestCode, resultCode, data);
//    }
    
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.stopwatch, menu);
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	if (keyCode == KeyEvent.KEYCODE_DPAD_CENTER) {
    		Intent intent = new Intent(MenuActivity.this, PushUpActivity.class);
        	intent.putExtra(Constants.PUSH_UP_COUNT, pushUpCount);
        	startActivity(intent);
            return true;
        }
    	
    	return false;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection.
        switch (item.getItemId()) {
            case R.id.stop:
                Intent intent = new Intent(MenuActivity.this, MenuActivity.class);
                startActivity(intent);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onOptionsMenuClosed(Menu menu) {
        // Nothing else to do, closing the Activity.
        finish();
    }
}

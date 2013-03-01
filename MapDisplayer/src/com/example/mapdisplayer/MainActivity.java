package com.example.mapdisplayer;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.Window;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	public static int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClickSpeak(View view) {
    	Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
    	speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.ACTION_WEB_SEARCH);
    	startActivityForResult(speechRecognizerIntent, REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
    		ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
    		((EditText) findViewById(R.id.editText1)).setText(results.size() != 0 ? results.get(0) : "");
    	}
    	super.onActivityResult(requestCode, resultCode, data);
    }
}
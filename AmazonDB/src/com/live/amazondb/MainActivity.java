package com.live.amazondb;

import java.util.List;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpledb.AmazonSimpleDBClient;
import com.amazonaws.services.simpledb.model.Item;
import com.amazonaws.services.simpledb.model.SelectRequest;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		
		return true;
	}

	public void onClickHandler(View view) {
		EditText editText1 = (EditText)findViewById(R.id.editText1);
		final StringBuilder compiledResult = new StringBuilder();
		final String text = editText1.getText().toString();
		Thread t = new Thread() {
			@Override
			public void run() {
				SelectRequest queryRequest = new SelectRequest(text).withConsistentRead(true);
				List<Item> results = getInstance().select(queryRequest).getItems();
				for (Item item:results) {
					compiledResult.append(item.getName()).append("\n");
				}
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException ex) {
			
		}
		((android.widget.TextView) findViewById(R.id.textView2)).setText(compiledResult);
	}

	public static AmazonSimpleDBClient getInstance() {
        //return AWSAndroidDemo.clientManager.sdb();
		return new AmazonSimpleDBClient(new BasicAWSCredentials( PropertyLoader.getInstance().getAccessKey(), PropertyLoader.getInstance().getSecretKey()));
	}
}

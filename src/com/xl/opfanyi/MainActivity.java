package com.xl.opfanyi;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class MainActivity extends Activity
{
    /** Called when the activity is first created. */
  
	EditText edit_input;
	TextView text_output;
	Button btn_fanyi;
	
	@Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		btn_fanyi = (Button) findViewById(R.id.btn_fanyi);
		text_output = (TextView) findViewById(R.id.text_output);
		edit_input = (EditText) findViewById(R.id.edit_input);
		
		btn_fanyi.setOnClickListener(new View.OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					// TODO: Implement this method
					FanyiThread thread = new FanyiThread(new FanyiThread.OnFanyiListener(){

							@Override
							public void onOutString(String text)
							{
								// TODO: Implement this method
								text_output.setText(text);
								Toast.makeText(MainActivity.this,"",0).show();
							}
							
						
					});
					thread.setText(edit_input.getText().toString());
					thread.start();
				}

			
		});
		
    }
	
	
	
	
	
	
	
	
	
	
}

package com.example.myfirstapp;




import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends Activity {
	public final static String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
	
	private Button btn;
	private EditText edit;
	private Spinner spinner;
	//private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        btn = (Button) findViewById(R.id.button);
        edit = (EditText) findViewById(R.id.edit_message);
        spinner = (Spinner) findViewById(R.id.spinner);
        //textView = (TextView) findViewById(R.id.textView1);
        
        btn.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)
                    {
                        //Log.v("EditText", edit.getText().toString());
                       // Log.v("Spinner", spinner.getSelectedItem().toString());
                    	//if(spinner.getSelectedItem().toString()=="All Types")
                    	 String[] customarr = {"alltype","feature","tv_series","game"};
                    	 String selecteditem = customarr[spinner.getSelectedItemPosition()];
                    	 String tit=edit.getText().toString();
                    	 tit=tit.replaceAll(" ","%20");
                    	 	
                    	String url="http://cs-server.usc.edu:39527/examples/servlet/Hworld?title="+tit+"&select="+selecteditem;
                    	
                    	if(tit.contentEquals("Search the Movie Title"))
        	        	{
                    		
                    		
                    			 Context context = getApplicationContext();
            	        		 Toast.makeText(context,"Please Enter the Movie Title", Toast.LENGTH_SHORT).show();
								//Thread.sleep(4000);
							
        	        		 
        	        	}
                        
                    	Log.v("url",url);
                      //  http://cs-server.usc.edu:39527/examples/servlet/Hworld?title="+title+"&select="+type
                        
                        Intent intent=new Intent(MainActivity.this,FetchData.class);
                        String message=url;
                        intent.putExtra(EXTRA_MESSAGE, message);
                        startActivity(intent);
                    }
                });
    
 
    
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.title_type,android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    
    //String url="http://cs-server.usc.edu:23405/examples/servlet/HelloWorldExample?title="+edit.getText().toString()+"&type="+spinner.getSelectedItem().toString();
   // Log.v("url",url);
    
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            //Object item = parent.getItemAtPosition(pos);
            
        }
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });
    
    
    }
    
   
    } 
    
    


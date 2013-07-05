package com.example.myfirstapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class FetchData extends Activity {
	private TextView textView;
	private JSONObject jObject;
	String response;
	CharSequence mapforlistview;
	
	 public final static String EXTRA_TITLE = "com.example.myfirstapp.TITLE";
	    public final static String EXTRA_COVER = "com.example.myfirstapp.COVER";    
	    public final static String EXTRA_YEAR = "com.example.myfirstapp.YEAR";
	    public final static String EXTRA_DETAILS = "com.example.myfirstapp.DETAILS";
	    public final static String EXTRA_DIRECTOR = "com.example.myfirstapp.DIRECTOR";
	    public final static String EXTRA_RATING = "com.example.myfirstapp.RATING";
	    public final static String EXTRA_REVIEWS = "com.example.myfirstapp.REVIEWS";
	 ListView list;
	/* String[] cover;
	 String[] title;
	 String[] rating;
	 String[] year;
	 String[] director;
	 String[] details;*/
	 //ImageView imgView;

	 @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fetch_data);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		textView = (TextView) findViewById(R.id.TextView1);
		
		
		Intent intent = getIntent();
		String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
		readWebpage(message);
		}
		
		private class DownloadWebPageTask extends AsyncTask<String, Void, String> {
	        @Override
	        protected String doInBackground(String... urls) {
	          String response = "";
	          for (String url : urls) {
	            DefaultHttpClient client = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet(url);
	            try {
	              HttpResponse execute = client.execute(httpGet);
	              InputStream content = execute.getEntity().getContent();

	              BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
	              String s = "";
	              while ((s = buffer.readLine()) != null) {
	                response += s;
	              }

	            } catch (Exception e) {
	              e.printStackTrace();
	            }
	          }
	          
	          
	          return response;
	        }
	        
	        @Override
	        protected void onPostExecute(String result) {
	        	
	        	///////////////////////////////////////////
	        	Log.v("results",result);
	        	String str1="";
	        	final String title[]=new String[5];
	        	final String details[]=new String[5];
	        	final String director[]=new String[5];
	        	final String year[]=new String[5];
	        	final String rating[]=new String[5];
	        	final String cover[]=new String[5];


	        	result=result.substring(24, result.length());
	        	String str=result;
	        	String arpit=str;
	        	
	        	String f1="";

	        	String[] splits = str.split(",");

	        	System.out.println("splits.size: " + splits.length);
	        	int counter=0;
	        	int counter1=0;
	        	int counter2=0;
	        	int counter3=0;
	        	int counter4=0;
	        	int counter5=0;

	        	for(String asset: splits){

	        	if(asset.contains("title"))
	        	{
	        	if(!asset.contains("deatils"))
	        	{
	        	//System.out.println(asset.substring(9,asset.length()-1 ));
	        	title[counter]=asset.substring(9,asset.length()-1 );
	        	counter++;
	        	}

	        	}
	        	if(asset.contains("deatils"))
	        	{
	        	// System.out.println("arpit1");
	        	// System.out.println(asset.substring(11,47));
	        	// System.out.println("arpit1");
	        	details[counter1]=asset.substring(11,47);
	        	counter1++;
	        	//counter++;
	        	}
	        	if(asset.contains("director"))
	        	{
	        	//System.out.println("arpit1");
	        	// System.out.println(asset.substring(12,asset.length()-1));
	        	// System.out.println("arpit1");
	        	director[counter2]=asset.substring(12,asset.length()-1);
	        	counter2++;
	        	//counter1++;
	        	//counter++;
	        	}

	        	if(asset.contains("year"))
	        	{
	        	//System.out.println("arpit2");
	        	// System.out.println(asset.substring(8,asset.length()-1));
	        	year[counter3]=asset.substring(8,asset.length()-1);
	        	counter3++;
	        	// System.out.println("arpit1");
	        	//details[counter1]=asset.substring(11,47);
	        	//counter1++;
	        	//counter++;
	        	}
	        	if(asset.contains("rating"))
	        	{
	        	//System.out.println("arpit3");
	        	//System.out.println(asset.substring(10,asset.length()-1));
	        	rating[counter4]=asset.substring(10,asset.length()-1);
	        	counter4++;
	        	// System.out.println("arpit1");
	        	//details[counter1]=asset.substring(11,47);
	        	//counter1++;
	        	//counter++;
	        	}


	        	//"cover":"http://ia.media-imdb.com/images/M/MV5BMjMyOTM4MDMxNV5BMl5BanBnXkFtZTcwNjIyNzExOA@@._V1._SX54_CR0,0,54,74_.jpg",
	        	//"title":
	        	//"The Amazing Spider-Man ","year":"2012","director":" Marc Webb","rating":" 7.3"

	        	}
	        	System.out.println("Title Array");
	        	for(int i=0;i<title.length;i++)
	        	{
	        	//System.out.println(title[i]);
	        	f1+=title[i];
	        	}

	        	System.out.println("Details Array");
	        	for(int i=0;i<details.length;i++)
	        	{
	        	//System.out.println(details[i]);
	        	f1+=details[i];
	        	}
	        	System.out.println("Director Array");
	        	for(int i=0;i<director.length;i++)
	        	{
	        	//System.out.println(director[i]);
	        	f1+=director[i];
	        	}
	        	System.out.println("Year Array");
	        	for(int i=0;i<year.length;i++)
	        	{
	        	//System.out.println(year[i]);
	        	f1+=year[i];
	        	}
	        	System.out.println("Rating Array");
	        	for(int i=0;i<rating.length;i++)
	        	{
	        	//System.out.println(rating[i]);
	        	f1+=rating[i];
	        	}

	        	String[] splits1 = str.split("cover");

	        	//System.out.println("splits.size: " + splits1.length);

	        	for(String asset: splits1){
	        	//System.out.println("arpit1");
	        	if(asset.contains("http"))
	        	{
	        	cover[counter5]= asset.substring(3, asset.indexOf("\","));
	        	counter5++;

	        	}
	        	//System.out.println(asset.substring(3, asset.indexOf("\",")) );
	        	//System.out.println("arpit1");
	        	}
	        	System.out.println("Cover Array");
	        	for(int i=0;i<cover.length;i++)
	        	{
	        	//System.out.println(cover[i]);
	        		f1+=cover[i];
	        	}
	        	//textView.setText(f1);
	        	
	        	/////////////////Table data code
	        	TableLayout tl = (TableLayout) findViewById(R.id.maintable);
	        	
	        	for(int i=0 ;i<cover.length;i++)
	        	{
	        	TableRow tr =new TableRow(FetchData.this);


	        			TextView titleV =new TextView(FetchData.this);
	        			if(title[i]!=null)
	        			titleV.setText(title[i]+'('+year[i]+')');

                         
	        			TextView ratingV =new TextView(FetchData.this);
	        			
	        			
	        			if(rating[i]!=null)
	        			ratingV.setText("Rating: " + rating[i]);

	        			//new loadImageTask().execute(cover[i].toString());
	        			ImageView imgView = new ImageView(FetchData.this);
	        			
	        			// grab image to display
				        try {
				        	imgView.setImageDrawable(grabImageFromUrl(cover[i]));
				        } catch(Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
				        }
	        			
	        			
                        imgView.setMaxHeight(5);

	        		    imgView.setMaxWidth(5);
	        		    
	        		    tr.addView(imgView);
	        			
	        			//////////////////////////////
	        			/*ImageView imgView = new ImageView(FetchData.this);
	    		        
	    		        // grab image to display
	    		        try {
	    		        	imgView.setImageDrawable(grabImageFromUrl(cover[i]));
	    		        } catch(Exception e) {
	    					// TODO Auto-generated catch block
	    					e.printStackTrace();
	    		        }*/
	    		        //tr.addView(imgView);
	        			
	        			//////////////////////////////
	        		    
	        		    
	        			tr.addView(titleV);

	        			tr.addView(ratingV);

	        			tr.setId(i);

	        			tr.setClickable(true);
	        			tr.setFocusable(true);
	        			//PopupWindow popUp = new PopupWindow(FetchData.this);
	        			final int ok=i;
	        			
	        			tr.setOnClickListener(new OnClickListener() {
	    					
	    					@Override
	    					public void onClick(View v) {
	    						int id = v.getId();
	    		                //set up dialog
	    						Context context = getApplicationContext();
                              //  Toast.makeText(context,"HI", Toast.LENGTH_SHORT).show();
	    		                Dialog dialog = new Dialog(FetchData.this);
	    		                dialog.setContentView(R.layout.custom);
	    		                dialog.setTitle("Details");
	    		                dialog.setCancelable(true);
	    		                
	    		                
	    		                ////////////////
	    		                
	    		                TextView coverTextView = (TextView) dialog.findViewById(R.id.textView2);
	    		                TextView yearTextView = (TextView) dialog.findViewById(R.id.textView3);
	    		                TextView directorTextView = (TextView) dialog.findViewById(R.id.textView4);
	    		                TextView ratingTextView = (TextView) dialog.findViewById(R.id.textView5);
	    		                try {
	    		                	coverTextView.setText("Name: " + title[ok]+"("+year[ok]+")");
	    		                	yearTextView.setText("Year: " + year[ok]);
	    		                	directorTextView.setText("Director: " + director[ok]);
	    		                	ratingTextView.setText("Rating: " + rating[ok]+"/10");
	    		                	
	    		                   /* titleText = result.getJSONObject(id).getString("title").toString();
	    		                    coverText = result.getJSONObject(id).getString("cover").toString();   
	    		                    yearText = result.getJSONObject(id).getString("year").toString();
	    		                    detailsText = result.getJSONObject(id).getString("details").toString();
	    		                    directorText = result.getJSONObject(id).getString("director").toString();
	    		                    ratingText = result.getJSONObject(id).getString("rating").toString();*/
	    						} catch (Exception e1) {
	    							// TODO Auto-generated catch block
	    							e1.printStackTrace();
	    						}
	    		                
	    		              //set up image view
	    		                ImageView imgView = (ImageView) dialog.findViewById(R.id.imageView1);
	    				        
	    				        // grab image to display
	    				        try {
	    				        	imgView.setImageDrawable(grabImageFromUrl(cover[ok]));
	    				        } catch(Exception e) {
	    							// TODO Auto-generated catch block
	    							e.printStackTrace();
	    				        }
	    		                
	    		                Button button = (Button) dialog.findViewById(R.id.button1);
	    		                button.setOnClickListener(new OnClickListener() {
	    			                @Override
	    			                    public void onClick(View v) {
	    			                	Intent intent = new Intent(FetchData.this, FacebookPost.class);
	    				          		intent.putExtra(EXTRA_TITLE, title[ok]);
	    				          		intent.putExtra(EXTRA_COVER, cover[ok]);
	    				          		intent.putExtra(EXTRA_YEAR, year[ok]);
	    				          		intent.putExtra(EXTRA_DIRECTOR, director[ok]);
	    				          		intent.putExtra(EXTRA_DETAILS, details[ok]);
	    				          		intent.putExtra(EXTRA_RATING, rating[ok]);
	    				          		startActivity(intent);
	    			                    }
	    			                });
	    		                
	    		                dialog.show();
	    		                //////////////////
	    		                
	    					}});
	        			
	        		
	        			tl.addView(tr,	new TableLayout.LayoutParams(TableLayout.LayoutParams.WRAP_CONTENT, TableLayout.LayoutParams.WRAP_CONTENT));

	        			}
	        	
	        }
		}
	        
	       /* private Drawable grabImageFromUrl(String url) throws Exception {
	        	return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
	        }*/
	        
	        private Drawable grabImageFromUrl(String url) throws Exception {
            	return Drawable.createFromStream((InputStream)new URL(url).getContent(), "src");
            }
	        
	        public void readWebpage(String message) {
		    	  
		        DownloadWebPageTask task = new DownloadWebPageTask();
		        task.execute(new String[] {message});
		
		}
		}

               /////////////////////////////////////load image function
	      /*  public class loadImageTask extends AsyncTask<String, Void, Void>

	        {

	        Drawable imgLoad;

	        @Override
            protected void onPreExecute() {

	        // TODO Auto-generated method stub

	        super.onPreExecute();

	        //progressbar.setVisibility(View.VISIBLE);

	        }

	        @Override

	        protected Void doInBackground(String... params) {

	        // TODO Auto-generated method stub

	        imgLoad = LoadImageFromWeb(params[0]);

	        return null;

	        }

	        @Override

	        protected void onPostExecute(Void result) {

	        // TODO Auto-generated method stub

	        super.onPostExecute(result);

	        //if(progressbar.isShown())

	        //{

	        //progressbar.setVisibility(View.GONE);

	        imgView.setVisibility(View.VISIBLE);

	        imgView.setBackgroundDrawable(imgLoad);

	        //}

	        }

	        }

	        public Drawable LoadImageFromWeb(String url)

	        {
	        	 Drawable d=null;

	        try

	        {

	        InputStream is = (InputStream)

	        new URL(url).getContent();

	        d = Drawable.createFromStream(is, "src name");

	       

	        }
	        	
	        catch(Exception e)
	        {
	        	
	        }
	        return d;	
	        	
	        }
	        	
	        	///////////////////////////////////////////load image
	        	
	        	//List(result);
	        }
	      

	      public void readWebpage(String message) {
	    	  
	        DownloadWebPageTask task = new DownloadWebPageTask();
	        task.execute(new String[] {message});
	
	}}
	      
     /* public void List(String result)
       {
    	   JSONObject responseObj=null;
    	   try{
    	    	responseObj= new JSONObject(result);
    	    	JSONObject Obj = responseObj.getJSONObject("results");
    	    	JSONArray listMovieObj = Obj.getJSONArray("result");
    	    	
				for(int i=0 ;i<listMovieObj.length();i++)
    	    	{
    	    		
    	    		JSONObject e = listMovieObj.getJSONObject(i);
    	    		cover[i]=e.getString("cover");
                    title[i]= e.getString("title");
    	    		year[i]= e.getString("year");
    	    		director[i]= e.getString("director");
    	    		rating[i]= e.getString("rating");
    	    		details[i]= e.getString("details");
    	    	}
    	    	ArrayList<HashMap<String, Object>> moviesList =new ArrayList<HashMap<String, Object>>();
    	    	for(int i=0;i < listMovieObj.length();i++)
    	    	{
    	    		HashMap<String, Object> map = new HashMap<String, Object>();
    	    		map.put("Image", cover[i]);
    	    		map.put("Title",title[i]);
    	    	    map.put("Title", title[i]);
    	            Context context = getApplicationContext();
    	            Toast.makeText(context, title[i], Toast.LENGTH_SHORT).show();
    	            map.put("Rating", "Rating:" + rating[i]);
    	    		
    	    	}
    	    	
    	    }
    	   catch(JSONException e){ Log.v("error","jj");}
       }}
      

 /* public class view {

	public int icon;
	public String title;
	public int rating;
	
	public view(){
		super();
		
	   }
	
	    public view(int icon,String title,int rating)
	    {
		  super();
		  this.icon=icon;
		  this.title=title;
		  this.rating=rating;
	    }
	
	
      }

public class ListAdapter extends ArrayAdapter<view>{
	
	Context context; 
    int layoutResourceId;    
    Movie data[] = null;
    
    public ListAdapter(Context context, int layoutResourceId, Movie[] data){
    	
    	super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
    	View row = convertView;
        MovieHolder holder = null;
        
        if(row==null)
        {
        	LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        	row = inflater.inflate(layoutResourceId, parent, false);
        	
        	holder = new MovieHolder();
        	holder.imageView01 = (ImageView)row.findViewById(R.id.imageView01);
            holder.TextView1 = (TextView)row.findViewById(R.id.TextView1);
            
            row.setTag(holder);
        }
        else
        {
        	holder= (MovieHolder)row.getTag();
        }
        View view = data[position];
        holder.TextView1.setText(view.title);
        holder.imageView01.setImageResource(view.icon);
        holder.TextView2.setImageResource(view.rating);
       
        return row;
    
    
}
    public class MovieHolder
    {
        ImageView imageView01;
        TextView TextView1,TextView2;
    }


*/






	      
	      
	           
	             
	           
	           
	           
	           
	       



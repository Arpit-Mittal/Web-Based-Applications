package com.example.myfirstapp;

import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.facebook.FacebookActivity;
import com.facebook.FacebookException;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.widget.WebDialog;
import com.facebook.widget.WebDialog.OnCompleteListener;


public class FacebookPost extends FacebookActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 this.openSession();
		//setContentView(R.layout.activity_facebook_post);
		//this.openSession();
	}

	@Override
	  protected void onSessionStateChange(SessionState state, Exception exception) {
	    // user has either logged in or not ...
	    if (state.isOpened()) {
		    Intent intent = getIntent();
		    String titleText = intent.getStringExtra(FetchData.EXTRA_TITLE);
		    String coverText = intent.getStringExtra(FetchData.EXTRA_COVER);
		    String yearText = intent.getStringExtra(FetchData.EXTRA_YEAR);
		    String ratingText = intent.getStringExtra(FetchData.EXTRA_RATING);
		    String detailsText = intent.getStringExtra(FetchData.EXTRA_DETAILS);
		    
	        Bundle postParams = new Bundle();

	        JSONObject prop1 = new JSONObject();
	        JSONObject properties = new JSONObject();
	        try {
	            prop1.put("text", "Here");
				prop1.put("href", detailsText+"reviews");
			    properties.put("Look at user reviews ", prop1);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        postParams.putString("name", titleText);
	        postParams.putString("caption", "I am interested in this movie/game/series");
	        postParams.putString("description",  titleText + " released in " + yearText + " has a rating of " + ratingText);
	        postParams.putString("properties", properties.toString());
	        postParams.putString("link", detailsText);
	        postParams.putString("picture", coverText);

	 	    WebDialog feedDialog = (
	 	        new WebDialog.FeedDialogBuilder(FacebookPost.this,
	 	            Session.getActiveSession(),
	 	           postParams))
	 	        .setOnCompleteListener(new OnCompleteListener() {
	 	            @Override
	 	            public void onComplete(Bundle values,
	 	                FacebookException error) {
	 	                // When the story is posted, echo the success
	 	                // and the post Id.
	 	                final String postId = values.getString("post_id");
	 	                if (postId != null) {
	 	                    Toast.makeText(FacebookPost.this, "Post has been published", Toast.LENGTH_SHORT).show();
	 	                }
	 	                else
	 	                {
	 	                	Toast.makeText(FacebookPost.this, "Post not published", Toast.LENGTH_SHORT).show();
	 	                	finish();
	 	                }
	 	            }
	 	        })
	 	        .build();
	 	    feedDialog.show();
	    }
	  }
	

}

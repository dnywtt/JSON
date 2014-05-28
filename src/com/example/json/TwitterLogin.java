package com.example.json;

//http://stackoverflow.com/questions/19767615/how-to-display-image-byte-array-from-json-into-imageview-factory-returns-nu

//http://www.androidbegin.com/tutorial/android-json-parse-images-and-texts-tutorial/
//http://javatechig.com/android/json-feed-reader-in-android
//https://github.com/javatechig/Advance-Android-Tutorials/tree/master/Feed%20Reader/src/com/javatechig/feedreader

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;





import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import twitter4j.json.DataObjectFactory;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class TwitterLogin extends Activity {

	String CONSUMER_KEY = "e7J6Clf4zg52GA71waGr38Azq";
	String CONSUMER_SECRET = "GcFPSQX8ZskX7iv9Z8lhrsdbGeJ8l6svZ8FVNOAPgVD3yOXRFW";
	String accessTokenStr = "2411322655-SCXBE8kd6825LrClxHHdb3ltXWA5n8uPNcgKsrC";
	String accessTokenSecretStr = "WjQeh0isMHjlmfuIhDpMxIjUA6qnmFP1whjFhwXnq7xah";
	String TwitterStreamURL = "https://api.twitter.com/1.1/statuses/home_timeline.json";

	List<twitter4j.Status> statuses;
	String tweets;
	String strInitialDataSet;
	static String text = "text";
	String lang;
	String name;
	static String url = "url";
	ListView listview;
	List<Product> products;
	Product test;
	
	
	ArrayList<HashMap<String, String>> contactList;
	TextView textview;
	List<String> your_array_list;
	String text1 = "text1";
	String lang1 = "lang1";
	String username1 = "username1";
	String url1 = "url1";
	String image1 = "image1";
	TextView name1;
	
	private ProgressDialog pDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Get any saved data
		super.onCreate(savedInstanceState);

		// Point to the name for the layout xml file used
		setContentView(R.layout.activity_main);
		contactList = new ArrayList<HashMap<String, String>>();

		// Get the data send from MainActivity

//		Bundle extras = getIntent().getExtras();
//		String consumerKeya = extras.getString("consumerKey");
//		String consumerSecreta = extras.getString("consumerSecret");
//		String accessTokena = extras.getString("accessToken");
//		String accessSecreta = extras.getString("accessSecret");
//		Toast.makeText(getApplicationContext(),
//				consumerKeya + consumerSecreta + accessTokena + accessSecreta,
//				Toast.LENGTH_LONG).show();

		new MyAsyncTask().execute();

	}

	// Use AsyncTask if you need to perform background tasks, but also need

	private class MyAsyncTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			// Showing progress dialog
			pDialog = new ProgressDialog(TwitterLogin.this);
			pDialog.setMessage("Please wait...");
			pDialog.setCancelable(false);
			pDialog.show();
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		protected Void doInBackground(Void... arg0) {

			try {
				ConfigurationBuilder builder = new ConfigurationBuilder();

				// GET THE CONSUMER KEY AND SECRET KEY FROM THE STRINGS XML
				builder.setOAuthConsumerKey(CONSUMER_KEY);
				builder.setOAuthConsumerSecret(CONSUMER_SECRET);
				builder.setOAuthAccessToken(accessTokenStr);
				builder.setOAuthAccessTokenSecret(accessTokenSecretStr);
				builder.setJSONStoreEnabled(true);
				builder.setIncludeEntitiesEnabled(true);
				builder.setIncludeMyRetweetEnabled(true);
				// builder.setIncludeRTsEnabled(true);

				AccessToken accessToken = new AccessToken(accessTokenStr,
						accessTokenSecretStr);
				Twitter twitter = new TwitterFactory(builder.build())
						.getInstance(accessToken);
				// Paging paging = new Paging(10); // MAX 200 IN ONE CALL. SET

				statuses = twitter.getUserTimeline();
				String strInitialDataSet = DataObjectFactory
						.getRawJSON(statuses);
				Log.e("TWEETS", strInitialDataSet);
				
			//	creating the arrylist to add values
				
				products = new ArrayList();

				try {
					JSONArray jr = new JSONArray(strInitialDataSet);
					for (int i = 0; i < jr.length(); i++) {
						JSONObject obj = jr.getJSONObject(i);
						text = obj.getString("text");
						lang = obj.getString("lang");

						JSONObject user = obj.getJSONObject("user");
						name = user.getString("name");
						url = user.getString("profile_background_image_url");

						// tmp hashmap for single contact
//						HashMap<String, String> contact = new HashMap<String, String>();
//					    contact.put(text1, text);
//						contact.put(lang1, lang);
//						contact.put(username1, name);
//						contact.put(url1, url);
//						contactList.add(contact);
						
						
						products.add(new Product(text,url));
	        			
					}				

				} catch (JSONException e) {
					e.printStackTrace();
				}
			} catch (TwitterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} finally {

			}
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			// Dismiss the progress dialog
			if (pDialog.isShowing())
				pDialog.dismiss();
			Log.e("test", text);

			    listview = (ListView) findViewById(R.id.list_products);
			    listview.setAdapter(new ProductListAdapterSimple(TwitterLogin.this, products));
		}

	}

}

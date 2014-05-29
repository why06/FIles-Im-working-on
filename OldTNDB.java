/*	OldTNDB
 * 	Description: Class to setup, update, and manage SQLite database.
 *		Also retrieves JSON.
 * 	Created by: Shaun Carter 05/28/2014
 *  Copyright (c) 2014 Designsensory. All rights reserved.
 * 
 */

package com.designsensory.old_tn_trail;
import android.content.Context;
import android.database.sqlite.*;
import android.os.AsyncTask;
import android.util.Log;

import org.json.*; //import JSON stuff

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;


public class OldTNDB extends SQLiteOpenHelper{

	//TABLE CREATION STATEMENTS
	private static POI poi = new POI();
	private static final String poi_create = poi.getCreateStatement();
	private static final String poi_name = poi.getTableName();
	private static FAQ faq  = new FAQ();
	private static final String faq_create = faq.getCreateStatment();
	private static final String faq_name = faq.getTableName();
	private static Timeline time = new Timeline();
	private static final String time_create = time.getCreateStatment();
	private static final String time_name = time.getTableName();
	private static DidYouKnow dyk = new DidYouKnow();
	private static final String dyk_create = dyk.getCreateStatment();
	private static final String dyk_name = dyk.getTableName();
	private static Tag tag = new Tag();
	private static final String tag_create = tag.getCreateStatment();
	private static final String tag_name = tag.getTableName();
	

	//DATABASE INFO
	private static final  String DATABASE_NAME = "TDTDItem.db";
	private static final int DATABASE_VERSION = 1;
	//DB creation statement
	private static final String DATABASE_CREATE = poi_create + "\n"
			+ faq_create + "\n" + time_create + "\n" + dyk_create + "\n" + tag_create ;
	
	
	public OldTNDB(Context context) {
		
		super(context, DATABASE_NAME, null, DATABASE_VERSION); //name after ios dbase
		// TODO Auto-generated constructor stub	
	}

	@Override
	public void onCreate(SQLiteDatabase db) { // called when Db is accessed but not yet created
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { //called if database version increased
		Log.w(OldTNDB.class.getName(), "Upgrading database from version" + oldVersion 
				+ "to" + newVersion + ", which will destroy all old datat");
		db.execSQL("DROP TABLE IF EXIST " + poi_name + ", " + faq_name + ", " + time_name + ", " 
				+ dyk_name + ", " + tag_name);
		this.onCreate(db);
	}
	
	private class DownloadJSON extends AsyncTask<String, Void, JSONObject>{

    	@Override
    	protected JSONObject doInBackground(String... urls) {
    		// TODO Auto-generated method stub
    		try{
    			JSONObject json = null;
    			json = readJsonFromUrl(urls[0]);
    			return json;
    		}catch(IOException e){
    			e.printStackTrace();
    		} catch (JSONException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    		return null;
    	}
    	
    	// onPostExecute displays the results of the AsyncTask.
        @Override
        protected void onPostExecute(JSONObject result) {
        	Log.d("In PostExecute", result.toString());
            //Load Database with goodies
        	//Todo
        	parseJSONToDatabase(result);
            Log.d("In PostExecute", "text set.");
       }

    	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
    		InputStream is = null;
    		try{
    			is = new URL(url).openStream();
    			Log.d("Deep in JSON", "opened stream");
    		}catch(Exception e){
    			Log.d("Deep in JSON", "failed to opened stream");
    		}
    		try{
    			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
    			StringBuilder sb = new StringBuilder();
    			int charval;
    			while ((charval = rd.read()) != -1)
    			{
    				sb.append((char) charval);
    			}
    			String jsonText = sb.toString();
    			JSONObject json = new JSONObject(jsonText);
    			return json;
    		} finally {
    			is.close(); //close stream
    		}
    	}
    }
	
	private void parseJSONToDatabase(JSONObject json){
		if(json != null)
		{
			JSONArray categories = null;
			try {
				categories = json.getJSONArray("categories");//get the category array
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
			if(categories != null)
			{
				for(int i = 0; i < categories.length(); i++){
					JSONObject obj = categories.optJSONObject(i);
					
					String title = null;
					String slug;
					String description;
					String parent_id;
					String root_id;
					String interest_id;
					String order;
					String id = null;
					String active;
					int aux_db_table;
					String total = null;
					String parentcategory;
						
					
					//Save each item in temp var
					try{
						title = obj.getString("title");
						slug = obj.getString("slug");
						description = obj.getString("description");
						parent_id = obj.getString("parent_id");
						root_id = obj.getString("root_id");
						interest_id = obj.getString("interest_id");
						order = obj.getString("order");
						id = obj.getString("id");
						active = obj.getString("active");
						aux_db_table = obj.getInt("aux_db_table");
						total = obj.getString("total");
						parentcategory = obj.getString("parentcategory");
							
					}catch(JSONException e){
						//idk
					}
					
					Log.d("INside Loop/ iter: " + i, "title: " + title + " | " + "id: " + id + " | " + "total: " + total);
					
				}
			}
		}
	}
	
	public void fetchData()
	{
		try {
			Log.d("JSON part", "made it thus far.");    
			new DownloadJSON().execute("http://tntrailsandbyways.com/action/datapull.php?trail=1");
			Log.d("JSON part", "made it past JSON.");
		} catch (Exception e) {
			//Handle exception
		}
		
	}

}

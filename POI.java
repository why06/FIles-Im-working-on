package com.designsensory.old_tn_trail;

public class POI {
	private static final String CREATE_STATEMENT = "create table POI ("
			+ "_id integer primary key,"
			+ "address text,"
			+ "city text,"
			+ "commentary_url text,"
			+ "email text,"
			+ "favorite integer,"
			+ "featured integer,"
			+ "latitude real,"
			+ "longitude real,"
			+ "phone text,"
			+ "short_description text,"
			+ "state text,"
			+ "title text"
			+ "toll_free text"
			+ "twitter text"
			+ "visited integer"
			+ "website text"
			+ "zip text);";
	
	private static final String TABLE_NAME = "POI";
			
	public String getCreateStatement()
	{
		return CREATE_STATEMENT;
	}
	
	public String getTableName()
	{
		return TABLE_NAME;
	}
}

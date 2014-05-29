package com.designsensory.old_tn_trail;

public class Timeline {
	private static final String CREATE_STATEMENT = "create table Timeline ("
			+ "_id integer primary key,"
			+ "text text,"
			+ "title text);";
	
	public static final String TABLE_NAME = "Timeline";
	
	public String getCreateStatment()
	{
		return CREATE_STATEMENT;
	}
	
	public String getTableName()
	{
		return TABLE_NAME;
	}
}
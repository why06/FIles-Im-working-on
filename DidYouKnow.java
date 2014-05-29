package com.designsensory.old_tn_trail;
//this is a comment

public class DidYouKnow {
	private static final String CREATE_STATEMENT = "create table DidYouKnow ("
			+ "_id integer primary key,"
			+ "text text,"
			+ "title text);";
	private static final String TABLE_NAME = "DidYouKnow";
	
	public String getCreateStatment()
	{
		return CREATE_STATEMENT;
	}
	
	public String getTableName()
	{
		return TABLE_NAME;
	}
}
package com.designsensory.old_tn_trail;

public class Tag {
	private static final String CREATE_STATEMENT = "create table Tag ("
			+ "_id integer primary key,"
			+ "image_url text,"
			+ "order integer"
			+ "selected integer"
			+ "title text);";
	private static final String TABLE_NAME = "Tag";
	
	public String getCreateStatment()
	{
		return CREATE_STATEMENT;
	}
	
	public String getTableName()
	{
		return TABLE_NAME;
	}
}
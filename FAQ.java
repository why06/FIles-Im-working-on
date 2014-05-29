package com.designsensory.old_tn_trail;

public class FAQ {
	private static final String CREATE_STATEMENT = "create table FAQ ("
			+ "_id integer primary key,"
			+ "answer text,"
			+ "order integer,"
			+ "question text);";
	
	private static final String TABLE_NAME = "FAQ";
	
	public String getCreateStatment()
	{
		return CREATE_STATEMENT;
	}
	
	public String getTableName()
	{
		return TABLE_NAME;
	}
}

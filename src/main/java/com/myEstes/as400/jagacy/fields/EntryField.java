package com.myEstes.as400.jagacy.fields;

public class EntryField {

	private int row;
	private int column;
	
	public EntryField(final int rowNum, final int columnNum) {
		this.row = rowNum;
		this.column = columnNum;
	}
	
	
	public final int getRow() {
		return row;
	}
	
	public final int getColumn() {
		return column;
	}
}

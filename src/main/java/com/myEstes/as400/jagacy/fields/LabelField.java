package com.myEstes.as400.jagacy.fields;

public class LabelField {

	private int row;
	private int column;
	private String text;
	
	public LabelField(final int rowNum, final int columnNum, final String labelText) {
		this.row = rowNum;
		this.column = columnNum;
		this.text = labelText;
	}
	
	
	public final int getRow() {
		return row;
	}
	
	public final int getColumn() {
		return column;
	}
	
	public final String getText() {
		return text;
	}	
}

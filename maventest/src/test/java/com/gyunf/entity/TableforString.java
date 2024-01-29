package com.gyunf.entity;

public class TableforString {
	private String[][] str;
	private int[] width;

	public TableforString(String[][] str, int[] width) {
		this.str = str;
		this.width = width;
	}

	public String[][] getStr() {
		return str;
	}

	public void setStr(String[][] str) {
		this.str = str;
	}

	public int[] getWidth() {
		return width;
	}

	public void setWidth(int[] width) {
		this.width = width;
	}

}

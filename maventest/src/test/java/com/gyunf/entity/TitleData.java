package com.gyunf.entity;

import java.util.ArrayList;
import java.util.List;

public class TitleData {
	private String title;
	private int[] titleDegree;

	/*
	 * object 可加类型有 String：文本 ，Table 表格， Griddata 表格， TableforString表格
	 * ，String[][]表格， GridTable表格
	 */
	private List<Object> objects = new ArrayList<Object>();

	public TitleData(String title, int[] titleDegree) {
		this.title = title;
		this.titleDegree = titleDegree;

	}

	public void addObject(Object obj) {
		objects.add(obj);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int[] getTitleDegree() {
		return titleDegree;
	}

	public String getTitleDegreetoString() {
		String str = "";
		for (int i = 0; i < titleDegree.length; i++) {
			if (i == 0) {
				str += titleDegree[i];
			} else {
				str += "." + titleDegree[i];
			}
		}
		return str;
	}

	public void setTitleDegree(int[] titleDegree) {
		this.titleDegree = titleDegree;
	}

	public List<Object> getObjects() {
		return objects;
	}

	public void setObjects(List<Object> objects) {
		this.objects = objects;
	}

	public boolean equalswith(int[] degree) {
		if (degree.length != titleDegree.length)
			return false;
		for (int i = 0; i < degree.length; i++)
			if (degree[i] != titleDegree[i])
				return false;
		return true;
	}

	public boolean maxWithTitleDegree(int[] degree) {
		for (int i = 0; i < titleDegree.length; i++) {
			if (degree.length < i + 1)
				return true;
			if (degree[i] < titleDegree[i])
				return true;
			if (degree[i] > titleDegree[i])
				return false;
		}
		return false;
	}
}

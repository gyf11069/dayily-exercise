package com.gyunf.entity;

import java.util.List;
import java.util.Map;

public class GridTable {
	private int Total;
	// 添加需要的数据
	private List<Map<String, Object>> RowsList;
	// 设置需要的关键字
	private String[] keys;
	// 关键字对应的中文名
	private String[] names;
	// 窗口百分比
	private float[] widths;
	// 是否添加序号,默认不添加序号
	private boolean serial = false;

	public GridTable() {
	}

	public GridTable(List<Map<String, Object>> rowsList) {
		this.RowsList = rowsList;
	}

	public GridTable(List<Map<String, Object>> rowsList, String[] keys,
                     String names[]) {
		this.RowsList = rowsList;
		this.keys = keys;
		this.names = names;
	}

	public GridTable(List<Map<String, Object>> rowsList, String[] keys,
                     String names[], float[] width) {
		this.RowsList = rowsList;
		this.keys = keys;
		this.names = names;
		this.widths = width;
	}

	public GridTable(List<Map<String, Object>> rowsList, float[] width) {
		this.RowsList = rowsList;
		this.widths = width;
	}

	public GridTable(List<Map<String, Object>> rowsList, boolean serial) {
		this.RowsList = rowsList;
		this.serial = serial;
	}

	public GridTable(List<Map<String, Object>> rowsList, String[] keys,
                     String names[], boolean serial) {
		this.RowsList = rowsList;
		this.keys = keys;
		this.names = names;
		this.serial = serial;

	}

	public GridTable(List<Map<String, Object>> rowsList, String[] keys,
                     String names[], float[] width, boolean serial) {
		this.RowsList = rowsList;
		this.keys = keys;
		this.names = names;
		this.widths = width;
		this.serial = serial;

	}

	public GridTable(List<Map<String, Object>> rowsList, float[] width,
                     boolean serial) {
		this.RowsList = rowsList;
		this.widths = width;
		this.serial = serial;

	}

	public int getTotal() {
		return Total;
	}

	public void setTotal(int total) {
		Total = RowsList.size();
	}

	public List<Map<String, Object>> getRowsList() {
		return RowsList;
	}

	public void setRowsList(List<Map<String, Object>> rowsList) {
		RowsList = rowsList;
	}

	public String[] getKeys() {
		return keys;
	}

	public void setKeys(String[] keys) {
		this.keys = keys;
	}

	public String[] getNames() {
		return names;
	}

	public void setNames(String[] names) {
		this.names = names;
	}

	public void setWidths(float[] widths) {
		this.widths = widths;
	}

	public boolean isSerial() {
		return serial;
	}

	public void setSerial(boolean serial) {
		this.serial = serial;
	}

	// 自动化百分比
	public float[] getWidths() {
		if (widths == null || widths.length == 0) {
			if (keys != null && keys.length != 0) {
				int n = keys.length;
				int len[] = new int[n];
				for (int s : len) {
					len[s] = 0;
				}
				for (Map<String, Object> map : RowsList) {
					for (int i = 0; i < n; i++) {
						int s;
						if (map.get(keys[i]) == null) {
							s = 0;
						} else {
							s = map.get(keys[i]).toString().length();
						}
						len[i] = len[i] > s ? len[i] : s;
					}
				}
				int sum = 0;
				for (int i = 0; i < n; i++) {
					sum += len[i];
				}
				this.widths = new float[n];
				for (int i = 0; i < n; i++) {
					this.widths[i] = (float) len[i] / sum * 500;
				}

			}

		}

		return widths;
	}

}

package com.gmail.gm.jcant;

public class DataUnificator {

	public static final int INT = 0;
	public static final int DOUBLE = 1;
	public static final int STRING = 2;
	public static final int BOOLEAN = 3;
	public static final int DATE = 4;
	public static final int OBJECT = 5;

	private String name;
	private String data;
	private int type;
	private DataUnificator[] uData;

	public DataUnificator() {
		super();
	}

	public DataUnificator(String name, DataUnificator[] uData, int type) {
		super();
		this.name = name;
		this.uData = uData;
		this.type = type;
	}

	public DataUnificator(String name, String data, int type) {
		super();
		this.name = name;
		this.data = data;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public DataUnificator[] getUData() {
		return uData;
	}

	public void setUData(DataUnificator[] uData) {
		this.uData = uData;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DataUnificator [name=" + name + ", data=" + data + ", type=" + type + "]";
	}

}

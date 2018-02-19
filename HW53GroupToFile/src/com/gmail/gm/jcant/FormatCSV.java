package com.gmail.gm.jcant;

public class FormatCSV {

	public static String getFormatData(DataUnificator[] data) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < data.length; i++) {
			sb.append(getSingleDU(data[i]));
		}
		return sb.toString();
	}

	private static String getSingleDU(DataUnificator du) {
		StringBuilder sb = new StringBuilder();

		if (du.getUData() == null) {
			sb.append(du.getType() + "," + du.getName() + "," + du.getData());
			return sb.toString();
		} else {
			sb.append(du.getType() + "," + du.getName() + "," + System.lineSeparator());
			for (DataUnificator item : du.getUData()) {
				sb.append(getSingleDU(item) + System.lineSeparator());
			}
			return sb.toString();
		}
	}

	public static DataUnificator[] getUData(String[] data) {
		DataUnificator[] result;
		for (String line : data) {
			String[] items = line.split(",");
			if (Integer.parseInt(items[0])==DataUnificator.OBJECT) {
				
			}
		}
	
	}
}

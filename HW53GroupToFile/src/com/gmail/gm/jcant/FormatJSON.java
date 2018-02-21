package com.gmail.gm.jcant;

import java.util.Arrays;

public class FormatJSON {

	public static String getFormatData(DataUnificator[] data) {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		for (int i = 0; i < data.length; i++) {
			sb.append(getSingleDU(data[i]));
			if (i != data.length - 1) {
				sb.append(",");
			}
		}
		sb.append("]");
		return sb.toString();
	}

	private static String getSingleDU(DataUnificator du) {
		StringBuilder sb = new StringBuilder();

		if (du.getUData() == null) {
			sb.append("{\"type\":" + du.getType() + ",\"name\":\"" + du.getName() + "\",\"data\":\"" + du.getData()
					+ "\", \"uData\":null}");
			return sb.toString();
		} else {
			sb.append("{\"type\":" + du.getType() + ",\"name\":\"" + du.getName() + "\",\"data\":null, \"uData\":[");
			for (int i = 0; i < du.getUData().length; i++) {
				sb.append(getSingleDU(du.getUData()[i]));
				if (i != du.getUData().length - 1) {
					sb.append(",");
				}
				sb.append(System.lineSeparator());
			}
			sb.append("]}");
			return sb.toString();
		}
	}

	public static DataUnificator[] getUData(String data) {
		String[] adata = data.split(System.lineSeparator());
		DataUnificator[] result = new DataUnificator[0];
		DataUnificator[] current = null;
		DataUnificator dItem = null;
		DataUnificator dItem2 = null;

		for (String line : adata) {

			if (line.length() == 0) {
				continue;
			}

			String[] items = line.split(",");
			int itemType = Integer.parseInt(items[0]);
			String itemName = items[1];

			if (itemType == DataUnificator.OBJECT) {

				if (dItem != null) {
					result = addToArray(result, dItem);
				}

				current = new DataUnificator[0];
				dItem = new DataUnificator(itemName, current, itemType);
			} else {
				String itemData = items[2];
				dItem2 = new DataUnificator(itemName, itemData, itemType);
				current = addToArray(current, dItem2);
				dItem.setUData(current);
			}
		}

		result = addToArray(result, dItem);
		return result;
	}

	private static DataUnificator[] addToArray(DataUnificator[] arr, DataUnificator du) {
		DataUnificator[] result = Arrays.copyOf(arr, arr.length + 1);
		result[arr.length] = du;
		return result;
	}

}

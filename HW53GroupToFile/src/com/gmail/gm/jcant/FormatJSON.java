package com.gmail.gm.jcant;

import java.util.Arrays;

public class FormatJSON {

	// convert DataUnificator[] to JSON
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
					+ "\",\"uData\":null}");
			return sb.toString();
		} else {
			sb.append("{\"type\":" + du.getType() + ",\"name\":\"" + du.getName() + "\",\"data\":null,\"uData\":[");
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

	// convert JSON to DataUnificator[]
	public static DataUnificator[] getUData(String string) {
		DataUnificator[] result = new DataUnificator[0];
		string = string.substring(1, string.length() - 2); // remove '[' and ']'
		String[] adata = findBrackets(string, '{', '}');
		for (String element : adata) {
			DataUnificator du = new DataUnificator();
			String[] arrElement = element.split("\\[");
			String[] items = arrElement[0].split(",");
			for (String item : items) {
				item = item.replaceAll("\"", "");
				String[] sub = item.split(":");
				if (sub[0].equals("type")) {
					du.setType(Integer.parseInt(sub[1]));
				}
				if (sub[0].equals("name")) {
					du.setName(sub[1]);
				}
				if (sub[0].equals("data")) {
					if (sub[1].equals("null")) {
						du.setData(null);
					} else {
						du.setData(sub[1]);
					}
				}
				if (sub[0].equals("uData")) {
					if (sub.length == 2) {
						du.setUData(null);
					} else {
						du.setUData(getUData("[" + arrElement[1]));
					}
				}
			}
			result = addToArray(result, du);
		}
		return result;
	}

	private static String[] findBrackets(String string, char... brackets) {
		String[] result = new String[0];
		boolean findOpen = false;
		int start = 0;
		int end = string.length() - 1;
		int level = 0;

		for (int i = 0; i < string.length(); i++) {
			if ((!findOpen) && (string.charAt(i) == brackets[0])) {
				findOpen = true;
				start = i;
			} else if ((findOpen) && (level == 0) && (string.charAt(i) == brackets[1])) {
				end = i;
				result = addToArray(result, string.substring(start + 1, end));
				findOpen = false;
			} else if ((findOpen) && (string.charAt(i) == brackets[0])) {
				level++;
			} else if ((findOpen) && (level > 0) && (string.charAt(i) == brackets[1])) {
				level--;
			}
		}

		return result;
	}

	private static DataUnificator[] addToArray(DataUnificator[] array, DataUnificator item) {
		DataUnificator[] result = Arrays.copyOf(array, array.length + 1);
		result[array.length] = item;
		return result;
	}

	private static String[] addToArray(String[] array, String item) {
		String[] result = Arrays.copyOf(array, array.length + 1);
		result[array.length] = item;
		return result;
	}

}

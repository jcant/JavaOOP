package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DAOController {

	public DAOController() {
		super();
	}

	public static void saveObject(ObjectToDU object, DUToFormat formater, File file) throws IOException {

		String formatedData = formater.getFormatData(object.objectUnify());

		try (PrintWriter pw = new PrintWriter(file)) {

			pw.print(formatedData);

		} catch (IOException e) {
			throw e;
		}
	}

	public static void loadObject(DUToObject object, FormatToDU formater, File file) throws IOException {

		StringBuilder sb = new StringBuilder();

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = br.readLine()) != null) {
				sb.append(line + System.lineSeparator());
			}
		} catch (IOException e) {
			throw e;
		}

		object.unifyToObject(formater.getUnifyData(sb.toString()));

	}

}

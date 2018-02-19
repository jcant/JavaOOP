package com.gmail.gm.jcant;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class DAOController {

	
	public DAOController() {
		super();
	}

	public static void saveObject(ObjectToDAO object, DAOToFormat formater, File file) throws IOException{
		
		String formatedData = formater.getFormatData(object.objectUnify());
		
		try(PrintWriter pw = new PrintWriter(file)){
			
			pw.print(formatedData);
			
		} catch(IOException e) {
			throw e;
		}
	}
	
	public void loadObject(ObjectToDAO object, DAOToFormat formater, File file) {
		
	}
	
}

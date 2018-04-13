package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class Client implements Runnable {
	private Socket soc;
	private Thread thread;
	private List<String> request = new ArrayList<>();

	public Client(Socket soc) {
		super();
		this.soc = soc;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try (InputStream is = soc.getInputStream();
				OutputStream os = soc.getOutputStream();
				PrintWriter pw = new PrintWriter(os)) {
			
			read(is);
			String answer = requestProcessing();

			pw.print(answer);
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String requestProcessing() {
		logRequest();
		String answer = "";
		String[] req = request.get(0).split(" ");
		String reqType = req[0];
		String action = req[1].substring(1, req[1].length());
		
		if (action.equals("main_page")) {
			answer = getPage(action);
		}

		return getHTTPHeader() + answer;
	}

	private void read(InputStream is) {
		Scanner sc = new Scanner(is);
		this.request.clear();
		while (true) {
			String sline = sc.nextLine();
			if (sline.equals("")) {
				break;
			}
			this.request.add(sline);
		}
	}

	private String getPage(String pageName) {
		StringBuilder sb = new StringBuilder();
		try(BufferedReader br = new BufferedReader(new FileReader("./html/"+pageName+".html"))){
			String line = "";
			while((line=br.readLine())!=null) {
				sb.append(line).append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return sb.toString();
	}
	
	private String getHTTPHeader() {
		String result = "";
		
		result+="HTTP/1.1 200 OK\r\n";
		result+="Server: HW114GroupWebUI Server\r\n";
		result+="Content-Type:text/html\r\n";
		result+="Content-Length: \r\n";
		result+="Conection: close\r\n\r\n";
		
		return result;
	}

	private void logRequest() {
		this.request.forEach(e -> System.out.println(e));
		System.out.println("--- "+JDate.getDate(new Date())+"  "+JDate.getTime(new Date())+" ---");
		System.out.println();
	}
}

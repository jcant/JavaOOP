package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Client implements Runnable {
	private Socket soc;
	private Thread thread;
	private List<String> request = new ArrayList<>();
	private Group group;

	public Client(Socket soc, Group group) {
		super();
		this.soc = soc;
		this.group = group;
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
		String header = "";
		String answer = "";
		String[] req = request.get(0).split(" ");
		String reqType = req[0];
		String[] rawAction = req[1].substring(1, req[1].length()).split(":");
		String action = rawAction[0];
		String params = (rawAction.length > 1) ? (rawAction[1]) : ("");

		if (action.equals("page")) {
			answer = getPage(params);
			header = getHTTPHeader();
		}

		if (action.equals("getGroup")) {
			answer = getGroup();
		}

		if (action.equals("delStudent")) {
			group.deleteStudent(new Integer(params));
			answer = "Deletion completed";
		}
		
		return header + answer;
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

	private String getGroup() {
		String result = "[";
		
		Student[] students = group.getStudentsArray();
		
		for (int i = 0; i<students.length; i++) {
			result += "\""+students[i].getName()+" "+students[i].getSurname()+"\"";
			if(i!=students.length-1) {
				result +=", ";
			}
		}
		
		result+="]";
		
		
		//result += "{\"rows\" : 123123}";

		return result;
	}

	private String getPage(String pageName) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader("./html/" + pageName + ".html"))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				sb.append(line).append(System.lineSeparator());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb.toString();
	}

	private String getHTTPHeader() {
		String result = "";

		result += "HTTP/1.1 200 OK\r\n";
		result += "Server: HW114GroupWebUI Server\r\n";
		result += "Content-Type:text/html\r\n";
		result += "Content-Length: \r\n";
		result += "Conection: close\r\n\r\n";

		return result;
	}

	private void logRequest() {
		this.request.forEach(e -> System.out.println(e));
		System.out.println("--- " + JDate.getDate(new Date()) + "  " + JDate.getTime(new Date()) + " ---");
		System.out.println();
	}
}

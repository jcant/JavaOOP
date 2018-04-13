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
import java.util.Optional;
import java.util.Scanner;

public class Client implements Runnable {
	private Socket soc;
	private Thread thread;
	private List<String> request = new ArrayList<>();
	private Group group;

	private String answer = "";

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
			requestProcessing();

			pw.print(answer);
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
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

	private void requestProcessing() {
		logRequest();
		String action = "";
		String params = "";
		String[] req = request.get(0).split(" ");
		String reqType = req[0];
		if (req[1].equals("/")) {
			action = "page";
			params = "main_page";
		} else {
			String[] rawAction = req[1].substring(1, req[1].length()).split(":");
			action = rawAction[0];
			params = (rawAction.length > 1) ? (rawAction[1]) : ("");
		}

		doAction(action, params);

	}

	private void doAction(String action, String params) {
		if (action.equals("page")) {
			this.answer = getHTTPHeader() + getPage(params);
		}
		if (action.equals("getGroup")) {
			this.answer = getGroup();
		}
		if (action.equals("delStudent")) {
			group.deleteStudent(new Integer(params));
			this.answer = "Deletion completed";
		}
		if (action.equals("getGroupInfo")) {
			this.answer = getGroupInfo();
		}
		if (action.equals("addStudent")) {
			addStudent(params);
			this.answer = "Add student completed";
		}
		if (action.equals("getStudent")) {
			this.answer = getStudent(params);
		}
	}

	private String getStudent(String params) {
		Student st = group.getStudent(new Integer(params));
		
		String result = "{";
		result+="\"name\":\""+st.getName()+"\",";
		result+="\"surname\":\""+st.getSurname()+"\",";
		result+="\"male\":\""+((st.isMale())?("male"):("female"))+"\",";
		result+="\"birthday\":\""+JDate.getDate(st.getBirthday())+"\",";
		result+="\"weight\":\""+String.format("%.2f", st.getWeight())+"\",";
		result+="\"height\":\""+String.format("%.2f", st.getHeight())+"\",";
		result+="\"institute\":\""+st.getInstitutionName()+"\",";
		result+="\"datein\":\""+JDate.getDate(st.getDateIn())+"\",";
		result+="\"avgscore\":\""+String.format("%.2f", st.getAvarageScore())+"\"";
		
		result+="}";
		
		return result;
	}
	
	private void addStudent(String params) {
		JDate.setDefaultFormat("yyyy-MM-dd");

		String[] vals = params.split(",");
		for (int i=0; i< vals.length; i++) {
			if (vals[i].equals("null")) {
				vals[i] = null;
			}
		}
		Optional<String> name = Optional.ofNullable(vals[0]);
		Optional<String> surname = Optional.ofNullable(vals[1]);
		boolean male = (vals[2].equals("true")) ? (true) : (false);
		Optional<Date> birthday = Optional.ofNullable((vals[3]==null)?(null):(JDate.getDate(vals[3])));
		Optional<Double> weight = Optional.ofNullable((vals[4]==null)?(null):(new Double(vals[4])));
		Optional<Double> height = Optional.ofNullable((vals[5]==null)?(null):(new Double(vals[5])));
		Optional<String> institute = Optional.ofNullable(vals[6]);
		Optional<Date> datein = Optional.ofNullable((vals[7]==null)?(null):(JDate.getDate(vals[7])));
		Optional<Double> avgscore = Optional.ofNullable((vals[8]==null)?(null):(new Double(vals[8])));

		group.addStudent(new Student(name.orElse("Unknown"), surname.orElse("Unknown"), birthday.orElse(new Date()),
				male, weight.orElse(1.0), height.orElse(5.0), institute.orElse("Unknown"), datein.orElse(new Date()),
				avgscore.orElse(0.0)));

		JDate.setDefaultFormat("dd-MM-yyyy");
	}

	private String getGroupInfo() {
		String result = "";

		result += "{\"StudentsCount\":" + group.getStudentsCount();
		result += ", \"GroupName\":\"" + group.getGroupName() + "\"";
		result += ", \"GroupAverageScore\":\"" + String.format("%.2f", group.getAverageScore()) + "\"";
		result += "}";
		// System.out.println(result);
		return result;
	}

	private String getGroup() {
		String result = "[";

		Student[] students = group.getStudentsArray();

		for (int i = 0; i < students.length; i++) {
			result += "\"" + students[i].getName() + " " + students[i].getSurname() + "\"";
			if (i != students.length - 1) {
				result += ", ";
			}
		}

		result += "]";

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

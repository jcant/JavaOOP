package com.gmail.gm.jcant;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Properties;
import java.util.Scanner;

public class Client implements Runnable {
	private Socket soc;
	private Thread thread;
	private int number = 0;

	public Client(Socket soc, int number) {
		super();
		this.soc = soc;
		this.number = number;
		thread = new Thread(this);
		thread.start();
	}

	@Override
	public void run() {
		try (InputStream is = soc.getInputStream();
				OutputStream os = soc.getOutputStream();
				PrintWriter pw = new PrintWriter(os)) {

			String readRequest = read(is);

			log(readRequest);

			String answer = "request = " + number + System.lineSeparator() + getSysInfo();

			pw.print(answer);
			pw.flush();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String read(InputStream is) {
		StringBuilder result = new StringBuilder();
		Scanner sc = new Scanner(is);
		int line = 0;

		while (true) {
			String sline = sc.nextLine();
			if (sline.equals("")) {
				break;
			}
			result.append("" + line + ") " + sline).append(System.lineSeparator());

			line++;
		}
		
		return result.toString();
	}

	private String getSysInfo() {
		StringBuilder sb = new StringBuilder();

		Properties prop = System.getProperties();

		sb.append("java.runtime.name" + " : " + prop.getProperty("java.runtime.name") + System.lineSeparator());
		sb.append("java.vm.version" + " : " + prop.getProperty("java.vm.version") + System.lineSeparator());
		sb.append("java.vm.vendor" + " : " + prop.getProperty("java.vm.vendor") + System.lineSeparator());
		sb.append("java.home" + " : " + prop.getProperty("java.home") + System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("os.name" + " : " + prop.getProperty("os.name") + System.lineSeparator());
		sb.append("os.arch" + " : " + prop.getProperty("os.arch") + System.lineSeparator());
		sb.append("os.version" + " : " + prop.getProperty("os.version") + System.lineSeparator());
		sb.append(System.lineSeparator());
		sb.append("user.name" + " : " + prop.getProperty("user.name") + System.lineSeparator());
		sb.append("user.home" + " : " + prop.getProperty("user.home") + System.lineSeparator());

		return sb.toString();
	}

	private void log(String rec) {
		System.out.println(rec);
	}
}

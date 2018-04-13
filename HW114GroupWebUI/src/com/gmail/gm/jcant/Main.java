package com.gmail.gm.jcant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		
		Student st1 = new Student("Ivan", "Ivanov",   JDate.getDate("07-11-1978"), true, 78.2, 185, "SVMI", JDate.getDate("01-09-1995"), 11.8);
		Student st2 = new Student("Petr", "Petrov",   JDate.getDate("07-11-1978"), true, 78.2, 185, "SVMI", JDate.getDate("01-09-1995"), 11.8);
		Student st3 = new Student("Sidor", "Sidorov", JDate.getDate("07-11-1978"), true, 78.2, 185, "SVMI", JDate.getDate("01-09-1995"), 11.8);

		Group gr = new Group("ATP255", new Student[] {st1,st2,st3});
		
		try (ServerSocket ssoc = new ServerSocket(7777)) {
			while (true) {
				Socket clisoc = ssoc.accept();
				Client cli = new Client(clisoc, gr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

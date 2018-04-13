package com.gmail.gm.jcant;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

	public static void main(String[] args) {
		
		try (ServerSocket ssoc = new ServerSocket(7777)) {
			while (true) {
				Socket clisoc = ssoc.accept();
				Client cli = new Client(clisoc);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

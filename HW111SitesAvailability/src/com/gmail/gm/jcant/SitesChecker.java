package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class SitesChecker {

	private Map<String, String> sitesList = new HashMap<>();

	public SitesChecker() {
		super();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sitesList.forEach((key, value) -> sb.append(key + " - " + value + System.lineSeparator()));

		return sb.toString();
	}

	public void addSiteList(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			String line = "";
			while ((line = br.readLine()) != null) {
				sitesList.put(line, checkSite(line));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String checkSite(String adr) {
		String response = "";
		try {
			URL url = new URL(adr);
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();
			int resp = connect.getResponseCode();
			if ((resp >= 200) && (resp < 300)) {
				response = "Site available (code=" + resp + ")";
			} else {
				response = "Some problem with connection... (code=" + resp + ")";
			}
		} catch (IOException e) {
			response = "Site is Unavailable";
		}
		return response;
	}

}

package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RefParser {
	private String site;
	private Map<String, Link> links = new HashMap<>();
	private String siteContent;

	public RefParser(String site) {
		super();
		this.site = site;
		loadSiteContent();
	}

	public String getSiteContent() {
		return siteContent;
	}

	public String getSite() {
		return site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Map<String, Link> getLinks() {
		return links;
	}

	private void loadSiteContent() {
		try {
			URL url = new URL(site);
			
			HttpURLConnection connect = (HttpURLConnection) url.openConnection();

			
			
			Optional<String> charset = Optional.ofNullable(connect.getHeaderField("charset"));

			if (connect.getContentLengthLong() != 0) {
				StringBuilder sb = new StringBuilder();
				InputStream is = connect.getInputStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is, charset.orElse("utf-8")));
				String line = "";
				while ((line = br.readLine()) != null) {
					sb.append(line).append(System.lineSeparator());
				}

				siteContent = sb.toString();
			}

		} catch (IOException e) {
			e.printStackTrace();
			siteContent = "UNAVAILABLE!!!";
		}
	}
}

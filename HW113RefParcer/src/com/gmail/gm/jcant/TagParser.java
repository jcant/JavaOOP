package com.gmail.gm.jcant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TagParser {
	private String site;
	private Map<String, Link> links = new HashMap<>();
	private String siteContent;

	public TagParser() {
		super();
	}

	public TagParser(String site, File file) {
		super();
		this.site = site;
		loadSiteContent();
		findAllRefs();
		saveToFile(file);
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

	private void findAllRefs() {
		Pattern p = Pattern.compile("<a.*?/a>");
		Matcher m = p.matcher(siteContent);

		while (m.find()) {
			Link lnk = parseRef(siteContent.substring(m.start(), m.end()));
			links.put(lnk.getRef(), lnk);
		}
	}

	private Link parseRef(String tagContent) {
		String raw = tagContent;
		String ref = "";
		String text = "";

		Pattern p = Pattern.compile("href=\".*?\"");
		Matcher m = p.matcher(tagContent);
		if (m.find()) {
			ref = tagContent.substring(m.start(), m.end());
			ref = ref.replaceAll("href=", "").replaceAll("\"", "");
		}

		p = Pattern.compile(">.+?</a");
		m = p.matcher(tagContent);
		if (m.find()) {
			text = tagContent.substring(m.start(), m.end());
			text = text.replaceAll("^>", "").replaceAll("</a$", "");
		}
		return new Link(ref, text, raw);
	}

	private void saveToFile(File file) {
		if (file == null) {
			throw new IllegalArgumentException("File is null!");
		}
		if (links.isEmpty()) {
			throw new IllegalArgumentException("Nothing to write - links list is empty!");
		}

		try (PrintWriter pw = new PrintWriter(file)) {

			links.forEach((key, value) -> pw.println(key));

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();

		links.forEach((key, value) -> sb.append(key + " - " + value + System.lineSeparator()));

		return sb.toString();
	}
}

package com.hackbulgaria.corejava.networking2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Crawler {
	public static ArrayList<URL> list = new ArrayList<>();
	public static String getPageHTML(URL url) throws IOException {
		StringBuilder builder = new StringBuilder();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				url.openStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			builder.append(inputLine);
		in.close();
		return builder.toString();
	}

	public static URL crawler(URL url, String needle) throws IOException {
		String contents = getPageHTML(url);
		if (contents.contains(needle)) {
			return url;
		}
		return recurseCrawler(url.toString(), needle, getAllLinks(contents));
	}

	public static URL recurseCrawler(String url, String needle, List list)
			throws IOException {
		URL url1 = null;
		if (!url.contains("http://ebusiness.free.bg/")) {

			url1 = new URL("http://ebusiness.free.bg/" + url);
		} else {
			url1 = new URL(url);
		}
		String contents = getPageHTML(url1);
		if (contents.contains(needle)) {
			list.add(url1);
			return url1;
		}
		if (!list.isEmpty()) {
			String nextURL = list.get(list.size() - 1).toString();
			list.remove(list.size() - 1);
			return recurseCrawler(nextURL, needle, list);
		}
			return null;

	}


	public static void main(String[] args) throws IOException {
		URL resultUrl = null;
		URL urlToSearchIn = new URL("http://ebusiness.free.bg/");
		resultUrl = crawler(urlToSearchIn, "Револвираща");
		System.out.println(resultUrl.toString());
		System.out.println("----------------------");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));

		}
	}

	private static List<String> getAllLinks(String content) {
		ArrayList<String> resultList = new ArrayList<>();
		String regex = "<a.*?href=\"((?!javascript).*?)\".*?>";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			resultList.add(matcher.group(1));
		}
		return resultList;
	}
}

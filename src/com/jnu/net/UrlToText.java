package com.jnu.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlToText {
	/**
	 * ��ȡһ����ҳȫ������
	 */
	public static String getOneHtml(final String htmlurl) throws IOException {
		URL url;
		String temp;
		final StringBuffer sb = new StringBuffer();
		try {
			url = new URL(htmlurl);
			final BufferedReader in = new BufferedReader(new InputStreamReader(
					url.openStream(), "gbk"));// utf-8��ȡ��ҳȫ������
			while ((temp = in.readLine()) != null) {
				sb.append(temp);
			}
			in.close();
		} catch (final MalformedURLException me) {
			System.out.println("�������URL��ʽ�����⣡����ϸ����");
			me.getMessage();
			throw me;
		} catch (final IOException e) {
			e.printStackTrace();
			throw e;
		}
		return sb.toString();
	}

	/**
	 * �����ҳ����
	 */
	public static String getTitle(final String s) {
		String regex;
		String title = "";
		final List<String> list = new ArrayList<String>();
		// regex = "<title>.*?</title>";
		// \\s ��Java������д�� ��ʾ \s
		// http://www.oschina.net/question/195686_46313
		regex = "<title>[\\s\\S]*?</title>";
		final Pattern pa = Pattern.compile(regex, Pattern.CANON_EQ);
		final Matcher ma = pa.matcher(s);
		while (ma.find()) {
			list.add(ma.group());
		}
		for (int i = 0; i < list.size(); i++) {
			title = title + list.get(i);
		}

		return outTag(title);
	}

	/**
	 * 
	 * ȥ�����
	 */
	public static String outTag(final String s) {
		return s.replaceAll("<.*?>", "");
	}

	// Please use it multiThread or may be return abnormally
	public static HashMap<String, String> getFromJWC(final String s) {
		final HashMap<String, String> hm = new HashMap<String, String>();
		// final StringBuffer sb = new StringBuffer();
		String html = "";
		System.out.println("\n------------------��ʼ��ȡ��ҳ(" + s
				+ ")--------------------");
		try {
			html = getOneHtml(s);
		} catch (final Exception e) {
			e.getMessage();
		}

		System.out.println("------------------��ȡ��ҳ(" + s
				+ ")����--------------------\n");

		String title = "";
		String newsContent = "";

		Pattern pa = Pattern
				.compile("color=\"#FF6600\"><p>&nbsp;</p>(.*?)</div>");// ,
																		// Pattern.DOTALL);
		Matcher ma = pa.matcher(html);
		while (ma.find()) {
			Pattern titlePattern = Pattern.compile("</p>(.*?)</div>");
			Matcher titleMatcher = titlePattern.matcher(ma.group());
			if (titleMatcher.find()) {
				title = outTag(titleMatcher.group());
			}
		}

		Pattern pa2 = Pattern
				.compile("<font class=news>(.*?)<hr size=1 color=#66CC99>");// ,
																			// Pattern.DOTALL);
		Matcher ma2 = pa2.matcher(html);

		if (ma2.find()) {
			newsContent = ma2.group();
		}

		newsContent = newsContent.replaceAll("<br>", "\n\n");// ת������
		newsContent = newsContent.replaceAll("</P>", "\n\n");// ת������
		newsContent = newsContent.replaceAll("&nbsp;", "");

		hm.put("title", outTag(title));
		hm.put("newsContent", outTag(newsContent));
		System.out.print(outTag(newsContent));

		return hm;

	}

}

package com.jnu.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import com.jnu.bean.Person;



public class JsonParse {
	/**
	 * ����Json���
	 * 
	 * @param urlPath
	 * @return mlists
	 * @throws Exception
	 */

	public static ArrayList<Person> getListPerson(String urlPath) throws Exception {
		ArrayList<Person> mlists = new ArrayList<Person>();
		byte[] data = readParse(urlPath);
		JSONArray array = new JSONArray(new String(data));
		for (int i = 0; i < array.length(); i++) {
			JSONObject item = array.getJSONObject(i);
			String name = item.getString("name");
			String address = item.getString("address");
			int age = item.getInt("age");
			mlists.add(new Person(name, address, age));
			System.out.println(name);
		}
		return mlists;
	}

	/**
	 * ��ָ����url�л�ȡ�ֽ�����
	 * 
	 * @param urlPath
	 * @return �ֽ�����
	 * @throws Exception
	 */
	public static byte[] readParse(String urlPath) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[1024];
		int len = 0;
		URL url = new URL(urlPath);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		InputStream inStream = conn.getInputStream();

		while ((len = inStream.read(data)) != -1) {
			outStream.write(data, 0, len);

		}
		inStream.close();
		return outStream.toByteArray();

	}
}

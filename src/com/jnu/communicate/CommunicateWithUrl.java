package com.jnu.communicate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CommunicateWithUrl {
	// /JSONObject !!
	public static JSONObject getFromUrl(String get_url) {

		HttpClient client = new DefaultHttpClient();
		StringBuilder builder = new StringBuilder();
		JSONObject jsonObject;
		HttpGet get = new HttpGet(get_url);

		try {
			HttpResponse response = client.execute(get);
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			for (String s = reader.readLine(); s != null; s = reader.readLine()) {
				builder.append(s);
			}

			// client.getConnectionManager().shutdown();

			jsonObject = new JSONObject(builder.toString());
			// jsonObject.getString("account");
			return jsonObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static JSONArray postToUrl(String post_url, JSONArray param) {
		try {
			HttpPost request = new HttpPost(post_url);
			JSONArray result;
			// �󶨵����� Entry
			StringEntity se;

			se = new StringEntity(param.toString());

			request.setEntity(se);

			// ��������
			HttpResponse httpResponse = new DefaultHttpClient()
					.execute(request);
			// �õ�Ӧ����ַ���Ҳ��һ�� JSON ��ʽ��������
			String retSrc = EntityUtils.toString(httpResponse.getEntity());
			// ��� JSON ����
			result = new JSONArray(retSrc);
			return result;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		return null;
	}

	public static void receiveByPost(String url)
			throws ClientProtocolException, IOException {

		HttpClient httpclient = new DefaultHttpClient();
		HttpPost httppost = new HttpPost(url);
		File file = new File("C:\\2");
		InputStreamEntity reqEntity = new InputStreamEntity(
				new FileInputStream(file), -1);
		reqEntity.setContentType("binary/octet-stream");
		reqEntity.setChunked(true);
		// It may be more appropriate to use FileEntity class in this particular
		// instance but we are using a more generic InputStreamEntity to
		// demonstrate
		// the capability to stream out data from any arbitrary source
		// FileEntity entity = new FileEntity(file, "binary/octet-stream");
		httppost.setEntity(reqEntity);
		System.out.println("executing request " + httppost.getRequestLine());
		HttpResponse response = httpclient.execute(httppost);

	}

	public static void getCookie() throws ClientProtocolException, IOException {
		HttpClient httpclient = new DefaultHttpClient();

		// ����һ������Cookie�洢��ʵ��
		CookieStore cookieStore = new BasicCookieStore();
		// ����һ��������������Ϣ
		HttpContext localContext = new BasicHttpContext();
		// �ڱ����������а�һ�����ش洢
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);

		// ���������·��
		HttpGet httpget = new HttpGet("http://www.google.com/");
		// ���ݱ��ص�http�����ĸ������
		HttpResponse response = httpclient.execute(httpget, localContext);

		// ��ȡ������Ϣ
		HttpEntity entity = response.getEntity();
		System.out.println(response.getStatusLine());
		if (entity != null) {
			System.out.println("Response content length: "
					+ entity.getContentLength());
		}
		// ��ȡcookie�еĸ�����Ϣ
		List<Cookie> cookies = cookieStore.getCookies();
		for (int i = 0; i < cookies.size(); i++) {
			System.out.println("Local cookie: " + cookies.get(i));
		}
		// ��ȡ��Ϣͷ����Ϣ
		Header[] headers = response.getAllHeaders();
		for (int i = 0; i < headers.length; i++) {
			System.out.println(headers[i]);
		}

	}

	public static void main(String[] args) {
		try {
			receiveByPost("http://cjhyde.com/1.gif");
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

// ��������Ĳ���ļ��ַ�ʽ
// A.�������·�����Բ�ѯ�ַ��ʽ���ݲ���
// B.�������ʵ������Ӳ���
// List <NameValuePair> nvps = new ArrayList <NameValuePair>();
// nvps.add(new BasicNameValuePair("IDToken1", "username"));
// nvps.add(new BasicNameValuePair("IDToken2", "password"));
// httpost.setEntity(new UrlEncodedFormEntity(nvps, HTTP.UTF_8));
// ��ʵҲ���Խ���Ӧ���ֶη�װ��JSON �Ȼ��ͨ���������������ԭ��һ��


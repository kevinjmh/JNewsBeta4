package com.jnu.net;

import java.util.HashMap;

import com.jnu.ui.R;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class DownloadPassage extends
		AsyncTask<String, Integer, HashMap<String, String>> {

	private Context mContext;

	public DownloadPassage(Context context) {
		this.mContext = context;
	}

	@Override
	protected void onPreExecute() {

	}

	@Override
	protected HashMap<String, String> doInBackground(String... urls) {
		// ��Ҫ�ڸ��̸߳��½���
		return UrlToText.getFromJWC(urls[0]);
	}

	@Override
	protected void onProgressUpdate(Integer... progress) {

	}

	// �÷����Զ��õ� doInBackground �ķ��� ���������̣߳�UI������ �������ڸ��½���
	@Override
	protected void onPostExecute(HashMap<String, String> contentTitleMap) {

		// WebView webView =
		// (WebView)((Activity)mContext).findViewById(R.id.passage_view);
		// webView.loadDataWithBaseURL(null,contentTitleMap.get("newsContent"),
		// "text/html", "UTF-8","UTF-8");
		// webView.getSettings().setJavaScriptEnabled(true);
		// webview.loadUrl("file:///" +
		// Environment.getExternalStorageDirectory().getAbsolutePath() +
		// "/"+"1.html");

		String title = contentTitleMap.get("title");
		String content = contentTitleMap.get("newsContent");

		TextView tv = (TextView) ((Activity) mContext)
				.findViewById(R.id.passage_textview);
		tv.setText(title + "\n\n" + content);
		((Activity) mContext).setTitle(title);

	}

}

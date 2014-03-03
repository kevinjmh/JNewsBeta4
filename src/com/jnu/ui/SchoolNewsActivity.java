package com.jnu.ui;

import static com.jnu.utils.NetworkUtils.isNetworkAvailable;
import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.jnu.jnews.MyListView;
import com.jnu.net.DownloadUrlTask;

public class SchoolNewsActivity extends Activity {
	private LinearLayout layout;
	 private final String url = "http://jwcweb.jnu.edu.cn/index-new.asp";
     private MyListView urlList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
      //  setContentView(R.layout.slidingmenumain);
		LinearLayout layout=new LinearLayout(this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
      //getWindow().getDecorView().setBackgroundColor(0xff00ff00);
		urlList = new MyListView(this);
		if( isNetworkAvailable(SchoolNewsActivity.this)){
	 	DownloadUrlTask urlDownload = new DownloadUrlTask(this,urlList);
    	urlDownload.execute(url);
    	layout.addView(urlList,layoutParams);
    	//layout.setBackgroundColor(0xff444444);
    	setContentView(layout, layoutParams);
    	// view.addView(urlList);
		}
		else{
			Toast.makeText(this, "无网络连接", Toast.LENGTH_SHORT).show();
		}
        
		
		
		

		
		
	}
}

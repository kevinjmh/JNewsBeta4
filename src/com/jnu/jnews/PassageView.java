package com.jnu.jnews;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.webkit.WebSettings;
import android.webkit.WebSettings.LayoutAlgorithm;
import android.webkit.WebSettings.ZoomDensity;
import android.webkit.WebView;
import com.jnu.simpleAsync.*;
import com.jnu.ui.R;

public class PassageView extends BaseActivity {
	    
	    // 新浪手机网页转码
	 
	    private WebView webText ;
	
		@SuppressWarnings("deprecation")
		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.passage);
		//	setContentView(R.layout.passage_text);
			
			Intent intent = getIntent();
			Bundle bundle = intent.getExtras();
			String url =(String)bundle.get("url");
			webText = (WebView)findViewById(R.id.passage_view);
			webText.loadUrl(url);
			
	        //设置屏幕自适应
			 WebSettings webSettings = webText.getSettings();
			    webSettings.setSupportZoom(true);
			    webSettings.setJavaScriptEnabled(true);
			    webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
			    webSettings.setBuiltInZoomControls(true);//support zoom
			    webSettings.setPluginsEnabled(true);//support flash
			    webSettings.setUseWideViewPort(true);//關鍵點
			    webSettings.setLoadWithOverviewMode(true);

			    DisplayMetrics metrics = new DisplayMetrics();
			  getWindowManager().getDefaultDisplay().getMetrics(metrics);
			  int mDensity = metrics.densityDpi;
			  //DebugLog.d(TAG, "densityDpi = " + mDensity);
			  if (mDensity == 240) { 
			   webSettings.setDefaultZoom(ZoomDensity.FAR);
			  } else if (mDensity == 160) {
			     webSettings.setDefaultZoom(ZoomDensity.MEDIUM);
			  } else if(mDensity == 120) {
			   webSettings.setDefaultZoom(ZoomDensity.CLOSE);
			  }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){
			   webSettings.setDefaultZoom(ZoomDensity.FAR); 
			  }else if (mDensity == DisplayMetrics.DENSITY_TV){
			   webSettings.setDefaultZoom(ZoomDensity.FAR); 
			  }
			
			
			/*
		    this.doAsync(new Callable<HashMap<String, String>>() {  
		            // 希望异步加载的数据  
		            public HashMap<String, String> call() throws Exception {  
		                return UrlToText.getFromJWC(url);   
		            }  
		        }, new Callback<HashMap<String, String>>() {  
		            // 当加载完成后回调，在UI线程中的操作  
		            public void onCallback(final HashMap<String, String> contentTitleMap) {  
		  		      String title = contentTitleMap.get("title");
				      String content = contentTitleMap.get("newsContent");
				   
				      TextView tv =  (TextView)PassageView.this.findViewById(R.id.passage_textview);
				      tv.setText(title+"\n\n"+content);
				      PassageView.this.setTitle(title);
		            }  
		        });  */
		      

		}
	    
}

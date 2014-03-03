package com.jnu.ui;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.DownloadListener;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;

/**
 * Created by harry on 5/28/13.
 */
public class Together extends Activity implements View.OnClickListener {
    private ImageButton btnFirst,btnSecond,btnThird;
    private WebView togetherView;
    private String defaultUrl="http://cjhyde.com/yikuaiba/index.php";
    private String secondUrl="http://jnews.cjhyde.com";
    private String thirdUrl="about:blank";
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.together);
        
        
        
       
        
        this.initWebView();
        
        togetherView.getSettings().setJavaScriptEnabled(true);
        togetherView.setDownloadListener(new DownloadListener() {
            @Override
            public void onDownloadStart(String url, String userAgent, String contentDisposition, String mimetype, long contentLength) {
                if (url != null && url.startsWith("http://"))
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });
     
        
        togetherView.loadUrl(defaultUrl);
         
    /*    togetherView.setWebViewClient(new WebViewClient(){  
            @Override  
            public void onPageFinished(WebView view, String url) {  
                    // TODO Auto-generated method stub  
                    super.onPageFinished(view, url);  
                    //页面下载完毕,却不代表页面渲染完毕显示出来  
                    //WebChromeClient中progress==100时也是一样  
                    if ( togetherView.getContentHeight() != 0) {  
                            //这个时候网页才显示  
                    }  
            }  
            @Override  
            public boolean shouldOverrideUrlLoading(WebView view, String url) {  
                    // TODO Auto-generated method stub  
                    //自身加载新链接,不做外部跳转  
                    view.loadUrl(url);  
                    return true;  
            }  
               
});  */

      

        }

    

    @Override
    public void onClick(View v) {
        switch (v.getId()){
           /* case R.id.btnFirst:
                togetherView.loadUrl(defaultUrl);
                break;
            case R.id.btnSecond:
                togetherView.loadUrl(secondUrl);
                break;
            case R.id.btnThird:
                togetherView.loadUrl(thirdUrl);
                break;*/
        }
    }
    
    /*

     * 初始化WebView

     */ 

    private void initWebView(){ 

        //从布局文件中扩展webView 

        this. togetherView=(ProgressWebView) findViewById(R.id.together_webView); 

        this. togetherView.setWebChromeClient(new chromeClient()); 
        
       /*this. btnFirst = (ImageButton)findViewById(R.id.btnFirst);
       this.  btnSecond = (ImageButton)findViewById(R.id.btnSecond);
       this. btnThird = (ImageButton)findViewById(R.id.btnThird);

     //   togetherView = (WebView)findViewById(R.id.together_webView);
       
       this.  btnFirst.setOnClickListener(this);
       this.  btnSecond.setOnClickListener(this);
       this.  btnThird.setOnClickListener(this);
*/
       // this. togetherView.loadUrl("defaultUrl"); 

          

    } 
    class chromeClient extends WebChromeClient{ 

    	 

    	 

        @Override 

        public void onProgressChanged(WebView view, int newProgress) { 

            //动态在标题栏显示进度条 

        	Together.this.setProgress(newProgress*100); 

            super.onProgressChanged(view, newProgress); 

        } 

 

 

        @Override 

        public void onReceivedTitle(WebView view, String title) { 

            //设置当前activity的标题栏 

        	Together.this.setTitle(title); 

            super.onReceivedTitle(view, title); 

        } 

         

    } 

     
}

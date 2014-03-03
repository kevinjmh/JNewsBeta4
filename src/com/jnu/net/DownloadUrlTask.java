package com.jnu.net;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DownloadUrlTask extends AsyncTask<String,Integer,ArrayList<String>>
 implements OnCancelListener{

   private ListView urlListView;
   private Context mContext;
   ProgressDialog pd = null;
   public DownloadUrlTask( Context mContext,ListView urlListView){
	   this.urlListView =  urlListView;
	   this.mContext = mContext;
   }
   
   protected void onPreExecute(){
	  pd = new ProgressDialog(mContext);
	  pd.setTitle("正在载入");
	  pd.setMessage("读取目录");
	  pd.setCancelable(true);
	  pd.setOnCancelListener(this);
	  pd.setIndeterminate(false);
	  pd.show();
   }
  
   protected ArrayList<String> doInBackground(String... urls ){
	   // 不要在该线程更新界面
	   ArrayList<String> str = new ArrayList<String>();
      try {
		str =   HtmlParse.getHtmlInfo(urls[0]);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	  return str;
   }
   protected void onProgressUpdate(Integer... progress){

   }
 
   // 该方法自动得到 doInBackground 的返回 并且在主线程（UI）运行 可以用于更新界面
   protected void onPostExecute(ArrayList<String> allList){
       
	   ArrayList<String> titleList = new ArrayList<String>();
	   final ArrayList<String> urlList = new ArrayList<String>();
	   int i = 0;
	   for(String s:allList){
		   if(i%2 == 1)
			   titleList.add(s);
		   else
			   urlList.add(s);
		   
		   ++i;
	   }
	      
	   urlListView.setAdapter( new ArrayAdapter<String>
	           (mContext ,android.R.layout.simple_list_item_1, titleList)); 
	   
	//   urlListView.setAdapter( new SimpleAdapter
	//           (mContext,titleList,R.layout.simple_list,new String[]{},new int[]{R.id.url_title} ));  
	   
	   urlListView.setOnItemClickListener(new OnItemClickListener() {
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                long arg3) {
        	// arg2 The position of the view in the adapter.
         //   setTitle("点击第"+arg2+"个项目");
        	 String   url = urlList.get(arg2);
        	 System.out.println("huang"+url);
        	 Intent intent = new Intent(mContext,com.jnu.jnews.PassageView.class);
        	 intent.putExtra("url", url);
        	 mContext.startActivity(intent);
        	 
        }
    });
	
	pd.cancel();
   }

@Override
public void onCancel(DialogInterface dialog) {
	// TODO Auto-generated method stub
	this.cancel(true);
}
   
  
   
   
}
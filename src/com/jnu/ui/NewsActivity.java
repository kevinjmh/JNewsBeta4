package com.jnu.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import com.jnu.bean.Person;
import com.jnu.utils.JsonParse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//放第一屏的最新消息
public class NewsActivity extends Activity {
	private static final String urlPath = "http://cjhyde.com/jnews/php/json.php";//"http://192.168.2.105:8080/JsonServer/JsonServlet";
	private List<Person> persons;
    List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView listview=new ListView(this);
		LinearLayout layout=new LinearLayout(this);
		
	//	listview.setTextColor(this.getResources().getColor(R.color.black)); 
		SimpleAdapter adapter = new SimpleAdapter(this,getData(),R.layout.vlist,
				new String[] { "name","address", "age" },
				new int[]{R.id.name,R.id.address,R.id.age});
		listview.setAdapter(adapter);
		
		//Button button = new Button(this);
		//button.setText("number one");
		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
			ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
		
		layout.addView(listview,layoutParams);
		
		LinearLayout.LayoutParams Newsl = new LinearLayout.LayoutParams(
				ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT);
		// getWindow().getDecorView().setBackgroundColor(0xff99ccff);
		//layout.setBackgroundColor(0x00444444);
		setContentView(layout,Newsl);
	}
	
	private List<Map<String,Object>> getData() {

		try {
			// 得到Json解析成功之后数据
        new Thread(new getPersonList(urlPath)).start();
			
		} catch (Exception e) {
			Toast.makeText(NewsActivity.this, "解析失败", 2000);//在手机上显示提示Toast，2秒
			
		}
		return data;
	}

    class  getPersonList implements Runnable {
        private String urlPath;
        public getPersonList(String urlPath){
             this.urlPath = urlPath;
        }
        @Override
        public void run() {
            try {
                persons = JsonParse.getListPerson(urlPath);
                for (int i = 0; i < persons.size(); i++) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    map.put("name", persons.get(i).getName());
                    map.put("address", persons.get(i).getAddress());
                    map.put("age", persons.get(i).getAge());
                    data.add(map);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}


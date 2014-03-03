package com.jnu.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.Toast;
import com.jnu.jnews.Login;

public class MainActivity extends Activity {
	MyImageView news, share, login, wechat;
   
	private long group_id;
    private int position;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 初始化shareSDK
	//	AbstractWeibo.initSDK(this);
        
		setContentView(R.layout.main);
		news = (MyImageView) findViewById(R.id.news);
		news.setOnClickIntent(new MyImageView.OnViewClick() {
			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				Intent i=new Intent(MainActivity.this,
						JNewsActivity.class);

//				i.putExtra("groupid", group_id);
//				i.putExtra("grouppos", position);
				i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i);

				
			}
		});

		share = (MyImageView) findViewById(R.id.share);
		share.setOnClickIntent(new MyImageView.OnViewClick() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
                Intent i=new Intent(MainActivity.this,
                       Together.class);

                i.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(i);
			}});

		login = (MyImageView) findViewById(R.id.login);
		login.setOnClickIntent(new MyImageView.OnViewClick() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				Intent i = new Intent(MainActivity.this, Login.class)
						.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
				startActivity(i);
			}
		});

		wechat = (MyImageView) findViewById(R.id.wechat);
		wechat.setOnClickIntent(new MyImageView.OnViewClick() {

			@Override
			public void onClick() {
				// TODO Auto-generated method stub
				// WechatPage page = new WechatPage(menu);
				// menu.setBodyView(page.getPage());

//				Intent i = new Intent(MainActivity.this,
//						SlidingmenuActivity.class)
//						.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				startActivity(i);
				Toast.makeText(getApplicationContext(), "comming soon!请微信关注JNews，搜索公众账号JNEWS_WS", Toast.LENGTH_SHORT).show();
				
			}
		});
	}
	
	private long exitTime = 0;
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		
		//双击退出
		if(keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN){
				if((System.currentTimeMillis()-exitTime) > 2000){
				Toast.makeText(getApplicationContext(), "再按一次后退键退出程序", Toast.LENGTH_SHORT).show();
				exitTime = System.currentTimeMillis();
				} else {
				//退出代码
					onDestroy();
				}
				return true;
				}
		return super.onKeyDown(keyCode, event);	
	}
	
	protected void onDestroy() {
		super.onDestroy();
		System.exit(0); // 结束进程
	}
	
}
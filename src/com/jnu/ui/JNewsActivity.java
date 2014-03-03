package com.jnu.ui;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ActivityGroup;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class JNewsActivity extends ActivityGroup {
	//private LinearLayout layout;
	private ViewPager pager;
	private ArrayList<View> pageViews;

	//private LinearLayout layout2;
	private ArrayList<ImageView> imageViews;

	// init pageview
	void Init() {
		pageViews = new ArrayList<View>();
		View view00 = getLocalActivityManager().startActivity("activity01",
				new Intent(this, NullActivity.class)).getDecorView();
		View view01 = getLocalActivityManager().startActivity("activity01",
				new Intent(this, SchoolNewsActivity.class)).getDecorView();
		View view02 = getLocalActivityManager().startActivity("activity02",
				new Intent(this, HotNewsActivity.class)).getDecorView();
		View view03 = getLocalActivityManager().startActivity("activity02",
				new Intent(this, NewsActivity.class)).getDecorView();
		View view04 = getLocalActivityManager().startActivity("activity01",
				new Intent(this, NullActivity.class)).getDecorView();
		pageViews.add(view00);
		pageViews.add(view01);
		pageViews.add(view02);
		pageViews.add(view03);
		pageViews.add(view04);
	}

	void Init_Point() {
		imageViews = new ArrayList<ImageView>();
		ImageView imageView;
		for (int i = 0; i < pageViews.size(); i++) {
			imageView = new ImageView(this);
			imageView.setLayoutParams(new LayoutParams(5, 5));
			imageView.setBackgroundResource(R.color.js_blue);
			/*LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
					new ViewGroup.LayoutParams(LayoutParams.WRAP_CONTENT,
							LayoutParams.FILL_PARENT));*/
		//	layoutParams.leftMargin = 20;
		//	layoutParams.rightMargin = 20;
		//	layoutParams.weight=1;
			//layout2.addView(imageView, layoutParams);
			if (i == 0 || i == pageViews.size() - 1) {
				imageView.setVisibility(View.GONE);
			}
			if (i == 1) {
				//imageViews.get(i).setBackgroundResource(R.drawable.new_news);
				imageView.setBackgroundResource(R.drawable.d2);
			}
			imageViews.add(imageView);

		}
	}

	public void draw_Point(int index) {
		for (int i = 1; i < imageViews.size(); i++) {

			if (index == i) {
				if(i==1)
					imageViews.get(i).setBackgroundResource(R.drawable.d2);
				else{
					imageViews.get(i).setBackgroundResource(R.drawable.d2);
				}
			} else
				imageViews.get(i).setBackgroundResource(R.color.js_blue);
                
		}

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainviewpagerlyout);
		pager = (ViewPager) findViewById(R.id.vp_contains);
		//layout2 = (LinearLayout) findViewById(R.id.iv_image);
		Init();
		Init_Point();
		pager.setAdapter(new myPagerView());

		pager.setCurrentItem(1);

		pager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				draw_Point(arg0);// 描绘分页点，一定要写在这里.
				// 如果是第一屏或者是最后一屏禁止滑动，其实这里实现的是如果滑动的是第一屏则跳转至第二屏，如果是最后一屏则跳转到倒数第二屏.
				if (arg0 == imageViews.size() - 1 || arg0 == 0) {
					if (arg0 == 0) {
						pager.setCurrentItem(arg0 + 1);// 第二屏 会再次实现该回调方法实现跳转.
						imageViews.get(1).setBackgroundResource(R.drawable.d2);
					} else {
						pager.setCurrentItem(arg0 - 1);// 倒数第二屏
						imageViews.get(arg0 - 1).setBackgroundResource(
								R.drawable.d2);
					}
				}

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {

			}
		});
	}

	@Override
	protected void onNewIntent (Intent intent)
	{
		 super.onNewIntent(intent);  
		 setIntent(intent);   
		 Bundle bundle = intent.getExtras();
		 if(bundle!=null){
			long groupid = bundle.getLong("groupid", 0);
			int 	lastPosition = 0;
		 }
	}
	class myPagerView extends PagerAdapter {
		// 显示数目
		@Override
		public int getCount() {
			return pageViews.size();
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public int getItemPosition(Object object) {
			// TODO Auto-generated method stub
			return super.getItemPosition(object);
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			// TODO Auto-generated method stub
			((ViewPager) arg0).removeView(pageViews.get(arg1));
		}

		/***
		 * 获取每一个item， 类于listview中的getview
		 */
		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(pageViews.get(arg1));
			return pageViews.get(arg1);
		}

	}
}

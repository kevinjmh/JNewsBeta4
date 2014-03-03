package com.jnu.jnews;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.jnu.ui.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import static com.jnu.communicate.CommunicateWithUrl.*;

public class Login extends Activity {

	private Button loginButton;
	private EditText account;
	private EditText password;
	private String account_str;
	private String password_str;
	private JSONArray result_message;
	private MessageHandler sender_handler = new MessageHandler(Login.this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		loginButton = (Button) findViewById(R.id.login_button);
		account = (EditText) findViewById(R.id.account);
		password = (EditText) findViewById(R.id.password);

		loginButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				account_str = account.getText().toString();
				password_str = password.getText().toString();

				JSONObject loginRequire = new JSONObject();
				try {
					loginRequire.put("account", account_str);

					loginRequire.put("password", password_str);

					JSONObject cookie = new JSONObject();
					cookie.put("cookie", null);

					JSONArray result = new JSONArray();
					result.put(loginRequire);
					result.put(cookie);

					new Thread(new LoginByUrl(result)).start();

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		});

	}

	class LoginByUrl implements Runnable {
		JSONArray result;

		public LoginByUrl(JSONArray result) {
			this.result = result;
		}

		@Override
		public void run() {

			result_message = postToUrl("http://cjhyde.com/2.html", result);

			if (result_message != null) {
				JSONObject login_result;
				try {
					login_result = result_message.getJSONObject(0);

					if (login_result.get("LoginResult").equals("success")) {
						sender_handler.sendEmptyMessage(0);
						System.out.println("success!");
					} else {
						sender_handler.sendEmptyMessage(1);
						System.out.println("fail!");
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				sender_handler.sendEmptyMessage(2);
				System.out.println("no connection!!");
			}

		}
	}

}

class MessageHandler extends Handler {
	Context mContext;

	public MessageHandler(Context mContext) {
		this.mContext = mContext;
	}

	@Override
	public void handleMessage(Message msg) {
		super.handleMessage(msg);
		switch (msg.what) {
		case 0:
			Toast.makeText(mContext, "Login Success!", Toast.LENGTH_LONG)
					.show();
			System.out.println("something !!!!");
			break;
		case 1:
			Toast.makeText(mContext, "Login fail!", Toast.LENGTH_LONG).show();
			break;
		case 2:
			Toast.makeText(mContext, "Connect fail!", Toast.LENGTH_LONG).show();
			break;

		}
	}
}

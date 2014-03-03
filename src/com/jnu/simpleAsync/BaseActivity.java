package com.jnu.simpleAsync;

import android.app.Activity;
import android.content.Context;

/**
 * ����Activity,���ڴ����ҵ���޹صĹ��÷���
 * 
 * @ClassName: BaseActivity
 * @author ����
 * @version 1.0 2011-12-11 ����7:03:18
 */
public abstract class BaseActivity extends Activity {

	/**
	 * ��װ��asynctask�������˷���û�н�ȿ�.
	 * 
	 * @param pCallEarliest
	 *            ���������̣߳�����ִ�д˷���.
	 * @param mCallable
	 *            �������첽�߳�,�ڶ�ִ�д˷���.
	 * @param mCallback
	 *            ���������߳�,���ִ�д˷���.
	 */
	public <T> void doAsync(final CallEarliest<T> pCallEarliest,
			final Callable<T> mCallable, final Callback<T> mCallback) {
		AsyncTaskUtils.doAsync(pCallEarliest, mCallable, mCallback);
	}

	/**
	 * ��װ��asynctask�������˷���ӵ�н�ȶԻ��򣬲�֧�ֶ�����ʽ.
	 * 
	 * @param pContext
	 *            ������
	 * @param styleID
	 *            �Ի�����ʽ
	 *            ProgressDialog.STYLE_HORIZONTAL|ProgressDialog.STYLE_SPINNER
	 * @param pTitle
	 *            ����
	 * @param pMessage
	 *            ����
	 * @param pCallEarliest
	 *            ���������̣߳�����ִ�д˷���.
	 * @param progressCallable
	 *            �������첽�߳�,���ڴ��ݶԻ�����.
	 * @param pCallback
	 *            ���������߳�,���ִ�д˷���.
	 */
	public <T> void doProgressAsync(final Context pContext, final int styleID,
			final String pTitleResID, final String pMessageResID,
			final CallEarliest<T> pCallEarliest,
			final ProgressCallable<T> pCallable, final Callback<T> pCallback) {

		AsyncTaskUtils.doProgressAsync(pContext, styleID, pTitleResID,
				pMessageResID, pCallEarliest, pCallable, pCallback);
	}

	/**
	 * ��װ��asynctask�������˷���ӵ�н�ȶԻ��򣬲�֧�ֶ�����ʽ.
	 * 
	 * @param pContext
	 *            ������
	 * @param styleID
	 *            �Ի�����ʽ
	 *            ProgressDialog.STYLE_HORIZONTAL|ProgressDialog.STYLE_SPINNER
	 * @param pTitle
	 *            ����,��Դid
	 * @param pMessage
	 *            ����,��Դid
	 * @param pCallEarliest
	 *            ���������̣߳�����ִ�д˷���.
	 * @param progressCallable
	 *            �������첽�߳�,���ڴ��ݶԻ�����.
	 * @param pCallback
	 *            ���������߳�,���ִ�д˷���.
	 */
	public <T> void doProgressAsync(final Context pContext, final int styleID,
			final int pTitleResID, final int pMessageResID,
			final CallEarliest<T> pCallEarliest,
			final ProgressCallable<T> pCallable, final Callback<T> pCallback) {

		AsyncTaskUtils.doProgressAsync(pContext, styleID, pTitleResID,
				pMessageResID, pCallEarliest, pCallable, pCallback);
	}

}
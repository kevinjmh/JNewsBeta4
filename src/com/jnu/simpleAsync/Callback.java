package com.jnu.simpleAsync;

/* �ص��ӿ�,�ص��������������߳�   
 * @ClassName: Callback      
 * @author ����   
 * @version 1.0 2012-1-16 ����5:58:16      
 * @param <T>   
 */
public interface Callback<T> {

	public void onCallback(final T pCallbackValue);
}
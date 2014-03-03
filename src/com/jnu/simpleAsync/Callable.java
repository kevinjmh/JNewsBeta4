package com.jnu.simpleAsync;

/* �ص��ӿ�,�ص������������첽�߳�
 * @ClassName: Callable   
 * @author ����
 * @version 1.0 2012-1-16 ����5:56:42   
 * @param <T>
 */
public interface Callable<T> {

	public T call() throws Exception;
}
package com.jnu.simpleAsync;

/**
 * ���۲���
 * 
 * @ClassName: ProgressCallable
 * @author ����
 * @version 1.0 2012-1-16 ����11:08:52
 * @param <T>
 */
public interface ProgressCallable<T> {

	/**
	 * ע��۲��߶���
	 * 
	 * @param pProgressListener
	 * @return
	 * @throws Exception
	 */
	public T call(final IProgressListener pProgressListener) throws Exception;
}
package cn.soft.dao;

import cn.soft.common.QueryResult;

public interface Dao {
	
	
	/**
	 * ����һ������
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * ����һ������
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * ���÷��͵ķ�ʽ�Դ�������͸���id����ɾ��
	 * @param <T>
	 * @param entityClass ʵ������
	 * @param entityid ʵ��id
	 */
	public <T> void delete(Class<T> entityClass,Object entityid);
	
	/**
	 * ���÷��͵ķ�ʽͬʱɾ������Ķ��id����Ӧ�Ķ���
	 * @param <T>
	 * @param entityClass ʵ������
	 * @param entityid ʵ��id
	 */
	public <T> void deletes(Class<T> entityClass,Object[] entityid);
	
	/**
	 * ��ָ���������id���в���
	 * @param <T>
	 * @param entityClass ʵ������
	 * @param entityId ʵ��id
	 * @return
	 */
	public <T> T find(Class<T> entityClass,Object entityId);
	
	/**
	 * ��ҳ���Ҽ�¼
	 * @param <T> ���÷��Ϳɸ��ݲ�ͬ�����ͽ��в���
	 * @param entityClass ʵ������
	 * @param firstIndex ��ʼ������
	 * @param maxCount �����ҵļ�¼��
	 * @param orderby ����order by �������� �ڴ˴���ñ����Ѿ�����Ϊo ������ʹ��ʱӦдΪ��order by o.ʵ������ desc����asc
	 * ����һ���ַ���  ʹ���߿��Ը�����Ҫ��order by�Ӿ�ʹ��֮ǰ���������sql��� ���˶�������Ѿ�����ֻ��ʹ�����������o
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount,String orderby);
	
	/**
	 * ��ҳ���Ҽ�¼  ������������������
	 * @param <T>
	 * @param entityClass
	 * @param firstIndex
	 * @param maxCount
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount);
	
	/**
	 * ���ҳ�ȫ��ʵ��ļ�¼���� �����з�ҳ��ʾ
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass);
}

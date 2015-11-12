package cn.soft.dao;

import cn.soft.common.QueryResult;

public interface Dao {
	
	
	/**
	 * 保存一个对象
	 * @param entity
	 */
	public void save(Object entity);
	
	/**
	 * 更新一个对象
	 * @param entity
	 */
	public void update(Object entity);
	
	/**
	 * 运用泛型的方式对传入的类型根据id进行删除
	 * @param <T>
	 * @param entityClass 实体类型
	 * @param entityid 实体id
	 */
	public <T> void delete(Class<T> entityClass,Object entityid);
	
	/**
	 * 运用泛型的方式同时删除传入的多个id所对应的对象
	 * @param <T>
	 * @param entityClass 实体类型
	 * @param entityid 实体id
	 */
	public <T> void deletes(Class<T> entityClass,Object[] entityid);
	
	/**
	 * 对指定对象根据id进行查找
	 * @param <T>
	 * @param entityClass 实体类型
	 * @param entityId 实体id
	 * @return
	 */
	public <T> T find(Class<T> entityClass,Object entityId);
	
	/**
	 * 分页查找记录
	 * @param <T> 运用泛型可根据不同的类型进行查找
	 * @param entityClass 实体类型
	 * @param firstIndex 开始索引号
	 * @param maxCount 最大查找的记录数
	 * @param orderby 运用order by 进行排序 在此处类得别名已经定义为o 所以在使用时应写为：order by o.实体属性 desc或者asc
	 * 这是一个字符串  使用者可以根据需要在order by子句使用之前添加其它的sql语句 但此对象别名已经定义只能使用这个别名：o
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount,String orderby);
	
	/**
	 * 分页查找记录  但不按条件进行排序
	 * @param <T>
	 * @param entityClass
	 * @param firstIndex
	 * @param maxCount
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount);
	
	/**
	 * 查找出全部实体的记录数， 不进行分页显示
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> QueryResult<T> getScorllData(Class<T> entityClass);
}

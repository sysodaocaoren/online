package cn.soft.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.soft.common.QueryResult;
import cn.soft.dao.Dao;

@Transactional
public abstract class DaoImpl implements Dao {
	@Resource protected SessionFactory sessionFactory;
	
	public void save(Object entity) {
		sessionFactory.getCurrentSession().save(entity);
	}

	public void update(Object entity) {
		sessionFactory.getCurrentSession().merge(entity);
	}

	
	public <T> void delete(Class<T> entityClass,Object entityid) {
		deletes(entityClass,new Object[]{entityid});
	}

	
	public <T> void deletes(Class<T> entityClass,Object[] entityid) {
		for(Object id: entityid)
		{
			sessionFactory.getCurrentSession().delete(find(entityClass,id));
		}
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> T find(Class<T> entityClass, Object entityId) {
		return (T) sessionFactory.getCurrentSession().get(entityClass, (Serializable)entityId);

	}
	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount,String orderby)
	{
		String hql = "from "+entityClass.getSimpleName()+" as o "+orderby;
		QueryResult<T> qr = new QueryResult<T>();
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		if(firstIndex!=-1&&maxCount!=-1)
			query.setFirstResult(firstIndex).setMaxResults(maxCount);
		String hql1 = "select count(*) from "+entityClass.getSimpleName()+" as o "+orderby;
		qr.setResultSet(query.list());
		query = sessionFactory.getCurrentSession().createQuery(hql1);
		qr.setTotalRecord((Long)query.uniqueResult());
		return qr;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> QueryResult<T> getScorllData(Class<T> entityClass,int firstIndex ,int maxCount)
	{
		return getScorllData(entityClass,firstIndex,maxCount,"");
	}
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public <T> QueryResult<T> getScorllData(Class<T> entityClass)
	{
		return this.getScorllData(entityClass, -1, -1);
	}
}

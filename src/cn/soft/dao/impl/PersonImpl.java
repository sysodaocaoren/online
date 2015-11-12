package cn.soft.dao.impl;


import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.soft.dao.PersonDao;
import cn.soft.vo.Message;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

@Service 
public class PersonImpl extends DaoImpl implements PersonDao {
	
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Person getPasswd(Person person) {
		String hql = "from Person as p where p.user_name=:name and p.user_mail=:mail and p.user_realname=:realname and p.user_sex=:sex";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("name", person.getUser_name());
		query.setString("mail", person.getUser_mail());
		query.setString("realname", person.getUser_realname());
		query.setString("sex", person.getUser_sex());
		Person p = (Person)query.uniqueResult();
		return p;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Person userLogin(Person p) {
		String hql="from Person as p where p.user_name=:name and p.user_pass=:pass and p.user_flag=:flag";
		Query query = super.sessionFactory.getCurrentSession().createQuery(hql);
		query.setString("name", p.getUser_name());
		query.setString("pass", p.getUser_pass());
		query.setString("flag", p.getUser_flag());
		Person person = (Person)query.uniqueResult();	
		return person;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean juegeUser(String name) {
		String hql = "from Person as p where p.user_name='"+name+"'";
		Person person = (Person)super.sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		if(person!=null)
			return false;
		else
			return true;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Set<Orders> find(int id) {
		Person person = (Person)super.sessionFactory.getCurrentSession().get(Person.class, id);
		Hibernate.initialize(person.getOrders());
		return person.getOrders();

	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public UserInfo findUser(int id) {
		Person person = (Person)super.sessionFactory.getCurrentSession().get(Person.class, id);
		Hibernate.initialize(person.getUserinfo());
		return person.getUserinfo();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Set<Message> findMessagByID(int id) {
		Person person = (Person)super.sessionFactory.getCurrentSession().get(Person.class, id);
		Hibernate.initialize(person.getMessage());
		return person.getMessage();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Person findByName(String name) {
		String hql = "from Person as p where p.user_name='"+name+"'";
		Person person = (Person)super.sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
		return person;
	}

}

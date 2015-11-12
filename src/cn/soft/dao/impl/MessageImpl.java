package cn.soft.dao.impl;

import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.soft.dao.MessageDao;
import cn.soft.vo.Message;

@Service
public class MessageImpl extends DaoImpl implements MessageDao {

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Message findById(int id) {
		Message message = (Message)super.sessionFactory.getCurrentSession().get(Message.class, id);
		Hibernate.initialize(message.getUser());
		return message;
	}

}

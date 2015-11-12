package cn.soft.dao.impl;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.soft.dao.OrderDao;
import cn.soft.vo.MyMenu;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
@Service
public class OrderImpl extends DaoImpl implements OrderDao {

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public MyMenu find(int id) {
		Orders orders = (Orders)super.sessionFactory.getCurrentSession().get(Orders.class, id);
		Hibernate.initialize(orders.getMenu());
		return orders.getMenu();
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Person findUserByID(int id) {
		Orders orders = (Orders)super.sessionFactory.getCurrentSession().get(Orders.class, id);
		Hibernate.initialize(orders.getUser());
		return orders.getUser();
	}

	public String getId() {
		String newId="";
		String sql="select os.order_id from Orders as os order by os.order_id desc ";
		List<Object> lis = super.sessionFactory.getCurrentSession().createQuery(sql).list();
		if(lis.size()==0){
			return "0";
		}else{
			String oldId=lis.get(0).toString();
			newId=Integer.toString(Integer.parseInt(oldId)+1);
			return newId;
		}
		
	}

}

package cn.soft.dao.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.soft.dao.MenuDao;
import cn.soft.vo.MyMenu;
@Service
public class MenuImpl extends DaoImpl implements MenuDao {

	
	@SuppressWarnings("unchecked")
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<MyMenu> searchMenu(String name) {
		String hql = "from MyMenu as mm where mm.menu_name like '%"+name+"%'";
		 return super.sessionFactory.getCurrentSession().createQuery(hql).list();
		
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public MyMenu findByName(String name) {
		String hql = "from MyMenu as mm where mm.menu_name='"+name+"'";
		return (MyMenu)super.sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

}

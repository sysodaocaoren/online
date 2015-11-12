package cn.soft.dao;

import cn.soft.vo.MyMenu;
import cn.soft.vo.Person;

public interface OrderDao extends Dao {
 
	/**
	 * 通过一对一的关系映射查找到对应的MyMenu对象
	 * @param id Orders的id值
	 * @return
	 */
	public MyMenu find(int id);
	
	/**
	 * 通过多对一的关系映射找到对应的Person对象
	 * @param id Orders的id值
	 * @return
	 */
	public Person findUserByID(int id);

	public String getId();
}

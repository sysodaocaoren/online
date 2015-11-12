package cn.soft.dao;

import cn.soft.vo.MyMenu;
import cn.soft.vo.Person;

public interface OrderDao extends Dao {
 
	/**
	 * ͨ��һ��һ�Ĺ�ϵӳ����ҵ���Ӧ��MyMenu����
	 * @param id Orders��idֵ
	 * @return
	 */
	public MyMenu find(int id);
	
	/**
	 * ͨ�����һ�Ĺ�ϵӳ���ҵ���Ӧ��Person����
	 * @param id Orders��idֵ
	 * @return
	 */
	public Person findUserByID(int id);

	public String getId();
}

package cn.soft.dao;

import java.util.List;

import cn.soft.vo.MyMenu;

public interface MenuDao extends Dao {

	/**
	 * ���ݴ������Ϣ����ģ����ѯ
	 * @param name
	 * @return ����һ���˵��б�
	 */
	public List<MyMenu> searchMenu(String name);
	
	/**
	 * ���ݲ˵������������в���
	 * @param name
	 * @return
	 */
	public MyMenu findByName(String name);
}

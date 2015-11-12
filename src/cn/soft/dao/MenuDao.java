package cn.soft.dao;

import java.util.List;

import cn.soft.vo.MyMenu;

public interface MenuDao extends Dao {

	/**
	 * 根据传入的信息进行模糊查询
	 * @param name
	 * @return 返回一个菜单列表
	 */
	public List<MyMenu> searchMenu(String name);
	
	/**
	 * 根据菜单的名称来进行查找
	 * @param name
	 * @return
	 */
	public MyMenu findByName(String name);
}

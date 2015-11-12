package cn.soft.dao;

import cn.soft.vo.Message;

public interface MessageDao extends Dao {

	/**
	 * 根据id查找Message对象，同时这里声明懒加载，可以得到Person对象
	 * @param id
	 * @return
	 */
	public Message findById(int id);
	
	
}

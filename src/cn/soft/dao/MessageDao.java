package cn.soft.dao;

import cn.soft.vo.Message;

public interface MessageDao extends Dao {

	/**
	 * ����id����Message����ͬʱ�������������أ����Եõ�Person����
	 * @param id
	 * @return
	 */
	public Message findById(int id);
	
	
}

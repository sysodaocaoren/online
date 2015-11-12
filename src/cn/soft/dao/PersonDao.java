package cn.soft.dao;


import java.util.Set;

import cn.soft.vo.Message;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

public interface PersonDao extends Dao {

	/**
	 * 根据传入的person的部分信息进行用户密码的查找
	 * @return
	 */
	public Person getPasswd(Person person);
	
	/**
	 * 根据用户传入的person部分信息查找用户是否存在
	 * @param p
	 * @return
	 */
	public Person userLogin(Person p);
	
	/**
	 * 根据name判断用户名是否存在
	 * @param name
	 * @return 如果存在则返回false 不存在返回true
	 */
	public boolean juegeUser(String name);
	
	/**
	 * 通过一对多的关系映射得到多的一端Orders的集合对象
	 * @param id Person的id值
	 * @return
	 */
	public Set<Orders> find(int id);
	
	
	/**
	 * 根据person的id值来查找Userinfo对象
	 * @param id
	 * @return
	 */
	public UserInfo findUser(int id);
	
	
	/**
	 * 通过一对多的关系映射得到多的一端Message的集合对象
	 * @param id Person的id值
	 * @return
	 */
	public Set<Message> findMessagByID(int id);
	
	/**
	 * 根据用户的姓名查找出用户的信息
	 * @param name
	 * @return
	 */
	public Person findByName(String name);
}

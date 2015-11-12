package cn.soft.dao;


import java.util.Set;

import cn.soft.vo.Message;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

public interface PersonDao extends Dao {

	/**
	 * ���ݴ����person�Ĳ�����Ϣ�����û�����Ĳ���
	 * @return
	 */
	public Person getPasswd(Person person);
	
	/**
	 * �����û������person������Ϣ�����û��Ƿ����
	 * @param p
	 * @return
	 */
	public Person userLogin(Person p);
	
	/**
	 * ����name�ж��û����Ƿ����
	 * @param name
	 * @return ��������򷵻�false �����ڷ���true
	 */
	public boolean juegeUser(String name);
	
	/**
	 * ͨ��һ�Զ�Ĺ�ϵӳ��õ����һ��Orders�ļ��϶���
	 * @param id Person��idֵ
	 * @return
	 */
	public Set<Orders> find(int id);
	
	
	/**
	 * ����person��idֵ������Userinfo����
	 * @param id
	 * @return
	 */
	public UserInfo findUser(int id);
	
	
	/**
	 * ͨ��һ�Զ�Ĺ�ϵӳ��õ����һ��Message�ļ��϶���
	 * @param id Person��idֵ
	 * @return
	 */
	public Set<Message> findMessagByID(int id);
	
	/**
	 * �����û����������ҳ��û�����Ϣ
	 * @param name
	 * @return
	 */
	public Person findByName(String name);
}

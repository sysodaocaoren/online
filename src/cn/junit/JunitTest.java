package cn.junit;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.soft.common.QueryResult;
import cn.soft.dao.MenuDao;
import cn.soft.dao.MessageDao;
import cn.soft.dao.OrderDao;
import cn.soft.dao.PersonDao;
import cn.soft.vo.Message;
import cn.soft.vo.MyMenu;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

public class JunitTest {
	@Resource  private static MenuDao menuDao;
	@Resource private static PersonDao personDao;
	@Resource private static OrderDao orderDao;
	@Resource private static MessageDao messageDao;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		menuDao =(MenuDao) ac.getBean("menuImpl");
		personDao = (PersonDao)ac.getBean("personImpl");
		orderDao = (OrderDao)ac.getBean("orderImpl");
		messageDao = (MessageDao)ac.getBean("messageImpl");
	}

	@Test
	public void testSave()
	{
		Person person = new Person();
		person.setUser_name("小明");
		person.setUser_pass("123");
		person.setUser_flag("0");
		person.setUser_realname("李小明");
		person.setUser_sex("男");
		menuDao.save(person);
	}
	
	@Test
	public void testFind()
	{
		List<MyMenu> list = menuDao.searchMenu("鱼");
		for(MyMenu mm : list)
		{
			System.out.println(mm.getMenu_name());
		}
	}
	
	
	@Test
	public void testUserInfo()
	{
		UserInfo ui = personDao.findUser(92);
		System.out.println(ui.getAddress());
	}
	
	@Test
	public void testdelete()
	{
		Set<Orders> o =  personDao.find(89);
		for(Orders od : o)
		{
			System.out.println(od.getOther_notice());
		}
	}
	
	@Test
	public void testlist()
	{
		Person p = personDao.find(Person.class, 93);
		MyMenu mm = menuDao.find(MyMenu.class, 104);
		Orders order = new Orders();
		order.setMenu(mm);
		order.setOrder_notice("微辣");
		order.setUser(p);
		order.setOrder_num(2);
		order.setOther_notice("是否健康");
		orderDao.save(order);
	}
	
	@SuppressWarnings("static-access")
	@Test
	public void testTime()
	{
		 Calendar rightNow = Calendar.getInstance();		 
		 SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		 String [] year = new String[7];
		 for(int i=6;i>=0;i--)
		 {
			 String sysDatetime = fmt.format(rightNow.getTime()); 
			 year[i]=sysDatetime;
			 rightNow.add(rightNow.DAY_OF_MONTH, -1);
		 }
		 QueryResult<Orders> qr = orderDao.getScorllData(Orders.class);
		 double [] total = new double[7];
		 for(int i=0;i<year.length;i++)
		 {
			 if(i<year.length-1)
			 {
				 for(Orders o : qr.getResultSet())
				 {
					 if(o.getOrder_date().compareTo(year[i])>0&&o.getOrder_date().compareTo(year[i+1])<0)
					 {
						 MyMenu mm = orderDao.find(o.getOrder_id());
						 total[i]+= o.getOrder_num()*mm.getMenu_price();
					 }
				 }
			 }
			 else
			 {
				 for(Orders o : qr.getResultSet())
				 {
					 if(year[i].compareTo(o.getOrder_date())<0)
					 {
						 MyMenu mm = orderDao.find(o.getOrder_id());
						 total[i]+= o.getOrder_num()*mm.getMenu_price();
					 }
				 } 
			 }
			 
		 }
		 
	}
	
	@Test
	public void testMessage()
	{
		Person p = personDao.find(Person.class, 93);
		Message m = new Message();
		m.setUser(p);
		m.setContent("hello 大家好，这是我的另外一条留言");
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd hh：mm：ss");
		String dates = fmt.format(rightNow.getTime());
		m.setDates(dates);
		m.setSubject("other留言");
		messageDao.save(m);
	}
	
	@Test
	public void testMg()
	{
		String name = "鸡肉";
		String orderby = "where o.menu_name like '%";//+n+"%' order by o.menu_id asc";
		for(int i=0;i<name.length()-1;i++)
		{
			orderby+=name.charAt(i)+"%' or o.menu_name like '%";
		}
		orderby +=name.charAt(name.length()-1)+"%'";
		System.out.println(orderby);
	}
	
	@Test
	public void testCompar()
	{
		System.out.println("2011-07-11".compareTo("2011-07-10 10"));
	}
}

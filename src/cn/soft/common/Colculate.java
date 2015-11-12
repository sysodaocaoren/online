package cn.soft.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import cn.soft.dao.OrderDao;
import cn.soft.dao.PersonDao;
import cn.soft.vo.MenuList;
import cn.soft.vo.MyMenu;
import cn.soft.vo.Orders;

@Service	//将这个类作为服务层对象交给spring容器管理
public class Colculate {
	
	//通过spring容器注入下面的对象，不用再通过new来实例化他们
	@Resource private PersonDao personDao;
	@Resource private OrderDao orderDao;
	
	
	/**
	 * 此方法是用来计算用户已购买的商品的数量、总价等信息，并将这些信息放入到session范围内供页面获取
	 * @param ac	用来获取请求参数
	 * @return
	 */
	public boolean colculate(HttpServletRequest ac)
	{
		//获取用户的id值
		int id = (Integer)ac.getSession().getAttribute("user_id");		
		List<MenuList> list = new ArrayList<MenuList>();
		//根据用户的id再结合用户与订单的一对多的关系映射，得到多的一端的对象集合
		Set<Orders> order = personDao.find(id);		
		//此处的i是用来设置在页面显示时显示menuId的值
		int i=1;		
		//订购的菜单的总的个数
		int totalCount=0;	
		//总价钱
		double total = 0.0;	
		//用来标识购物车中是否有商品，如果有则返回true，没有则返回false
		boolean flag=false;	
		//遍历得到的set集合
		for(Orders o : order)			
		{
			/*此处的if语句是用来判断哪些订单是用户刚刚下的而不是以前的订单记录。
			在这里需要把购买时的订单时间和用户登录时的订单时间进行比较，如果购买的时间大于用户的登录时间，
			则表明此菜单是用户刚刚下的，而不是以前的，这两个时间都由程序自动记录。
			*/
			if(o.getOrder_date().compareTo((String)ac.getSession().getAttribute("loginTime"))>0)
			{							
				//利用订单和菜单一对一的映射关系查找出菜单对象
				MyMenu myMenu = orderDao.find(o.getOrder_id());
				//把菜得相关信息封装到MenuList类中						
				MenuList mlist = new MenuList();	
				mlist.setOrderID(o.getOrder_id());
				mlist.setName(myMenu.getMenu_name());
				mlist.setMenuId(i++);
				mlist.setCount(o.getOrder_num());
				mlist.setPrice(myMenu.getMenu_price());
				mlist.setTotal(o.getOrder_num()*myMenu.getMenu_price());
				mlist.setContent(myMenu.getMenu_content());
				totalCount+=o.getOrder_num();
				total+=o.getOrder_num()*myMenu.getMenu_price();		
				//把每一个菜单的信息放入list集合对象中，为以后页面中遍历list集合显示菜单列表使用
				list.add(mlist);		
				flag=true;
			}		
		}
		//将计算出的对象放入到session对象中
		ac.getSession().setAttribute("list", list);
		ac.getSession().setAttribute("totalCount", totalCount);
		//tatal的设置其实已经在MenuList对象中了，在此处又设置一遍是为了供首页的最上方的购车信息使用
		ac.getSession().setAttribute("totalprice", total);		
		//counts的设置也是为首页的购物车显示信息使用
		ac.getSession().setAttribute("counts", i-1);			
		return flag;
	}
	
	
	
}

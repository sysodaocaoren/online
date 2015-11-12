package cn.soft.action;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.soft.common.Colculate;
import cn.soft.common.NextPage;
import cn.soft.common.QueryResult;
import cn.soft.dao.MenuDao;
import cn.soft.dao.OrderDao;
import cn.soft.dao.PersonDao;
import cn.soft.vo.MenuList;
import cn.soft.vo.MyMenu;
import cn.soft.vo.Orders;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class OrderAction {
	@Resource private MenuDao menuDao;
	@Resource private PersonDao personDao;
	@Resource private OrderDao orderDao;
	@Resource private Colculate colculate;
	@Resource private NextPage nextPage;
	private int page=1;
	private Orders orders;
	private String userID;
	private String menuID;
	

	/**
	 * 添加菜单信息  首先查看用户是否登录  如果没有返回到login视图进行登录
	 * @return 如果登录对所添加的菜单进行保存，然后计算订单的数量和总价
	 */
	public String addMenu()
	{
		ActionContext ac = ActionContext.getContext();
		String flag = (String)ac.getSession().get("flag");
		if(ac.getSession().get("user_name")!=null) //判断用户是否登录
		{
			int userid= Integer.parseInt(userID);
			Person p = personDao.find(Person.class, userid);
			int menuid = Integer.parseInt(menuID);	
			MyMenu m = menuDao.find(MyMenu.class, menuid);		
			save(p,m);							//保存选购的菜单信息
			HttpServletRequest request = ServletActionContext.getRequest();	
			colculate.colculate(request);			//调用Colculate类计算订单数量和总价
			if(flag==null||flag.equals(""))
				return "addSuccess";				//添加成功后返回的结果视图为首页
			else
				return "qindan";
		}
		else
		{
			return "login";
		}
		
	}
	
	/**
	 * 删除用户订单中的菜单项，根据订单号orderID来删除
	 * @return
	 */
	public String deleteMenu()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int orderID = Integer.parseInt(request.getParameter("orderID"));
		orderDao.delete(Orders.class, orderID);
		return "deleteSuccess";
	}
	
	/**
	 * 用来保存用户的订单信息
	 * @param p
	 * @param m
	 */
	public void save(Person p,MyMenu m)
	{
		String id=orderDao.getId();
		orders.setOrder_id(Integer.parseInt(id));
		orders.setUser(p);
		orders.setMenu(m);
		orders.setStates("1");
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
		String sysDatetime = fmt.format(rightNow.getTime()); 
		orders.setOrder_date(sysDatetime);		//设置购买菜单的时间  用来和用户登录时的时间进行比较 
		orderDao.save(orders);
	}
	
	
	/**
	 * 根据传入参数的不同，为管理员显示出已经付清的订单或未付清的订单信息
	 * @return 调用common()方法将所有与订单相关的信息封装起来，并放入list集合中
	 */
	public String accounted()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();
		int fg = Integer.parseInt(ac.getParameter("fg"));		
		String orderby = "";
		if(fg==0)
			orderby = "where o.states='0' order by o.order_id";
		else if(fg==1)
			orderby = "where o.states='1' order by o.order_id";		
		List<MenuList> list = common(orderby);
		ac.getSession().setAttribute("infolist", list);
		ac.getSession().setAttribute("page", page);
		ac.getSession().setAttribute("fg", fg);
		return "infolist";
	}
	
	
	/**
	 * 根据搜索文本框中的姓名进行搜索，得到其所有的订单信息
	 * @return
	 */
	public String searchOrder()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();
		String name = ac.getParameter("name");
		if(name!=null&&!"".equals(name)){
			String n = "";
			try {
				n = new String(name.getBytes("iso-8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			if(!personDao.juegeUser(n))
			{
				Person p = personDao.findByName(n);
				UserInfo userinfo = personDao.findUser(p.getUser_id());
				Set<Orders> set = personDao.find(p.getUser_id());
				List<MenuList> list = new ArrayList<MenuList>();
				int i=1;
				for(Orders o : set)
				{
					MenuList ml = new MenuList();
					ml.setMenuId(i++);
					MyMenu menu = orderDao.find(o.getOrder_id());
					ml.setOrderID(o.getOrder_id());
					ml.setName(menu.getMenu_name());
					ml.setCount(o.getOrder_num());
					ml.setPrice(menu.getMenu_price());
					ml.setState(o.getStates());
					ml.setTotal(o.getOrder_num()*menu.getMenu_price());
					ml.setUserID(p.getUser_id());
					ml.setUsername(p.getUser_name());
					if(userinfo!=null)
					{
						ml.setUserTel(userinfo.getMovPho());
						ml.setSendTime(userinfo.getSendDate());
					}
					list.add(ml);
				}
				ac.getSession().setAttribute("searchlist", list);
				ac.getSession().setAttribute("name", n);
			}
			else{
				String message = "该用户不存在";
				ac.getSession().setAttribute("message", message);
			}
		}
		return "searched";
	}
	
	
	/**
	 * 一个用来封装订单相关信息的公共方法
	 * @param orderby	为查询的条件
	 * @return
	 */
	public List<MenuList> common(String orderby)
	{
		List<MenuList> list = new ArrayList<MenuList>();
		QueryResult<Orders> qr = nextPage.viewList(orderDao, page, 10, Orders.class, orderby);
		int i=1;
		for(Orders o : qr.getResultSet())
		{
			MenuList ml = new MenuList();
			ml.setMenuId(i++);
			MyMenu menu = orderDao.find(o.getOrder_id());
			ml.setOrderID(o.getOrder_id());
			ml.setName(menu.getMenu_name());
			ml.setCount(o.getOrder_num());
			ml.setPrice(menu.getMenu_price());
			ml.setTotal(o.getOrder_num()*menu.getMenu_price());
			Person p = orderDao.findUserByID(o.getOrder_id());
			ml.setUserID(p.getUser_id());
			ml.setUsername(p.getUser_name());
			UserInfo ui = personDao.findUser(p.getUser_id());
			if(ui!=null)
			{
				ml.setUserTel(ui.getMovPho());
				ml.setSendTime(ui.getSendDate());
			}
			list.add(ml);
		}
		return list;
	}
	
	/**
	 * 确认订单的审查，执行该方法会将订单的状态由1置为0，即由未付状态变为已付状态
	 * @return
	 */
	public String submitOrder()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();
		int id = Integer.parseInt(ac.getParameter("orderID"));
		String sh = ac.getParameter("search");		//这里的sh只是一个标志
		Orders order =orderDao.find(Orders.class, id);
		order.setStates("0");
		orderDao.update(order);
		if(sh==null||sh.equals(""))				//如果它为空则仅仅执行审查然后跳转到update视图
			return "update";
		else
			return "searched";					//不为空则跳转到searched视图
	}
	
	/**
	 * 根据用户在订单查询的页面中键入的用户名进行订单的查询，将查询到的用户的订单放入到集合对象中供页面遍历使用
	 * @return
	 */
	public String searchUserOrder()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		if(username!=null&&!"".equals(username)){
			String flag = request.getParameter("flag");
			String message = "";
			Person p = personDao.findByName(username);
			Set<Orders> set = personDao.find(p.getUser_id());
			if(set.size()<=0)
			{
				message = "没有你的订单记录！";
				request.getSession().setAttribute("message", message);
			}
			else
			{
				List<MenuList> list = new ArrayList<MenuList>();
				if(flag!=null)
				{				
					Calendar rightNow = Calendar.getInstance();
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
					String now = fmt.format(rightNow.getTime());
					for(Orders o : set)
					{					
						if(o.getOrder_date().compareTo(now)>0)
						{
							addMenuList(list, o);
						}
					}
					if(list.size()<=0)
					{
						message= "今天没有你的订单信息！";
						request.getSession().setAttribute("message", message);
					}
					else
					{
						message = "你今天的订单记录为：";
						request.getSession().setAttribute("message", message);
						request.getSession().setAttribute("list", list);
					}
					
				}
				else
				{
					for(Orders o : set)		//遍历所用的订单记录
					{
						addMenuList(list, o);
					}				
					message = "你所有的订单记录为：";
					request.getSession().setAttribute("message", message);
					request.getSession().setAttribute("list", list);
				}
				
			}
		}
		
		return "hadFind";
	}

	/**
	 * 抽取出来的一个方法，用来将信息封装到一个MenuList对象中
	 * @param list
	 * @param i
	 * @param o
	 */
	private void addMenuList(List<MenuList> list, Orders o) {
		MenuList ml = new MenuList();
		MyMenu menu = orderDao.find(o.getOrder_id());
		ml.setName(menu.getMenu_name());
		ml.setCount(o.getOrder_num());
		ml.setPrice(menu.getMenu_price());
		ml.setTotal(o.getOrder_num()*menu.getMenu_price());
		ml.setSendTime(o.getOrder_date());
		ml.setState(o.getStates());
		list.add(ml);
	}
	
	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getMenuID() {
		return menuID;
	}

	public void setMenuID(String menuID) {
		this.menuID = menuID;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}

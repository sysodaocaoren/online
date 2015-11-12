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
	 * ��Ӳ˵���Ϣ  ���Ȳ鿴�û��Ƿ��¼  ���û�з��ص�login��ͼ���е�¼
	 * @return �����¼������ӵĲ˵����б��棬Ȼ����㶩�����������ܼ�
	 */
	public String addMenu()
	{
		ActionContext ac = ActionContext.getContext();
		String flag = (String)ac.getSession().get("flag");
		if(ac.getSession().get("user_name")!=null) //�ж��û��Ƿ��¼
		{
			int userid= Integer.parseInt(userID);
			Person p = personDao.find(Person.class, userid);
			int menuid = Integer.parseInt(menuID);	
			MyMenu m = menuDao.find(MyMenu.class, menuid);		
			save(p,m);							//����ѡ���Ĳ˵���Ϣ
			HttpServletRequest request = ServletActionContext.getRequest();	
			colculate.colculate(request);			//����Colculate����㶩���������ܼ�
			if(flag==null||flag.equals(""))
				return "addSuccess";				//��ӳɹ��󷵻صĽ����ͼΪ��ҳ
			else
				return "qindan";
		}
		else
		{
			return "login";
		}
		
	}
	
	/**
	 * ɾ���û������еĲ˵�����ݶ�����orderID��ɾ��
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
	 * ���������û��Ķ�����Ϣ
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
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH��mm��ss");
		String sysDatetime = fmt.format(rightNow.getTime()); 
		orders.setOrder_date(sysDatetime);		//���ù���˵���ʱ��  �������û���¼ʱ��ʱ����бȽ� 
		orderDao.save(orders);
	}
	
	
	/**
	 * ���ݴ�������Ĳ�ͬ��Ϊ����Ա��ʾ���Ѿ�����Ķ�����δ����Ķ�����Ϣ
	 * @return ����common()�����������붩����ص���Ϣ��װ������������list������
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
	 * ���������ı����е����������������õ������еĶ�����Ϣ
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
				String message = "���û�������";
				ac.getSession().setAttribute("message", message);
			}
		}
		return "searched";
	}
	
	
	/**
	 * һ��������װ���������Ϣ�Ĺ�������
	 * @param orderby	Ϊ��ѯ������
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
	 * ȷ�϶�������飬ִ�и÷����Ὣ������״̬��1��Ϊ0������δ��״̬��Ϊ�Ѹ�״̬
	 * @return
	 */
	public String submitOrder()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();
		int id = Integer.parseInt(ac.getParameter("orderID"));
		String sh = ac.getParameter("search");		//�����shֻ��һ����־
		Orders order =orderDao.find(Orders.class, id);
		order.setStates("0");
		orderDao.update(order);
		if(sh==null||sh.equals(""))				//�����Ϊ�������ִ�����Ȼ����ת��update��ͼ
			return "update";
		else
			return "searched";					//��Ϊ������ת��searched��ͼ
	}
	
	/**
	 * �����û��ڶ�����ѯ��ҳ���м�����û������ж����Ĳ�ѯ������ѯ�����û��Ķ������뵽���϶����й�ҳ�����ʹ��
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
				message = "û����Ķ�����¼��";
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
						message= "����û����Ķ�����Ϣ��";
						request.getSession().setAttribute("message", message);
					}
					else
					{
						message = "�����Ķ�����¼Ϊ��";
						request.getSession().setAttribute("message", message);
						request.getSession().setAttribute("list", list);
					}
					
				}
				else
				{
					for(Orders o : set)		//�������õĶ�����¼
					{
						addMenuList(list, o);
					}				
					message = "�����еĶ�����¼Ϊ��";
					request.getSession().setAttribute("message", message);
					request.getSession().setAttribute("list", list);
				}
				
			}
		}
		
		return "hadFind";
	}

	/**
	 * ��ȡ������һ����������������Ϣ��װ��һ��MenuList������
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

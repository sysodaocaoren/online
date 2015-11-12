package cn.soft.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.soft.common.NextPage;
import cn.soft.common.QueryResult;
import cn.soft.dao.PersonDao;
import cn.soft.vo.Person;

import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class PersonAction {
	@Resource private PersonDao personDao;
	@Resource private NextPage nextPage;
	private Person person;
	private int page;
		
	/**
	 * 实现用户的注册
	 */
	public String regist()
	{
		//此处判断用户名是否已经存在  如果不存在则进行保存
		if(personDao.juegeUser(person.getUser_name()))		
		{
			personDao.save(person);
			String message = "恭喜你，你已注册成功，点击进入登陆界面<a href='../Book/shipping_Step1.jsp'>登陆</a>后即可进行订购！";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "message";
		}
		else
		{
			String message = "对不起，你注册的用户名已经存在，请重新<a href='../user/register_page.jsp'>注册</a>！";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "fail";
		}
	
	}
	
	/**
	 * 实现用户的登录
	 */
	public String login()
	{
		//根据页面文本框输入的person的部分信息对person对象进行查找
		Person p = personDao.userLogin(person);		
		//如果p不为空则说明此用户存在    为空则说明用户不存在
		if(p!=null)										
		{
			Calendar rightNow = Calendar.getInstance();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
			//此时等到一个系统时间用来作为用户的登录时间  放置在session范围内
			String loginTime = fmt.format(rightNow.getTime());		
			ActionContext ac = ActionContext.getContext();
			//将用户的姓名和id放置到session范围内
			ac.getSession().put("user_name", p.getUser_name());		
			ac.getSession().put("user_id", p.getUser_id());
			//如果flag为0则表示为管理员，1则为普通用户，将标志flag放入session范围内，供页面辨别使用
			ac.getSession().put("user_flag", p.getUser_flag());						
			ac.getSession().put("loginTime", loginTime);
			return "loginSuccess";
		}
		else
		{
			String message = "对不起，用户名或密码错误，请重新<a href='../Book/shipping_Step1.jsp'>登陆</a>";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "fail";
		}
	}
	
	/**
	 * 此方法是用来判断用户是不是管理员，如果是管理员才能进入管理中心
	 * @return
	 */
	public String loginManage()
	{
		ActionContext ac = ActionContext.getContext();
		if(ac.getSession().get("user_name")!=null)
		{
			if(ac.getSession().get("user_flag").equals("0"))
			{
				return "lgmanage";
			}
			else{
				String message = "对不起，您不是管理员，只有管理员才能进入管理中心，<a href='../index.jsp'>返回</a>！";
				ac.getSession().put("message", message);
				return "message";
			}
		}
		else
			return "juegeLogin";		
	}
	
	/**
	 * 查找出所有的用户及管理员信息，将其显示在页面中供管理员管理
	 * @return
	 */
	public String userList()
	{
		ActionContext ac = ActionContext.getContext();
		QueryResult<Person> qr = nextPage.viewList(personDao, page, 10, Person.class, "order by o.user_id");
		ac.getSession().put("userList", qr.getResultSet());
		ac.getSession().put("page", page);
		return "list";
	}
	
	/**
	 * 更新向导视图，根据用户id先找到该用户
	 * 然后跳转到更新页面将用户的信息显示在页面对应的文本框中
	 * @return
	 */
	public String updateUI()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		Person p = personDao.find(Person.class, id);
		request.getSession().setAttribute("person", p);
		return "update";
	}
	
	/**
	 * 实现用户信息的更新
	 */
	public String updateUser()
	{
		Person p = personDao.find(Person.class, person.getUser_id());
		p.setUser_name(person.getUser_name());
		p.setUser_pass(person.getUser_pass());
		p.setUser_realname(person.getUser_realname());
		p.setUser_sex(person.getUser_sex());
		p.setUser_mail(person.getUser_mail());
		p.setUser_flag(person.getUser_flag());
		personDao.update(p);
		return "success";
	}
	
	/**
	 * 找回用户密码
	 */
	public String findPass()
	{
		ActionContext ac = ActionContext.getContext();
		Person p = personDao.getPasswd(person);
		String message = "";
		if(p!=null)
			message = "用户："+p.getUser_name()+" 的密码为"+p.getUser_pass();

		else
			message = "对不起，你所输入的用户不存在，你可以点击<a href='../user/register_page.jsp'>注册</a>";
		ac.getSession().put("message", message);
		return "message";
	}
	
	
	/**
	 * 此方法只是为了返回一个结果视图  用来告诉用户没有登录    让其登录后再进行相关操作
	 * @return
	 */
	public String juegeLogin()
	{
		String message = "您还没有登录，只有<a href='../Book/shipping_Step1.jsp'>登录</a>后才能进行此操作" +
				"<br/><br/>如果您没有注册，请点击<a href='../user/register_page.jsp'>注册</a>";
		ActionContext ac = ActionContext.getContext();
		ac.put("message", message);
		return "fail";
	}
	
	/**
	 * 管理员添加用户或再增加管理员
	 * @return
	 */
	public String manageRegist()
	{
		if(personDao.juegeUser(person.getUser_name()))		//此处判断用户名是否已经存在  如果不存在则进行保存
		{
			personDao.save(person);
			String message = "恭喜你，你已添加成功，<a href='../../test/user_userList.action'>返回用户管理首页</a>！";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			
		}
		else
		{
			String message = "对不起，你注册的用户名已经存在，请重新<a href='addUser.jsp'>添加</a>！";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
		}
		return "manageMessage";
	}
	
	
	/**
	 * 根据id删除一个用户或一个管理员
	 */
	public String deleteUser()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		personDao.delete(Person.class, id);
		return "success";
	}
	
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
}

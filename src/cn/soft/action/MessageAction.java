package cn.soft.action;

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

import cn.soft.common.NextPage;
import cn.soft.common.QueryResult;
import cn.soft.dao.MessageDao;
import cn.soft.dao.PersonDao;
import cn.soft.vo.Message;
import cn.soft.vo.Person;
import cn.soft.vo.ShowMessage;

import com.opensymphony.xwork2.ActionContext;

@Controller	@Scope("prototype")
public class MessageAction {
	private Message message;
	@Resource private NextPage nextPage;
	@Resource private MessageDao messageDao;
	@Resource private PersonDao personDao;
	private int page=1;
	
	/**
	 * 作为一个分页显示的向导，如果用户已处于登录状态，就查询出所有留言记录
	 * @return	返回到一个显示留言列表的视图    如果没有登录则返回的到登录界面视图让用户先登录
	 */
	public String messageUI()
	{
		ActionContext ac = ActionContext.getContext();
		if(ac.getSession().get("user_name")!=null)
		{
			//编写order by子句来决定输出的顺序
			String orderby = "order by o.id desc";	
			//将当前页号放入session范围，供分页链接使用
			ac.getSession().put("page", page);		
			//将查到的相关的留言记录放入session范围，供页面显示使用
			ac.getSession().put("messageList", pageUtil(orderby));	
			//是管理员则跳转到管理留言视图
			if(ac.getSession().get("user_flag").equals("0"))	
				return "managelist";
			else
				//如果不是管理员，则跳转到用户添加留言视图
				return "mglist";					
		}
		else
			//如果用户没有登录，则跳转到此视图提醒用户登录
			return "fail";							
	}

	/**
	 * 保存用户的留言信息
	 * @return
	 */
	public String addMessage()
	{
		ActionContext ac = ActionContext.getContext();
		int id = (Integer)ac.getSession().get("user_id");
		Person p = personDao.find(Person.class, id);
		message.setUser(p);
		Calendar nowTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH：mm：ss");
		String time = sdf.format(nowTime.getTime());
		//设置留言的时间
		message.setDates(time);				
		messageDao.save(message);
		return "success";
	}
	
	/**
	 * 用来将每个用户的留言记录全部查出来
	 * @return 如果用户有留言则返回到show视图   ， 如果没有留言记录则返回到message视图用来显示提示信息
	 */
	public String showOneself()
	{
		ActionContext ac = ActionContext.getContext();
		int id = (Integer)ac.getSession().get("user_id");
		Set<Message> set = personDao.findMessagByID(id);
		if(set.size()>0)
		{
			ac.getSession().put("oneselfList", set);
			return "show";
		}
		else
		{
			String message = "现在还没有你的留言记录,返回";
			ac.getSession().put("message", message);
			return "message";
		}
			
		
	}
	
	/**
	 * 该方法是调用分页查询方法，根据出入参数orderby的需求查找相关记录
	 * @param orderby 符合hql语言
	 * @return  返回一个集合类型
	 */
	public List<ShowMessage> pageUtil(String orderby)
	{
		//创建一个list集合用来存放留言信息类ShowMessage
		List<ShowMessage> list = new ArrayList<ShowMessage>();	
		//调用分页查询方法得到查询结果集
		QueryResult<Message> qr = nextPage.viewList(messageDao, page,6, Message.class, orderby);	
		//遍历结果集得到每一条message记录
		for(Message m : qr.getResultSet())				
		{
			//创建一个ShowMessage对象用来存放留言信息
			ShowMessage sm = new ShowMessage();			
			//根据一条留言记录的ID值来查询Message，这条记录上面已经查过了 ，但在这里并不是多余的
			Message mm = messageDao.findById(m.getId());	
			//因为还需要用户的姓名，所以要通过findById()这个方法在查询一次，这方法会设置懒加载得到Person对象
			sm.setUsername(mm.getUser().getUser_name());	
			sm.setMessageID(m.getId());
			sm.setContent(m.getContent());
			sm.setDate(m.getDates());
			sm.setSubject(m.getSubject());
			list.add(sm);
		}
		return list;
	}
	
	/**
	 * 此方法是管理员用来删除所选择的留言记录，在页面中所勾选的复选框对应的留言就都会删除
	 * @return
	 */
	public String deleteMessage()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] mg = request.getParameterValues("messages");		//获取复选框已选中复选框的值，其值就是对应的留言记录的id值
		for(int i=0;i<mg.length;i++)				//遍历所有选中的值
		{
			int id = Integer.parseInt(mg[i]);
			messageDao.delete(Message.class, id);		//调用delete()方法删除id值所对应的记录
		}
		return "success";				//结果返回到success视图，此视图将会重定向的一个查询出所有留言记录的action
	}
	
	
	/**
	 * 此方法是用来获取一条留言记录，并将其封装到ShowMessage类中供页面显示
	 * @return  返回到oneMessage视图，此视图对应一个显示一条留言的页面
	 */
	public String showOneMessage()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("messageID"));
		Message mg = messageDao.findById(id);
		ShowMessage sm = new ShowMessage();
		sm.setUsername(mg.getUser().getUser_name());
		sm.setContent(mg.getContent());
		sm.setDate(mg.getDates());
		sm.setSubject(mg.getSubject());
		request.getSession().setAttribute("oneMg", sm);
		return "oneMessage";
	}
	
	
	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}
	
	
}

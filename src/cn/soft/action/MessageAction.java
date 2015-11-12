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
	 * ��Ϊһ����ҳ��ʾ���򵼣�����û��Ѵ��ڵ�¼״̬���Ͳ�ѯ���������Լ�¼
	 * @return	���ص�һ����ʾ�����б����ͼ    ���û�е�¼�򷵻صĵ���¼������ͼ���û��ȵ�¼
	 */
	public String messageUI()
	{
		ActionContext ac = ActionContext.getContext();
		if(ac.getSession().get("user_name")!=null)
		{
			//��дorder by�Ӿ������������˳��
			String orderby = "order by o.id desc";	
			//����ǰҳ�ŷ���session��Χ������ҳ����ʹ��
			ac.getSession().put("page", page);		
			//���鵽����ص����Լ�¼����session��Χ����ҳ����ʾʹ��
			ac.getSession().put("messageList", pageUtil(orderby));	
			//�ǹ���Ա����ת������������ͼ
			if(ac.getSession().get("user_flag").equals("0"))	
				return "managelist";
			else
				//������ǹ���Ա������ת���û����������ͼ
				return "mglist";					
		}
		else
			//����û�û�е�¼������ת������ͼ�����û���¼
			return "fail";							
	}

	/**
	 * �����û���������Ϣ
	 * @return
	 */
	public String addMessage()
	{
		ActionContext ac = ActionContext.getContext();
		int id = (Integer)ac.getSession().get("user_id");
		Person p = personDao.find(Person.class, id);
		message.setUser(p);
		Calendar nowTime = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH��mm��ss");
		String time = sdf.format(nowTime.getTime());
		//�������Ե�ʱ��
		message.setDates(time);				
		messageDao.save(message);
		return "success";
	}
	
	/**
	 * ������ÿ���û������Լ�¼ȫ�������
	 * @return ����û��������򷵻ص�show��ͼ   �� ���û�����Լ�¼�򷵻ص�message��ͼ������ʾ��ʾ��Ϣ
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
			String message = "���ڻ�û��������Լ�¼,����";
			ac.getSession().put("message", message);
			return "message";
		}
			
		
	}
	
	/**
	 * �÷����ǵ��÷�ҳ��ѯ���������ݳ������orderby�����������ؼ�¼
	 * @param orderby ����hql����
	 * @return  ����һ����������
	 */
	public List<ShowMessage> pageUtil(String orderby)
	{
		//����һ��list�����������������Ϣ��ShowMessage
		List<ShowMessage> list = new ArrayList<ShowMessage>();	
		//���÷�ҳ��ѯ�����õ���ѯ�����
		QueryResult<Message> qr = nextPage.viewList(messageDao, page,6, Message.class, orderby);	
		//����������õ�ÿһ��message��¼
		for(Message m : qr.getResultSet())				
		{
			//����һ��ShowMessage�����������������Ϣ
			ShowMessage sm = new ShowMessage();			
			//����һ�����Լ�¼��IDֵ����ѯMessage��������¼�����Ѿ������ ���������ﲢ���Ƕ����
			Message mm = messageDao.findById(m.getId());	
			//��Ϊ����Ҫ�û�������������Ҫͨ��findById()��������ڲ�ѯһ�Σ��ⷽ�������������صõ�Person����
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
	 * �˷����ǹ���Ա����ɾ����ѡ������Լ�¼����ҳ��������ѡ�ĸ�ѡ���Ӧ�����ԾͶ���ɾ��
	 * @return
	 */
	public String deleteMessage()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String[] mg = request.getParameterValues("messages");		//��ȡ��ѡ����ѡ�и�ѡ���ֵ����ֵ���Ƕ�Ӧ�����Լ�¼��idֵ
		for(int i=0;i<mg.length;i++)				//��������ѡ�е�ֵ
		{
			int id = Integer.parseInt(mg[i]);
			messageDao.delete(Message.class, id);		//����delete()����ɾ��idֵ����Ӧ�ļ�¼
		}
		return "success";				//������ص�success��ͼ������ͼ�����ض����һ����ѯ���������Լ�¼��action
	}
	
	
	/**
	 * �˷�����������ȡһ�����Լ�¼���������װ��ShowMessage���й�ҳ����ʾ
	 * @return  ���ص�oneMessage��ͼ������ͼ��Ӧһ����ʾһ�����Ե�ҳ��
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

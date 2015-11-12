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
	 * ʵ���û���ע��
	 */
	public String regist()
	{
		//�˴��ж��û����Ƿ��Ѿ�����  �������������б���
		if(personDao.juegeUser(person.getUser_name()))		
		{
			personDao.save(person);
			String message = "��ϲ�㣬����ע��ɹ�����������½����<a href='../Book/shipping_Step1.jsp'>��½</a>�󼴿ɽ��ж�����";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "message";
		}
		else
		{
			String message = "�Բ�����ע����û����Ѿ����ڣ�������<a href='../user/register_page.jsp'>ע��</a>��";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "fail";
		}
	
	}
	
	/**
	 * ʵ���û��ĵ�¼
	 */
	public String login()
	{
		//����ҳ���ı��������person�Ĳ�����Ϣ��person������в���
		Person p = personDao.userLogin(person);		
		//���p��Ϊ����˵�����û�����    Ϊ����˵���û�������
		if(p!=null)										
		{
			Calendar rightNow = Calendar.getInstance();
			SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH��mm��ss");
			//��ʱ�ȵ�һ��ϵͳʱ��������Ϊ�û��ĵ�¼ʱ��  ������session��Χ��
			String loginTime = fmt.format(rightNow.getTime());		
			ActionContext ac = ActionContext.getContext();
			//���û���������id���õ�session��Χ��
			ac.getSession().put("user_name", p.getUser_name());		
			ac.getSession().put("user_id", p.getUser_id());
			//���flagΪ0���ʾΪ����Ա��1��Ϊ��ͨ�û�������־flag����session��Χ�ڣ���ҳ����ʹ��
			ac.getSession().put("user_flag", p.getUser_flag());						
			ac.getSession().put("loginTime", loginTime);
			return "loginSuccess";
		}
		else
		{
			String message = "�Բ����û������������������<a href='../Book/shipping_Step1.jsp'>��½</a>";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			return "fail";
		}
	}
	
	/**
	 * �˷����������ж��û��ǲ��ǹ���Ա������ǹ���Ա���ܽ����������
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
				String message = "�Բ��������ǹ���Ա��ֻ�й���Ա���ܽ���������ģ�<a href='../index.jsp'>����</a>��";
				ac.getSession().put("message", message);
				return "message";
			}
		}
		else
			return "juegeLogin";		
	}
	
	/**
	 * ���ҳ����е��û�������Ա��Ϣ��������ʾ��ҳ���й�����Ա����
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
	 * ��������ͼ�������û�id���ҵ����û�
	 * Ȼ����ת������ҳ�潫�û�����Ϣ��ʾ��ҳ���Ӧ���ı�����
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
	 * ʵ���û���Ϣ�ĸ���
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
	 * �һ��û�����
	 */
	public String findPass()
	{
		ActionContext ac = ActionContext.getContext();
		Person p = personDao.getPasswd(person);
		String message = "";
		if(p!=null)
			message = "�û���"+p.getUser_name()+" ������Ϊ"+p.getUser_pass();

		else
			message = "�Բ�������������û������ڣ�����Ե��<a href='../user/register_page.jsp'>ע��</a>";
		ac.getSession().put("message", message);
		return "message";
	}
	
	
	/**
	 * �˷���ֻ��Ϊ�˷���һ�������ͼ  ���������û�û�е�¼    �����¼���ٽ�����ز���
	 * @return
	 */
	public String juegeLogin()
	{
		String message = "����û�е�¼��ֻ��<a href='../Book/shipping_Step1.jsp'>��¼</a>����ܽ��д˲���" +
				"<br/><br/>�����û��ע�ᣬ����<a href='../user/register_page.jsp'>ע��</a>";
		ActionContext ac = ActionContext.getContext();
		ac.put("message", message);
		return "fail";
	}
	
	/**
	 * ����Ա����û��������ӹ���Ա
	 * @return
	 */
	public String manageRegist()
	{
		if(personDao.juegeUser(person.getUser_name()))		//�˴��ж��û����Ƿ��Ѿ�����  �������������б���
		{
			personDao.save(person);
			String message = "��ϲ�㣬������ӳɹ���<a href='../../test/user_userList.action'>�����û�������ҳ</a>��";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
			
		}
		else
		{
			String message = "�Բ�����ע����û����Ѿ����ڣ�������<a href='addUser.jsp'>���</a>��";
			ActionContext ac = ActionContext.getContext();
			ac.getSession().put("message", message);
		}
		return "manageMessage";
	}
	
	
	/**
	 * ����idɾ��һ���û���һ������Ա
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

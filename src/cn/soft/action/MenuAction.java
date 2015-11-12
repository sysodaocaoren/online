package cn.soft.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.soft.common.NextPage;
import cn.soft.common.QueryResult;
import cn.soft.dao.MenuDao;
import cn.soft.vo.MyMenu;














import com.opensymphony.xwork2.ActionContext;

@Controller @Scope("prototype")
public class MenuAction {
	@Resource private MenuDao menuDao;
	private String name;		//��������ı����е���Ϣ
	@Resource private NextPage nextPage;
	private static final int BUFFER_SIZE = 16 * 1024;
	private int page=1;
	private MyMenu menu;
	private File file;
	private String fileFileName;
	private String fileContentType;
	

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	/**
	 * ��ѯ��ҳ��ʾ�Ĳ˵���Ϣ
	 * @return show��ͼΪ���ص���ҳ��ͼ
	 */
	public String showMenu()
	{
		String order = "order by o.menu_id";
		QueryResult<MyMenu> qr = menuDao.getScorllData(MyMenu.class, 0, 3,order);
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("list1", qr.getResultSet());
		qr = menuDao.getScorllData(MyMenu.class, 6, 4,order);
		ac.getSession().put("list2", qr.getResultSet());
		qr = menuDao.getScorllData(MyMenu.class, 10, 4,order);
		ac.getSession().put("list3", qr.getResultSet());
		return "show";
	}
	
	/**
	 * ����һ���˵���id��ѯ����˵���ϸ��Ϣ
	 * @return info��ͼΪ���ص�bookCook.jsp����ͼ��ʾ�˵���ϸ��Ϣ
	 */
	public String menuInfo()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("menu_id"));
		MyMenu  myMenu  = menuDao.find(MyMenu.class, id);
		request.setAttribute("myMenu", myMenu);
		return "info";
	}
	
	
	/**
	 * ���������ı������Ϣ����ѯ��������֮��صĲ˵�
	 * @return ���������ϢΪ��  ���ѯ�����ݿ������еĲ˵�
	 */
	public String menuSearch()
	{
		ActionContext ac = ActionContext.getContext();
		String n = "";
		if(name==null||name.equals(""))
		{
			QueryResult<MyMenu> qr = nextPage.viewList(menuDao, page,10, MyMenu.class, "");
			ac.getSession().put("searchList", qr.getResultSet());
			ac.getSession().put("message", "<div align=\"center\"><font  color=\"#F46404\" size='5'><b>���ֶ��Ͳ˵�һ����</b></font></div><br>");
		}
		else
		{
			
			String orderby = "where o.menu_name like '%"+name+"%' ";//+n+"%' order by o.menu_id asc";
			orderby +=" order by o.menu_id asc";
			
			QueryResult<MyMenu> list = nextPage.viewList(menuDao, page,10, MyMenu.class, orderby);
			if(list==null)					//���û�в鵽��֮ƥ�����Ϣ����ô�Ͳ�ѯ�����в˵���Ϣ
			{
				QueryResult<MyMenu> qr = nextPage.viewList(menuDao, page,10, MyMenu.class, "");
				ac.getSession().put("searchList", qr.getResultSet());
				ac.getSession().put("message", "<div align=\"center\"><font  color=\"#F46404\" size='3'>û�в��ҵ���ƥ�����Ϣ<br/><br/></font>" +
						"<font  color=\"#F46404\" size='5'><b>���ֶ��Ͳ˵�һ����</b></font></div><br>");
			}
			else
			{
				ac.getSession().put("searchList", list.getResultSet());
				ac.getSession().put("message", "<div align=\"center\"> <font color=\"#F46404\" size='4'><b>"
												+ n + "</b></font> ������������£�" + "</div>");
			}
		}
		ac.getSession().put("page", page);
		ac.getSession().put("name", n);	//��¼�˵�����ʱ�ı�������ݲ���������ҳ�����ӵĴ���ʹ��
		HttpServletRequest request = ServletActionContext.getRequest();
		String manage = request.getParameter("manage");
		if(manage==null||manage.equals(""))
			return "search";
		else
			return "menulist";
	}
	
	/**
	 * ���ҳ����в˵�������ҳ��ʾ��������Ա����˵�ҳ��ʹ��
	 * @return
	 */
	public String menuList()
	{
		ActionContext ac = ActionContext.getContext();
		QueryResult<MyMenu> qr = nextPage.viewList(menuDao, page,15, MyMenu.class, "order by o.menu_id");
		ac.getSession().put("searchList", qr.getResultSet());
		ac.getSession().put("page", page);
		return "menulist";
	}
	
	/**
	 * ��Ӳ˵�
	 * @return
	 * @throws IOException 
	 */
	public String addMenu() throws IOException
	{	
		String name=file.getName();
		InputStream is=new FileInputStream(this.getFile());
		/*String path=ServletActionContext.getServletContext().getRealPath("/upload");
		System.out.println(path);*/
		File destFile=new File("F:\\����\\online\\WebRoot\\upload\\",this.getFileFileName());
		OutputStream os=new FileOutputStream(destFile);
		byte[] buffer=new byte[4000];
		int length=0;
		while((length=is.read(buffer))>0)
		{
		os.write(buffer, 0, length);
		}
		is.close();
		os.close();
		menu.setPicture_name(this.getFileFileName());
		menuDao.save(menu);
		return "success";
	}
	
	/**
	 * ����idɾ����Ӧ�˵�
	 * @return
	 */
	public String deleteMenu()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("menuID"));
		menuDao.delete(MyMenu.class, id);
		return "success";
	}
	
	/**
	 * ����id�Ȳ����Ӧ�˵�����Ϣ��Ȼ����ת������ҳ��
	 * @return
	 */
	public String updateUI()
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("menuID"));
		MyMenu m  = menuDao.find(MyMenu.class, id);
		request.getSession().setAttribute("menu", m);
		return "update";
	}
	
	/**
	 * ���²˵���Ϣ
	 * @return
	 */
	public String updateMenu()
	{
		MyMenu m  = menuDao.find(MyMenu.class, menu.getMenu_id());
		m.setMenu_name(menu.getMenu_name());
		m.setMenu_price(menu.getMenu_price());
		m.setMenu_content(menu.getMenu_content());
		menuDao.update(m);
		return "success";
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public MyMenu getMenu() {
		return menu;
	}

	public void setMenu(MyMenu menu) {
		this.menu = menu;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	
	
}

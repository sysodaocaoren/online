package cn.soft.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.soft.common.Colculate;



@Controller @Scope("prototype")
public class ListAction {
	@Resource private Colculate colculate;
	
	/**
	 * ��ʾ�Ѿ����빺�ﳵ�Ĳ˵��б�
	 * @return
	 */
	public String menuList()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();	
		boolean flag = colculate.colculate(ac);			//�˷����ǽ����й���Ĳ˵���Ϣ���м��㣬
														//Ȼ�󽫲˵��б���Ϣ�����ۡ��ܼ۵���Ϣ����session��Χ��
		
		if(flag)						//flagΪ��˵������صĲ˵��б���Ϣ��Ȼ�󷵻ص�list��ͼ��
		{								//����ͼΪ�鿴���ﳵ��ͼshipping_Step2.jsp
				return "list";
		}
		else{							//flagΪ��˵�����ﳵ��û����صĲ˵��б���Ϣ
			String message="��ǰû����Ĺ�����Ϣ��";
			ac.getSession().setAttribute("message", message);
			return "noMenu";			//noMenu��ͼ�����ص�noMenu.jsp��ͼ�������ͼ��������ʾ��ʾ��Ϣmessage��
		}

		
	}
	
	
}

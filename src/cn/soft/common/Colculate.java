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

@Service	//���������Ϊ�������󽻸�spring��������
public class Colculate {
	
	//ͨ��spring����ע������Ķ��󣬲�����ͨ��new��ʵ��������
	@Resource private PersonDao personDao;
	@Resource private OrderDao orderDao;
	
	
	/**
	 * �˷��������������û��ѹ������Ʒ���������ܼ۵���Ϣ��������Щ��Ϣ���뵽session��Χ�ڹ�ҳ���ȡ
	 * @param ac	������ȡ�������
	 * @return
	 */
	public boolean colculate(HttpServletRequest ac)
	{
		//��ȡ�û���idֵ
		int id = (Integer)ac.getSession().getAttribute("user_id");		
		List<MenuList> list = new ArrayList<MenuList>();
		//�����û���id�ٽ���û��붩����һ�Զ�Ĺ�ϵӳ�䣬�õ����һ�˵Ķ��󼯺�
		Set<Orders> order = personDao.find(id);		
		//�˴���i������������ҳ����ʾʱ��ʾmenuId��ֵ
		int i=1;		
		//�����Ĳ˵����ܵĸ���
		int totalCount=0;	
		//�ܼ�Ǯ
		double total = 0.0;	
		//������ʶ���ﳵ���Ƿ�����Ʒ��������򷵻�true��û���򷵻�false
		boolean flag=false;	
		//�����õ���set����
		for(Orders o : order)			
		{
			/*�˴���if����������ж���Щ�������û��ո��µĶ�������ǰ�Ķ�����¼��
			��������Ҫ�ѹ���ʱ�Ķ���ʱ����û���¼ʱ�Ķ���ʱ����бȽϣ���������ʱ������û��ĵ�¼ʱ�䣬
			������˲˵����û��ո��µģ���������ǰ�ģ�������ʱ�䶼�ɳ����Զ���¼��
			*/
			if(o.getOrder_date().compareTo((String)ac.getSession().getAttribute("loginTime"))>0)
			{							
				//���ö����Ͳ˵�һ��һ��ӳ���ϵ���ҳ��˵�����
				MyMenu myMenu = orderDao.find(o.getOrder_id());
				//�Ѳ˵������Ϣ��װ��MenuList����						
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
				//��ÿһ���˵�����Ϣ����list���϶����У�Ϊ�Ժ�ҳ���б���list������ʾ�˵��б�ʹ��
				list.add(mlist);		
				flag=true;
			}		
		}
		//��������Ķ�����뵽session������
		ac.getSession().setAttribute("list", list);
		ac.getSession().setAttribute("totalCount", totalCount);
		//tatal��������ʵ�Ѿ���MenuList�������ˣ��ڴ˴�������һ����Ϊ�˹���ҳ�����Ϸ��Ĺ�����Ϣʹ��
		ac.getSession().setAttribute("totalprice", total);		
		//counts������Ҳ��Ϊ��ҳ�Ĺ��ﳵ��ʾ��Ϣʹ��
		ac.getSession().setAttribute("counts", i-1);			
		return flag;
	}
	
	
	
}

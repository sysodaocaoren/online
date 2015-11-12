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
	 * 显示已经加入购物车的菜单列表
	 * @return
	 */
	public String menuList()
	{
		HttpServletRequest ac = ServletActionContext.getRequest();	
		boolean flag = colculate.colculate(ac);			//此方法是将所有购买的菜单信息进行计算，
														//然后将菜单列表信息、单价、总价等信息放入session范围内
		
		if(flag)						//flag为真说明有相关的菜单列表信息，然后返回到list视图，
		{								//此视图为查看购物车视图shipping_Step2.jsp
				return "list";
		}
		else{							//flag为假说明购物车中没有相关的菜单列表信息
			String message="当前没有你的购物信息！";
			ac.getSession().setAttribute("message", message);
			return "noMenu";			//noMenu视图将返回到noMenu.jsp视图，这个视图是用来显示提示信息message的
		}

		
	}
	
	
}

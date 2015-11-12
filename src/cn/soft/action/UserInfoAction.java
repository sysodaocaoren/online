package cn.soft.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import cn.soft.dao.PersonDao;
import cn.soft.dao.UserInfoDao;
import cn.soft.vo.Person;
import cn.soft.vo.UserInfo;

import com.opensymphony.xwork2.ActionContext;

@Controller
public class UserInfoAction {
	@Resource private PersonDao personDao;
	@Resource private UserInfoDao userInfoDao;
	private UserInfo userInfo;
	
	public String saveInfo()
	{
		ActionContext ac = ActionContext.getContext();
		int id = (Integer)ac.getSession().get("user_id");
		if(!judge())
		{
			
			Person p = personDao.find(Person.class, id);
			userInfo.setUser(p);
			userInfoDao.save(userInfo);
		}
		else
		{
			int infoID= personDao.findUser(id).getId();
			UserInfo ui = userInfoDao.find(UserInfo.class, infoID);
			ui.setAddress(userInfo.getAddress());
			ui.setMovPho(userInfo.getMovPho());
			ui.setNotice(userInfo.getNotice());
			ui.setSendDate(userInfo.getSendDate());
			ui.setTel(userInfo.getTel());
			userInfoDao.update(ui);
		}
		return "saveSuccess";
	}
	
	
	public String getInfo()
	{
		judge();
		return "info";
	}
	
	public boolean judge()
	{
		ActionContext ac = ActionContext.getContext();
		int id = (Integer)ac.getSession().get("user_id");
		if(personDao.findUser(id)!=null)
		{
			ac.getSession().put("userinfo", personDao.findUser(id));
			return true;				
		}
		else
			return false;

	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}
	
	
}

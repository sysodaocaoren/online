package cn.soft.common;

import org.springframework.stereotype.Service;

import cn.soft.dao.Dao;

import com.opensymphony.xwork2.ActionContext;

@Service	//把这个类作为服务层对象交给spring容器管理
public class NextPage {

	/**
	 * 这个方法是将分页功能抽取出来，供多处需要分页的地方使用
	 * @param <T>	通过泛型的方式来确定返回结果类型
	 * @param dao	这是一个最顶层的父接口，本项目中的所有接口都继承此接口
	 * 				getScorllData()这个方法是在此接口中定义的，用来进行分页查询使用，所以其他子接口都能使用该方法
	 * 				在这里就是通过下上转型的方式，来匹配各种子接口的分页查询操作
	 * @param page	当前页面的页号
	 * @param entityClass	要查询的实体类，在这里定义为泛型，会和返回结果类型自动匹配
	 * @param orderby	此处并非只能用来排序使用，因为其为字符串，也可以使任何正确的sql查询语句，但要符合此参数的要求
	 * @return
	 */
	public <T> QueryResult<T>  viewList(Dao dao,int page,int maxCount,Class<T> entityClass,String orderby)
	{
		int firstIndex = (page-1)*maxCount;
		int totalCount=0;
		QueryResult<T> qr = dao.getScorllData(entityClass, firstIndex, maxCount, orderby);
		totalCount = (int)qr.getTotalRecord();
		Page p = new Page(maxCount,totalCount);
		ActionContext ac = ActionContext.getContext();
		ac.getSession().put("pageinfo", p);
		return qr;
	}
}

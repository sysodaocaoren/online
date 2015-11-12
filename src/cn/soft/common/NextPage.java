package cn.soft.common;

import org.springframework.stereotype.Service;

import cn.soft.dao.Dao;

import com.opensymphony.xwork2.ActionContext;

@Service	//���������Ϊ�������󽻸�spring��������
public class NextPage {

	/**
	 * ��������ǽ���ҳ���ܳ�ȡ���������ദ��Ҫ��ҳ�ĵط�ʹ��
	 * @param <T>	ͨ�����͵ķ�ʽ��ȷ�����ؽ������
	 * @param dao	����һ�����ĸ��ӿڣ�����Ŀ�е����нӿڶ��̳д˽ӿ�
	 * 				getScorllData()����������ڴ˽ӿ��ж���ģ��������з�ҳ��ѯʹ�ã����������ӽӿڶ���ʹ�ø÷���
	 * 				���������ͨ������ת�͵ķ�ʽ����ƥ������ӽӿڵķ�ҳ��ѯ����
	 * @param page	��ǰҳ���ҳ��
	 * @param entityClass	Ҫ��ѯ��ʵ���࣬�����ﶨ��Ϊ���ͣ���ͷ��ؽ�������Զ�ƥ��
	 * @param orderby	�˴�����ֻ����������ʹ�ã���Ϊ��Ϊ�ַ�����Ҳ����ʹ�κ���ȷ��sql��ѯ��䣬��Ҫ���ϴ˲�����Ҫ��
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

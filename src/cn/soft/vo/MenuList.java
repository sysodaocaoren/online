package cn.soft.vo;

/**
 * �����ʹ������װ��Ϣ��
 * ���û��Ѿ������ÿ���˵Ĳ˵���Ϣ��װ���������Ժ��ҳ����ʾʹ��
 * ��װ����ÿ���˵���Ϣ��������ȫ���˵�����Ϣ
 * @author qing
 *
 */
public class MenuList {

	private int menuId;		//��ʶҳ����ʾ�еĲ˵���ID�����������ݿ�����ʵ�Ĳ˵�ID
	private String name;	//�˵���
	private int count;		//�����ÿ���˵�����
	private double price;	//�˵ĵ���
	private double total;	//ÿ���˵��ܼ�
	private String content;	//�Բ˵�Ҫ��
	private int orderID;	//����˶�Ӧ�Ķ���ID
	private int userID;		//�����˲˵��û�ID
	private String username;	//�����˲˵��û���
	private String userTel;	//�����˲˵��û��绰
	private String sendTime;//�ͻ�ʱ��
	private String state;	//��ʾ�û��Ƿ��Ѹ��嶩����0Ϊ�Ѿ����壬1Ϊδ����
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getUserTel() {
		return userTel;
	}
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	public String getSendTime() {
		return sendTime;
	}
	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}
	public int getOrderID() {
		return orderID;
	}
	public void setOrderID(int orderID) {
		this.orderID = orderID;
	}
	public int getMenuId() {
		return menuId;
	}
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}

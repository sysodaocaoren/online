package cn.soft.vo;

public class Orders {

	private int order_id;
	private int order_num;
	private String order_notice;
	private String other_notice;
	private Person user;
	private MyMenu menu;
	private String states;
	private String order_date;
	
	
	

	public String getOrder_date() {
		return order_date;
	}
	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}
	public String getStates() {
		return states;
	}
	public void setStates(String states) {
		this.states = states;
	}

	public String getOther_notice() {
		return other_notice;
	}
	public void setOther_notice(String other_notice) {
		this.other_notice = other_notice;
	}
	public Person getUser() {
		return user;
	}
	public void setUser(Person user) {
		this.user = user;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getOrder_notice() {
		return order_notice;
	}
	public void setOrder_notice(String order_notice) {
		this.order_notice = order_notice;
	}
	
	public MyMenu getMenu() {
		return menu;
	}
	public void setMenu(MyMenu menu) {
		this.menu = menu;
	}
	
}

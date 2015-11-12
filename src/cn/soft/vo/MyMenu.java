package cn.soft.vo;

public class MyMenu {
	private int menu_id;
	private String menu_name;
	private String menu_content;
	private double menu_price;
	private String picture_name;
	
	public String getPicture_name() {
		return picture_name;
	}
	public void setPicture_name(String picture_name) {
		this.picture_name = picture_name;
	}
	public int getMenu_id() {
		return menu_id;
	}
	public void setMenu_id(int menu_id) {
		this.menu_id = menu_id;
	}
	public String getMenu_name() {
		return menu_name;
	}
	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}
	public String getMenu_content() {
		return menu_content;
	}
	public void setMenu_content(String menu_content) {
		this.menu_content = menu_content;
	}
	public double getMenu_price() {
		return menu_price;
	}
	public void setMenu_price(double menu_price) {
		this.menu_price = menu_price;
	}

}

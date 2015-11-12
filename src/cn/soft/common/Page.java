package cn.soft.common;

public class Page {

	private int startPage=1;
	private int endPage;
	private int totalCount;
	public Page(int maxCount,int totalCount) {
		this.endPage = totalCount%maxCount==0 ? totalCount/maxCount : totalCount/maxCount+1;
		this.totalCount = totalCount;
	}
	public int getStartPage() {
		return startPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	
	
}

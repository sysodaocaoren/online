package cn.soft.common;

import java.util.List;

public class QueryResult<T> {
	private List<T> resultSet;
	private long totalRecord;
	
	public List<T> getResultSet() {
		return resultSet;
	}
	public void setResultSet(List<T> resultSet) {
		this.resultSet = resultSet;
	}
	public long getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
	}
	
}

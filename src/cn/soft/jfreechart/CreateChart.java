package cn.soft.jfreechart;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.springframework.stereotype.Controller;


@Controller
public class CreateChart {
	
	public String showPhoto()
	{
		test();
		return "photo";
	}
	
	@SuppressWarnings("static-access")
	public void test()
	{
		double[][] data = new double[][]{{312.0},{729.5},{304.0},{415.5},{204.0},{254.0},{0}}; 
		String [] rowKeys = new String [7];
		Calendar rightNow = Calendar.getInstance();		 
		SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		 for(int i=6;i>=0;i--)
		 {
			 String sysDatetime = fmt.format(rightNow.getTime()); 
			 rowKeys[i]=sysDatetime;
			 rightNow.add(rightNow.DAY_OF_MONTH, -1);
		 }
		 String[] columKeys = {""};
		 CategoryDataset dataset = DatasetUtilities.createCategoryDataset(rowKeys, columKeys, data);
		 JFreeChart chart = ChartFactory.createBarChart3D("近七天的销售情况", "时间", "销量", dataset, PlotOrientation.VERTICAL, true, true, false);
		
		
		
	}
	
}

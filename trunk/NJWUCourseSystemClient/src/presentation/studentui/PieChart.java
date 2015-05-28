package presentation.studentui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

public class PieChart {

	/**
	 * @param args
	 */
	
	ChartPanel chartpanel;
	
	private static PieDataset creatDataSet(int part,int total){
		DefaultPieDataset dpd=new DefaultPieDataset();
		Comparable<?> 已经获得的学分=""+part;  //在双引号内可以输入你想输入的提示字。
		Comparable<?> 还未获得的学分="" +( total-part) ;
		dpd.setValue(已经获得的学分,part);
		dpd.setValue(还未获得的学分,total-part);
		
		return dpd;
		
	}
	
	
	private ChartPanel getPieChart(PieDataset dpd){
		
		ChartPanel frame;
		JFreeChart jfc= ChartFactory.createPieChart("",dpd,true,true,false);
		jfc.getTitle().setFont(new Font("微软雅黑",Font.CENTER_BASELINE,20));//设置标题字体

		jfc.setBackgroundPaint(Color.gray);
		frame=new ChartPanel (jfc,true);	
		
		return frame;
			
	}
	public ChartPanel getPieChart(int partvalue , int totalvalue){
		//partvalue  要显示的部分
		//红色部分为你partvalue的值，蓝色部分为total中剩下的。
		return this.getPieChart(PieChart.creatDataSet(partvalue, totalvalue));
	}
	public ChartPanel getPieChart(int[] value,String[] info){
		DefaultPieDataset dpd=new DefaultPieDataset();
		Comparable<?>[] part = new Comparable<?>[value.length];
		for(int i=0;i<value.length;i++){
			part[i]=info[i] +value[i]; 
			dpd.setValue(part[i], value[i]);
		}
		return this.getPieChart(dpd);
		
	}
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//example
		PieChart pc = new PieChart();
		ChartPanel cp  = pc.getPieChart(3, 4);
		JFrame f = new JFrame();
		f.getContentPane().add(cp);
		f.setVisible(true);
	}

}

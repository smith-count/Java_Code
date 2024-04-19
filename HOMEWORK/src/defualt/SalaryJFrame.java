package defualt;

import java.util.*;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class SalaryJFrame extends JFrame implements ActionListener
{
	private JTextField[] texts;
	private JSpinner spin_year;
	private JButton button;
	private DefaultTableModel tablemodel;
	//private double tax;
	
	public SalaryJFrame()
	{
		super("计算月工资及个人所得税");
		this.setBounds(300,240,600,360);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE); 
		JPanel cmdpanel = new JPanel();
		this.getContentPane().add(cmdpanel,"North");
		String str = "年";
		cmdpanel.add(new JLabel(str));
		this.spin_year = new JSpinner(new SpinnerNumberModel(2018,0,2022,1));
		cmdpanel.add(this.spin_year,0);
		cmdpanel.add(this.button = new JButton("计算"));
		this.button.addActionListener(this);
		String[] titles = {"月份（月）","应发工资（元）","应缴税（元）","税后工资（元）"};
		this.tablemodel = new DefaultTableModel(titles,1);
		JTable jtable = new JTable(this.tablemodel);
		this.getContentPane().add(new JScrollPane(jtable));
		this.tablemodel.setRowCount(12);
		this.tablemodel.setColumnCount(4); 
		String[] months={"一月","二月","三月","四月","五月","六月",
				"七月","八月","九月","十月","十一月","十二月"};
		for(int row=0;row<12;row++)
	    {
	    	this.tablemodel.setValueAt(months[row],row,0);
	    } 
		this.setVisible(true);
		
	}
	public void actionPerformed(ActionEvent event)    
	{
		if(event.getSource()==this.button)
		{
			try{
					double salary=0;
					double date=0.1;
					double tax= salary*date;
					int i;
					int month = 0;
					int salarysum ,taxsum;
					for(salarysum=0,i=0;i<12;i++,month++)
					{
						Object obj=tablemodel.getValueAt(i, 1);
						salary=Integer.parseInt((String)this.tablemodel.getValueAt(i,1));
						if(obj!=null)
						{
							salarysum+=Integer.parseInt((String)this.tablemodel.getValueAt(i,1));
						}
					}
					for(taxsum=0,i=0;i<12;i++,month++)
					{
						int obj2=(int) ((Integer)(tablemodel.getValueAt(i, 1))*date);
						if(obj2!=0)
						{
							this.tablemodel.setValueAt(obj2,i,2);
							taxsum+=Integer.parseInt((String)this.tablemodel.getValueAt(i,1));
						}
					}
					for(salarysum=0,i=0;i<12;i++)
					{
							this.tablemodel.setValueAt(salarysum-taxsum,i,3);
					}
					double salaryaver= salarysum/12;
					double taxaver=taxsum/12;
					String[] str1={"合计"," "+salarysum};
					this.tablemodel.addRow(str1);
					String[] str2={"平均值"," "+salaryaver};
					this.tablemodel.addRow(str2);
					this.tablemodel.setValueAt(taxsum,12,2);
					this.tablemodel.setValueAt(salarysum-taxsum,12,3);
					this.tablemodel.setValueAt(taxaver,13,2);
					this.tablemodel.setValueAt(salaryaver-taxaver,13,3);
			}
			catch(NumberFormatException nfe)
			{	
			}
		}
	}
	public static void main(String[] arg)
	{
		new SalaryJFrame();
	}
}
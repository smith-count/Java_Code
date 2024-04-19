package defualt;

import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
public class Income extends JFrame implements ActionListener{
private JSpinner spin_year;
private JButton button;
private DefaultTableModel tablemodel;

public Income(){
	super("计算月工资");
	this.setBounds(200,400,800,360);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	JPanel cmdpanel =new JPanel();
	this.getContentPane().add(cmdpanel,"North");
	String str="年";
	cmdpanel.add(new JLabel(str));
	this.spin_year=new JSpinner(new SpinnerNumberModel(2018,0,2022,1));
	cmdpanel.add(this.spin_year,0);
	cmdpanel.add(this.button=new JButton("计算"));
	this.button.addActionListener(this);
	String[] titles={"月份","工资"};
	this.tablemodel=new DefaultTableModel(titles,1);
    JTable jtable=new JTable(this.tablemodel);
	this.getContentPane().add(new JScrollPane(jtable));
	this.setVisible(true);

	this.tablemodel.setRowCount(12);
	String[] months={"一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
	    for(int row=0;row<12;row++)
	    {
	    	this.tablemodel.setValueAt(months[row],row,0); 	
	    }
	   
}
public void actionPerformed(ActionEvent event)
{
	if(event.getSource()==this.button)
	{
		
	
	int income;
	 int i;
	for(income=0,i=0;i<12;i++)
	{
		income=income+Integer.parseInt(tablemodel.getValueAt(i,1).toString()+"");
	}
	String[] date1={"",""};
	this.tablemodel.addRow(date1);
	String[] date2={"",""};
	this.tablemodel.addRow(date2);
	}
}
public static void main(String[] arg)
{
	new Income();
}
}
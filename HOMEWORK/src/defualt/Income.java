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
	super("�����¹���");
	this.setBounds(200,400,800,360);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	JPanel cmdpanel =new JPanel();
	this.getContentPane().add(cmdpanel,"North");
	String str="��";
	cmdpanel.add(new JLabel(str));
	this.spin_year=new JSpinner(new SpinnerNumberModel(2018,0,2022,1));
	cmdpanel.add(this.spin_year,0);
	cmdpanel.add(this.button=new JButton("����"));
	this.button.addActionListener(this);
	String[] titles={"�·�","����"};
	this.tablemodel=new DefaultTableModel(titles,1);
    JTable jtable=new JTable(this.tablemodel);
	this.getContentPane().add(new JScrollPane(jtable));
	this.setVisible(true);

	this.tablemodel.setRowCount(12);
	String[] months={"һ��","����","����","����","����","����","����","����","����","ʮ��","ʮһ��","ʮ����"};
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
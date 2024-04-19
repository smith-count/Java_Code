package helloworld;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;



public class StudentGradeJFrame extends JFrame implements ActionListener
{

	
	
//	protected JMenu[] menus;
	private String filename="三年级二班-语文-成绩";
	private JTextField text_file;
	private JButton[] buttons;
	private JButton button_output;
	private DefaultTableModel[] tablemodels;
	private DefaultTableModel tablemodel;
	private JTabbedPane tab;   //选项卡窗格，每页显示一门课程
	private String[] subjects= {"语文","数学","英语"};	
	private String[] titles= {"学号","成绩"};
	private int[][] grade={{1,88},{2,85},{3,81},{4,89}};
	private final int[] level={101,90,80,70,60,0};

	public StudentGradeJFrame(int studentnumber)
	{
		//框架设计
		super("学生成绩多级统计");
		this.setBounds(300,240,1000,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//表格模型数组声明长度
		tablemodels=new DefaultTableModel[3];
		
		//表格对应其表格模型进行创建
		JTable[] jtables=new JTable[3];
		for(int i=0;i<jtables.length;i++)
		{			
			tablemodels[i]=new DefaultTableModel(titles,studentnumber);
			jtables[i]=new JTable(tablemodels[i]);
		}
		
		//为语文成绩一页的表格赋值
		for(int i=0;i<4;i++)
			for(int j=0;j<2;j++)
				tablemodels[0].setValueAt(grade[i][j], i, j);
		
		//设置按钮，事件监听，用以评等地
		button_output=new JButton("计算");
		this.getContentPane().add(button_output,"South");
		button_output.addActionListener(this);
		
		//位于北部的面板，包含文件名的输入，保存，打开按钮组件
		JPanel cmdpanel=new JPanel();
		cmdpanel.add(new JLabel("文件名"));
		this.getContentPane().add(cmdpanel,"North");
		String[] str= {"打开","保存"};
		text_file=new JTextField(filename);
		cmdpanel.add(text_file);
		buttons=new JButton[str.length];
		for(int i=0;i<str.length;i++)
		{
			buttons[i]=new JButton(str[i]);
			buttons[i].addActionListener(this);
			cmdpanel.add(buttons[i]);
		}
		
		
		//选项卡窗格设置
		tab=new JTabbedPane();	    
	     for (int i=0; i<subjects.length; i++)  
	     {
	    	 tab.addTab(subjects[i],new JScrollPane(jtables[i]));
	     } 		 	     
		 this.getContentPane().add(tab,"Center");
		 
		// 西部表格设置，及其内容赋值
		String[] dengdi= {"优","良","中","及格","不及格"};
		tablemodel=new DefaultTableModel(4,2);
		for(int i=0;i<4;i++)
			tablemodel.setValueAt(dengdi[i],i,0);
		JTable jtb=new JTable(tablemodel);
		this.getContentPane().add(new JScrollPane(jtb),"West");
		 
//设置可见
		 this.setVisible(true);
	}
	

		//事件处理方法
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==buttons[1])//保存
				{
					writeTo(this.text_file.getText(),tablemodels[0]);
				}
				if(event.getSource()==buttons[0])//打开
				{
					readFrom(this.text_file.getText(),tablemodels[0]);
				}
				if(event.getSource()==button_output)
				{
					int[] subjectgrade=new int[tablemodels[0].getRowCount()];
					for(int i=0;i<this.tablemodels[0].getRowCount();i++)
						subjectgrade[i]=this.grade[i][1];
					int c[]=Number(subjectgrade);
					this.outontable(c);
				}
				
			}
			
			//文件的打开
		    protected void readFrom(String filename,DefaultTableModel tablemodel) 
		    {
		    	try
		    	{
		    		InputStream in=new FileInputStream(filename);
		    		DataInputStream datain =new DataInputStream(in); 
		    		int i=0,j=0;
		    		for(i=0;i<tablemodel.getRowCount();i++)
		    		{
		    			for(j=0;j<tablemodel.getColumnCount();j++)
		    			{
		    				tablemodel.setValueAt(datain.readInt(), i, j);
		    			}
		    		}
		    		
		    		datain.close();
		    		in.close();
		    	}
		    	catch(FileNotFoundException ex)
		    	{
		    		JOptionPane.showMessageDialog(this, filename+"文件不存在");
		    	}
		    	catch(IOException ex)
		    	{
		    		JOptionPane.showMessageDialog(this,"读取文件时数据错误");
		    	}
		    	
		    }
		    
            //文件的打开
		    protected void writeTo(String filename,DefaultTableModel tablemodel)
		    {
		    	try
		    	{
		    		OutputStream out=new FileOutputStream(filename);
		    		DataOutputStream dataout=new DataOutputStream(out);
		    		for(int i=0;i<tablemodel.getRowCount();i++)
		    		{
		    			for(int j=0;j<tablemodel.getColumnCount();j++)
		    			{
		    				Object obj=tablemodel.getValueAt(i, j);  
		    				
		    				if(obj!=null && obj instanceof Integer)
		    				dataout.writeInt(((Integer)obj).intValue());
		    			}
		    		}
		    			dataout.close();
		    			out.close();
		    	}
		    		catch(FileNotFoundException ex)
		    		{
		    			JOptionPane.showMessageDialog(this, "\""+filename+"\"文件不存在");
		    		}
		    		catch(IOException ex)
		    		{
		    			JOptionPane.showMessageDialog(this, "写入文件时数据错误");
		    		}
		    }
	


		    //返回等地数组
			public int[] Number(int[] scores)      
			{
				int[] num={0,0,0,0,0,0};
				for(int i=0;i<5;i++)
					for(int j=0;j<scores.length;j++)
						{
							if(scores[j]>=level[i+1]&&scores[j]<level[i])
								num[i]++;					 
						}
				return num;
			}

			//为等地表格赋值
			public void outontable(int[] num)
			{
				for(int i=0;i<4;i++)
					this.tablemodel.setValueAt(num[i], i, 1);
			}
 
   public static void main(String[] args)
   {
	   new StudentGradeJFrame(4);
   }
   
}






//public void addMenu()
//{
//	String[] menustr= {"文件"};
//	String[][] menuitemstr= {{"打开","保存"}};
//	JMenuBar menubar=new JMenuBar();
//	this.setJMenuBar(menubar);
//	//一级菜单
//	this.menus=new JMenu[menustr.length];
//	//二级菜单
//	JMenuItem[][] menuitems=new JMenuItem[menus.length][];
//	
//	
//	for(int i=0;i<menustr.length;i++)
//	{
//		this.menus=new JMenu[menustr.length];
//		menubar.add(menus[i]);
//		menuitems[i]=new JMenuItem[menuitemstr[i].length];
//		for(int j=0;j<menuitemstr[i].length;j++)
//		{
//			menuitems[i][j]=new JMenuItem(menuitemstr[i][j]);
//			this.menus[i].add(menuitems[i][j]);
//		}
//		
//	}

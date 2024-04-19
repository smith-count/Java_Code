package helloworld;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.io.*;
public class StudentGrade extends JFrame implements ActionListener
{
    private JTextField[] texts;
    private String name;					//课程
	private final double[] level={101,90,80,70,60,0};
    private JButton[] buttons;
	private JTabbedPane tab;   //选项卡窗格，每页显示一门课程
	private double[] scores = new double[10];
	private File file;							//文件对象
	private JFileChooser fchooser;				//文件选择
	private Thread thread;
	private TabPageJPanel tpj;
	
	public StudentGrade()
	{
		super("学生成绩多级统计");
		this.setBounds(300,240,600,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//工具栏
		JToolBar toolbar = new JToolBar();
		this.getContentPane().add(toolbar, "North");
		String[][] str ={{"学科","班级"},{"语文","1"},{"新建页"}};
	    this.texts = new JTextField[str[0].length];
	    this.buttons=new JButton[str[2].length];
	     for (int i=0; i<this.texts.length; i++)  
	     {
	    	 toolbar.add(new JLabel(str[0][i]));
	    	 toolbar.add(this.texts[i]=new JTextField(str[1][i],6));
	     } 		 
	     toolbar.add(new JLabel("班"),4);
	     for (int i=0; i<this.buttons.length; i++)
	     {
	    	 this.buttons[i]= new JButton(str[2][i]);
	         toolbar.add(this.buttons[i]);
	         this.buttons[i].addActionListener(this);
	     }
		 this.getContentPane().add(this.tbj= new TabPageJPanel());
		 this.setVisible(true);
	}
	
	public int[] Number(double[] scores)      //对分数  分级
	{
		int[] num={0,0,0,0,0,0};
		for(int i=0;i<5;i++)
			for(int j=0;j<10;j++)
				{
					if(scores[j]>=level[i+1]&&scores[j]<level[i])
						num[i]++;					 
				}
		return num;
	}
	public void actionPerformed(ActionEvent event)
	{
		 String name= this.texts[0].getText();
		 if(event.getActionCommand().equals("新建页"))
		 {
			 this.tab.addTab(name,new TabPageJPanel());
			 this.tab.setSelectedIndex(this.tab.getTabCount()-1);
			 int index = tab.getSelectedIndex();
			 tab.setTitleAt(index, name);
		 }
	}	
 
	private class TabPageJPanel extends JPanel  implements ActionListener,Runnable
	{
		private DefaultTableModel tablemodel1,tablemodel2 ;
		private JButton[] buttons;
		private File file;
		private JFileChooser fchooser;
		private Thread thread;
		TabPageJPanel()
		{
			super(new BorderLayout());
			this.add(new JScrollPane());
			
			JToolBar toolbar = new JToolBar();
			this.add(toolbar, "South");
			String[] strs ={"统计","打开","保存","删除页"};
			
			this.buttons=new JButton[strs.length];		 
		     for (int i=0; i<this.buttons.length; i++)
		     {
		    	 this.buttons[i]= new JButton(strs[i]);
		         toolbar.add(this.buttons[i]);
		         this.buttons[i].addActionListener(this);
		     }
			//成绩表
			String[] titles1 = {"姓名","学号","成绩"};
			this.tablemodel1 = new DefaultTableModel(titles1,1);
			JTable jtable1 = new JTable(this.tablemodel1);
			JScrollPane scrollPane=new JScrollPane(jtable1);
			this.tablemodel1.setRowCount(50);
			this.tablemodel1.setColumnCount(3);
			this.add(scrollPane,"West");
			//统计表
			String[] titles2= {"等级","人数"};
			this.tablemodel2 = new DefaultTableModel(titles2,1);
			JTable jtable2 = new JTable(this.tablemodel2);
			JScrollPane scrollPane2=new JScrollPane(jtable2);
			this.tablemodel2.setRowCount(5);
			this.tablemodel2.setColumnCount(2);
			String[] levels={"优秀","良好","中等","及格","不及格"};
			for(int row=0;row<5;row++)
			{
				this.tablemodel2.setValueAt(levels[row],row,0);
			}
			this.add(scrollPane2,"Center");
		    this.fchooser=new JFileChooser();
		    this.fchooser.setCurrentDirectory(new File("C:\\User"));
//		    this.fchooser.setFileFilter(new ExtensionFileFilter("表格文件(*.xls)","xls"));
		}
		public int[] Number(double[] scores)      //函数对分数分级
		{
			int[] num={0,0,0,0,0,0};
			for(int i=0;i<5;i++)
				for(int j=0;j<10;j++)
					{
						if(scores[j]>=level[i+1]&&scores[j]<level[i])
							num[i]++;					 
					}
			return num;
		}
		public void actionPerformed(ActionEvent event) 
		{
			if(event.getSource() == this.buttons[0])//统计
			{
				try
				{	
					int No = 0;
					int[] number;
					for(No=0;No<10;No++)
					{
						Object obj=tablemodel1.getValueAt(No, 2);
						if(obj!=null)		
							scores[No] = Double.parseDouble((String) this.tablemodel1.getValueAt(No,2));
						else//空，不输入
							scores[No] =-1;
					}
					number=this.Number(scores);
					for(int row=0;row<5;row++)
						this.tablemodel2.setValueAt(number[row],row,1);
				}
				catch(NumberFormatException nfe)//成绩为数字
				{
					JOptionPane.showMessageDialog(this, "请输入0-100数字");
				}
				catch(NullPointerException npe)//名字，学号为不能为空
				{
					JOptionPane.showMessageDialog(this, "名字，学号为不能为空");
				}
				

			}
		      else if (event.getActionCommand().equals("打开")&&fchooser.showOpenDialog(this)==0)
	    	   		try
	        		{
	    	   			readFrom(fchooser.getSelectedFile(),this.tablemodel1);
	        		}
	        		catch(HeadlessException ex){}
		      else if (event.getActionCommand().equals("保存")&&fchooser.showSaveDialog(this)==0)
	        		try
	         		{
	        			writeTo(fchooser.getSelectedFile(),this.tablemodel1);
	         		}
	        		catch(HeadlessException ex){}
		      else if(event.getSource() == this.buttons[3])
				tab.remove(this);
		}
		
		
		
		
		public void run()
		{
			
		}
	    protected void readFrom(File filename,DefaultTableModel tablemodel1)
	    {
	    	try
	    	{
	    		InputStream in=new FileInputStream(filename);
	    		DataInputStream datain =new DataInputStream(in); 
	    		tablemodel1.setColumnCount(3);
	    		tablemodel1.setRowCount(50);
	    		int i=0,j=0;
	    			try
	    			{
	    				for(i=0;i<tablemodel1.getRowCount();i++)
	    					for(j=0;j<tablemodel1.getColumnCount();j++)
	    						this.tablemodel1.setValueAt(datain.readInt(), i, j);
	    				
	    			}
	    			catch(EOFException eof){}
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
	    protected void writeTo(File filename,DefaultTableModel tablemodel1)
	    {
	    	try
	    	{
	    		OutputStream out=new FileOutputStream(filename);
	    		DataOutputStream dataout=new DataOutputStream(out);
	    		for(int i=0;i<tablemodel1.getRowCount();i++)
	    		{
	    			for(int j=0;j<tablemodel1.getColumnCount();j++)
	    			{
	    				    Object obj=tablemodel1.getValueAt(i,j);
	    				    if(obj !=null&&obj instanceof Integer)
	    				    	dataout.writeChars(((Character)obj).toString());
	    				    else if (obj !=null &&obj instanceof Character)
	    				    	try{
	    				    			dataout.writeChars(((String)obj));
	    				    		}
	    				    	catch(NumberFormatException ex){}
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
	}
	public static void main(String[] arg)
	{
		new StudentGrade();
	}
}

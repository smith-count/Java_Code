package projectdesign;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;




public class SmithNumber extends JFrame implements ActionListener,Runnable
{
	private static String name="smith.int";
	
	private JTextField num,col,filename;
    private JButton button_output,button_save,button_open;
    private DefaultTableModel tablemodel,tablemodel1;
    private MessageJDialog Jdialog;
    private JPanel jpl;
    private static int[] suarray;
    
    public int SumOfSu=0;
    
    private JScrollPane jsp1,jsp2;
    
    Thread thread;//线程对象
    int sleeptime;//线程睡眠时间
    
    private JSplitPane jsp;

    public SmithNumber()
    {
        //框架设置       
    	super("smith数输出");
        this.setBounds(300,240,800,360);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);       
        //边布局
        this.getContentPane().setLayout(new BorderLayout());
        //北  控制面板 流布局
        JPanel cmdpanel=new JPanel(new FlowLayout());
        this.getContentPane().add(cmdpanel,"North");        
       //组件设置
        cmdpanel.add(new JLabel("列",JLabel.LEFT));//列
        this.col=new JTextField("6",10);
        col.setHorizontalAlignment(JTextField.CENTER);
        cmdpanel.add(col);
        
        cmdpanel.add(new JLabel("数字",JLabel.LEFT));//
        this.num=new JTextField("100",10);
        
        num.setEditable(true);//
        num.setHorizontalAlignment(JTextField.CENTER);
        cmdpanel.add(num);        
        //按钮
        button_output=new JButton("输出");
        cmdpanel.add(button_output);
        this.button_output.addActionListener(this);
        
        this.jpl=new JPanel();
        jpl.setLayout(new GridLayout(2,1));
        this.getContentPane().add(jpl,"West");
        String[] str= {"左图:smith数","右图:素数集"};
        for(int i=0;i<str.length;i++)
        {
        	jpl.add(new Label(str[i]));
        }
        		
        
        JPanel cmdpanel1=new JPanel(new FlowLayout());
        this.getContentPane().add(cmdpanel1,"South");
        
        String[] button_name= {"打开","保存"};
        JButton[] buttonfile= {button_open,button_save};
        
        cmdpanel1.add(new JLabel("文件名"));
        filename=new JTextField(name,10);
        cmdpanel1.add(filename);
        
        for(int i=0;i<button_name.length;i++)
        {
        	cmdpanel1.add(buttonfile[i]=new JButton(button_name[i]),"South");
        	buttonfile[i].addActionListener(this);
        }
        
        //表格
        this.tablemodel=new DefaultTableModel(1,6); 
        this.tablemodel1=new DefaultTableModel(1,6);
        JTable jtable=new JTable(this.tablemodel);
        JTable jtable1=new JTable(this.tablemodel1);
//        this.getContentPane().add(jtable,"Center");      
       
        
        
        
        this.jsp1=new JScrollPane(jtable);
        this.jsp2=new JScrollPane(jtable1);
        
        
        
        this.jsp=new JSplitPane(1,jsp1,jsp2);
        this.getContentPane().add(jsp,"Center");
        
        
        jsp.setDividerLocation(300);
        this.setVisible(true);
        
        
        this.sleeptime=(int)(Math.random()*10);
        
        this.thread=new Thread(this);
        this.Jdialog=new MessageJDialog();

    }   
    //报错对话框
    private class MessageJDialog extends JDialog
    {
    	private JLabel jlabel;
    	private MessageJDialog()
    	{
    		super(SmithNumber.this,"错误信息",true);
    		this.setSize(420,110);
    		this.jlabel=new JLabel("",JLabel.CENTER);
    		this.getContentPane().add(this.jlabel);
    		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	}   	
    	private void show(String message)
    	{
    		this.jlabel.setText(message);
    		this.setLocation(SmithNumber.this.getX()+100,SmithNumber.this.getY()+100);
    		this.setVisible(true);
    	}
    }
    
    
    public void run()
    {
    	try{
    		int num_all=Integer.parseInt(this.num.getText());
    		int num_col=Integer.parseInt(this.col.getText());
    		suarray=Suarray(Integer.parseInt(this.num.getText()));
    	    this.tablemodel.setColumnCount(1);
    	    this.tablemodel.setRowCount(1); 	
    	    int i=0,j=0;
   	   	    this.tablemodel.setColumnCount(num_col);
   	   	    
   	   	    for(int num=3;num<num_all;num++)
   	   			{   	   			
   	   				{
   	   					
   	   					if(SumOfDigits(num)==SumOfPrime(num))
   	   					{
   	   						this.tablemodel.setValueAt(num,i,j);
   	   						j++;
   	   						if(j>num_col-1)
  	   							{		
  	   								this.tablemodel.addRow(new Object[num_col]);
   	   								j=j%num_col;
   	   								i++;
   	   							}  
   	   						try {
								    Thread.sleep(this.sleeptime);
   	   							} 
   	   						catch(InterruptedException e) 
   	   							{
   	   								;
   	   							}
	
   	   					}
   	   				}
   	   		}
   	   	    if(j==0)
   	   	    	this.tablemodel.removeRow(i);
   	   	    i=0;j=0;
   	   	    this.tablemodel1.setColumnCount(num_col);
   	   	    this.tablemodel1.setRowCount(1); 	
   	   	    for(int k=0;k<suarray.length;k++)
   	   	    {  	   		
   	   	    	
   	   	    	if(suarray[k]==0)
   	   	    		{
   	   	    		 SumOfSu=k;
   	   	    		 break;
   	   	    		}
   	   	    		
   	   	    	this.tablemodel1.setValueAt(suarray[k], i, j);
   	   	    	j++;
   	   	    	if(j>num_col-1)
   	   	    	{		
					this.tablemodel1.addRow(new Object[num_col]);
					j=j%num_col;
					i++;
				}
   	   	    	
   	   		try {
				Thread.sleep(this.sleeptime);
			    } 
   	   		catch(InterruptedException e)
   	   			{
   	   				;
   	   			}
   	   	    }
   	   	    if(j==0)
   	   	    	this.tablemodel1.removeRow(i);

    	}
//    catch(ArrayIndexOutOfBoundsException ex1)
//    	{
//			this.Jdialog.show("列数无效(长度)");
//    	}
    catch(NumberFormatException ex1)
    	{
    		this.Jdialog.show("列数无效(格式异常)");
    	}
   }
    public void actionPerformed(ActionEvent event)
    {
    	 if(event.getSource()==this.button_output)
    	 {
    		 if(this.thread.getState()!=Thread.State.NEW)
        		 this.thread=new Thread(this);
        	 this.thread.start();	 
    	 }
    		 switch(event.getActionCommand())  	 
    		 {  		 
    		 case "保存":writeTo(this.filename.getText(),tablemodel);break;
    		 case "打开":readFrom(this.filename.getText(),tablemodel);  	 
    	     }
    }

    protected void readFrom(String filename,DefaultTableModel tablemodel)
	{
		try
		{			
			InputStream in=new FileInputStream(filename);
			DataInputStream datain=new DataInputStream(in);
//			int suarraylength=datain.readInt();
			tablemodel.setRowCount(1);
			tablemodel1.setRowCount(1);
			int i=0,j=0;
			int count=0;
			while(true)
			{	
				for(j=0;j<tablemodel1.getColumnCount();j++)
					
					{
					count++;
					tablemodel1.setValueAt(datain.readInt(), i, j);
					if(count==SumOfSu)
					{
						break;
					}
					}
				if(count==SumOfSu)
				{
					break;
				}
				i++;
				tablemodel1.addRow(new Object[tablemodel1.getColumnCount()]);										
			}
			i=0;
			j=0;
			while(true)
			{
				try
				{					
					for(j=0;j<tablemodel.getColumnCount();j++)
						tablemodel.setValueAt(datain.readInt(), i, j);
					i++;
					tablemodel.addRow(new Object[tablemodel.getColumnCount()]);	
				}
				catch(EOFException ex)
				{
					break;
				}
			}
			datain.close();
			in.close();
		}
		catch(FileNotFoundException ex)
		{
			this.Jdialog.show(filename+"文件不存在");
		}
		catch(IOException ex)
		{
			this.Jdialog.show("读取文件时数据错误");
		}
	}

    protected void writeTo(String filename,DefaultTableModel tablemodel)
    {
    	try
    	{
    		OutputStream out=new FileOutputStream(filename);
    		DataOutputStream dataout=new DataOutputStream(out);
    		
//    		int lengthofsuarray=suarray.length;
    		
//    		dataout.writeInt(lengthofsuarray);
    		
    		for(int i=0;i<tablemodel1.getRowCount();i++)
    		{
    			for(int j=0;j<tablemodel1.getColumnCount();j++)
    			{
    				Object obj=tablemodel1.getValueAt(i, j);
    				
    				if(obj!=null && obj instanceof Integer)
    					dataout.writeInt(((Integer)obj).intValue());
    				
    			}
    		}
    		for(int i=0;i<tablemodel.getRowCount();i++)
    		{
    			for(int j=0;j<tablemodel.getColumnCount();j++)
    			{
    				Object obj=tablemodel.getValueAt(i, j);
    				
    				if(obj!=null && obj instanceof Integer)
    					dataout.writeInt(((Integer)obj).intValue());
    				
    			}
    		}
    		
    		out.close();
    		dataout.close();
    		
    	}
    	catch(FileNotFoundException ex2)
    	{
    		this.Jdialog.show("未发现文件");
    	}
    	catch(IOException ex1)
		{
			this.Jdialog.show("输入输出错误");
		}
    }

    //因数和
    public int SumOfPrime(int num)
    {    	
    	
    	suarray=Suarray(num);
    	int n=num;
   	    int sum=0,count=0;	    
        while(true)
        {
        	int i=0;
        	for(i=0;i<suarray.length;i++)
        	{        		
        		if(n%suarray[i]==0)
        		{
        			n=n/suarray[i];
        			sum+=SumOfDigits(suarray[i]);
        			count++;
        			break;
        		}
        		
        	}
        	if(n==1)
        	{
        		break;
        	}
        } 
        if(count==1)
        	return sum=0;
    	return sum;   
    }
    
    //各位数和
    public int SumOfDigits(int num)
    {  	 
   	  int sum=0;
   	  while(true)
   	  {
   		  sum=sum+num%10;
       	  num=num/10;
       	  if(num==0)
       	  {
       		  sum=sum+num;
       		  break;
       	  }
      }   
       return sum; 
    }
    
    
    public static int[] Suarray(int num)
    {		
		int count=0;
		
    	int[] numl=new int[num];                  
		numl[0]=2;
		for(int i=3;i<=num;i+=2)
		{
			int j=(int)Math.sqrt(i);
			
			while(j>0&&i%j!=0)
			{	
				j-=2;		
			}
			if(j==0 || j==1)
				{
				count++;  
				numl[count]=i;		
				}	
			
		}
		
//		for(int i=2;i<=num;i++)
//		{
//			while(true)
//		}
		

		
		return numl;	
    }
    
    
    	
    
   
    public static void main(String[] args)
    {
        new SmithNumber();	
    }
}

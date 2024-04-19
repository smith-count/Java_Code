package changeinto;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;




public class FirestSmithNumber extends JFrame implements ActionListener,Runnable
{
	private static String[] name={"smith.int","su.int"};
	
	private JTextField num,col;
    private JButton button_output,button_save,button_open;
    private DefaultTableModel tablemodel,tablemodel1;
    private MessageJDialog Jdialog;
    private JPanel jpl;
    
    
    private JComboBox box;
    
    public int[] suarray,last_suarray;

    private int suend=0,smithend=0,last_num_all=0,last_num_col=0,time=0;
    
    private JScrollPane jsp1,jsp2;
    
    Thread thread;//线程对象
    int sleeptime;//线程睡眠时间
    
    private JSplitPane jsp;
    

    public FirestSmithNumber()
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
        this.box=new JComboBox();
        for(int i=0;i<2;i++)
        {
        	this.box.addItem(name[i]);
        }
        cmdpanel1.add(box);
        
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
    		super(FirestSmithNumber.this,"输入错误信息",true);
    		this.setSize(420,110);
    		this.jlabel=new JLabel("",JLabel.CENTER);
    		this.getContentPane().add(this.jlabel);
    		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
    	}   	
    	private void show(String message)
    	{
    		this.jlabel.setText(message);
    		this.setLocation(FirestSmithNumber.this.getX()+100,FirestSmithNumber.this.getY()+100);
    		this.setVisible(true);
    	}
    }
    
    
    public void run()
    {
    	
    	
    	try{
    		int num_all=Integer.parseInt(this.num.getText());
    		int num_col=Integer.parseInt(this.col.getText());
   		
    		if(time==0)
   	   	    {
   	   	    	suarray=new int[num_all/2];
   	   	    	last_suarray=new int[num_all/2];
   	   	    	
   	   	    }
   	   	    else
   	   	    	{
   	   	    		last_suarray=suarray;
   	   	    		suarray=new int[num_all/2];
   	   	    	}
   	   	    
   	   	    for(int a=0;a<last_suarray.length;a++)
   	   	    {
   	   	    	suarray[a]=last_suarray[a];
   	   	    }
    		
    		
    		if(num_all>=last_num_all || time==0)
    		{
    			
    		
//    	    this.tablemodel.setColumnCount(1);
//    	    this.tablemodel.setRowCount(1); 	

    	    int i=0,j=0;
//   	   	    this.tablemodel.setColumnCount(Integer.parseInt(this.col.getText())); 

//   	   	    this.tablemodel1.setColumnCount(num_col);
//   	   	    this.tablemodel1.setRowCount(1); 
   	   	    
                 
   	   	    this.tablemodel1.setValueAt(2,0,0);
   	   	    suarray[0]=2;
   	   	
   	   	    
   	   	    

   	   	
   	   	    
   	   	    if(num_all>last_num_all)
   	   	    
   	   	    
   	   	    {
   	   	    	
   	   	    
   	   	    i=suend/num_col;j=suend==0?1:(suend)%num_col;
   	   	    
   	   	    int ini=suend==0?3:(int)this.tablemodel1.getValueAt(i, j-1)+2;
		
   	   	    for(;ini<=num_all;ini+=2)//ini是初始值,i为行数,j为列数
   	   	    {
   	   	    	int RangeMax=(int)Math.sqrt(ini);
				
   	   	    	while(RangeMax>0&&ini%RangeMax!=0)
   	   	    	{	
   	   	    		RangeMax-=2;		
   	   	    	}
   	   	    	if(RangeMax==0 || RangeMax==1)
   	   	    	{
   	   	    		suend++;
   	   	    		this.tablemodel1.setValueAt(ini,i,j);
   	   	    		suarray[suend]=ini;
   	   	    		j++;
   	   	    		if(j>=num_col)
					{  
						j=j%num_col;
					    i++;
					    this.tablemodel1.addRow(new Object[num_col]);
					    
					}
   	   	    					
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
   	   	if(num_all<last_num_all)
    	{
   	   		
   	   		i=suend/num_col;j=suend==0?1:(suend)%num_col;	   	    
	   	    
    	}
   	   	     	   	
   	   	i=smithend/num_col;j=smithend==0?0:(smithend)%num_col;
	   	if(smithend%num_col==0 && time!=0)
	   		 this.tablemodel.addRow(new Object[num_col]);
	   	int num=smithend==0?3:(smithend%num_col==0?(int)this.tablemodel.getValueAt(i-1,num_col-1)+1:(int)this.tablemodel.getValueAt(i,j-1)+1);
   	   	    for(;num<num_all;num++)
   	   			{   	   			 	   				
   	   					if(SumOfDigits(num)==SumOfPrime(suarray,num))
   	   					{
   	   						smithend++;
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
   	   	    if(j==0)
   	   	    	this.tablemodel.removeRow(i);
    	
   	   	    last_num_all=num_all;
   	   	    last_num_col=num_col;
   	   	    last_suarray=suarray;
   	   	    time++;}
    	
    		
    	}
        catch(ArrayIndexOutOfBoundsException ex1)
    	{
			this.Jdialog.show("请输入一个大于"+last_num_all+"的数  "+"|"+"  你可以从下表找到所求范围内的Smith数");
    	}
    	catch(NumberFormatException ex1)
    	{
    		this.Jdialog.show("列数无效2");
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
    		 if(this.box.getSelectedIndex()==0)
    			 switch(event.getActionCommand())  	 
    		 {  		 
    		 case "保存":writeTo((String)this.box.getSelectedItem(),tablemodel);break;
    		 case "打开":readFrom((String)this.box.getSelectedItem(),tablemodel);  	 
    	     }
    		 else
    			 switch(event.getActionCommand())  	 
        		 {  		 
        		 case "保存":writeTo((String)this.box.getSelectedItem(),tablemodel1);break;
        		 case "打开":readFrom((String)this.box.getSelectedItem(),tablemodel1);  	 
        	     }
    			 
    }

    
//    class showtablethread extends Thread
//    {
//    	public String name;
//    	public void run()
//    	{
//    		
//    	}
//    	
//    }
    protected void readFrom(String filename,DefaultTableModel tablemodel)
	{
		try
		{			
			InputStream in=new FileInputStream(filename);
			DataInputStream datain=new DataInputStream(in);

			tablemodel.setRowCount(1);
			tablemodel.setRowCount(1);
			int i=0,j=0;

			while(true)
			{	
				try {
					
				
				for(j=0;j<tablemodel.getColumnCount();j++)
					
					{					
						tablemodel.setValueAt(datain.readInt(), i, j);										
					}
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

    		for(int i=0;i<tablemodel.getRowCount();i++)
    		{
    			for(int j=0;j<tablemodel.getColumnCount();j++)
    			{
    				Object obj=tablemodel.getValueAt(i, j);
    				
    				if(obj!=null && obj instanceof Integer)
    					dataout.writeInt(((Integer)obj).intValue());
    				if(obj==null)
    					break;
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
    public int SumOfPrime(int[] array,int num)
    {    	
    	int n=num;
    	int i=0;
    	int sum=0;
    	int count=0;
        while(true)
        {
        	i=0;
        	for(;i<array.length;i++)
        	{        		
        		
        		
        		
        		if(n%array[i]==0)
        		{
        			count++;
        			n=n/array[i];
        			sum=sum+SumOfDigits(array[i]);
        			break;        			
        		}
        		if(n==1)
        		{
        			break;
        		}

        		
        	}	
        	if(n==1)
    		{
    			break;
    		}
        }
        
        if(count==1)
        	return 0;
        else
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
    
    
//    public static int[] Suarray(int num)
//    {		
//		int count=1;
//    	int numl[]=new int[(int)(num/2)];                  
//		numl[0]=2;
//    	//2?
//		for(int i=3;i<=num;i+=2)
//		{
//			int j=(int)Math.sqrt(i);
//			
//			while(j>0&&i%j!=0)
//			{	
//				j-=2;		
//			}
//			if(j==i)
//				{
//				count++;  
//				numl[count]=i;		
//				}	
//			
//		}

		
//		return numl;	
//    }
    
    
    	
    
   
    public static void main(String[] args)
    {
        new FirestSmithNumber();	
    }
}
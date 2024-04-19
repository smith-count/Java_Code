package defualt;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class SmithNumber extends JFrame implements ActionListener,Runnable
{
    private JTextField num,col,filename;
    private JButton button_output;
    private DefaultTableModel tablemodel,tablemodel1;
    private MessageJDialog Jdialog;
    private static int[] suarray;
    
    JScrollPane JSpane,JSpane1;
    
    
    
    Thread thread;//线程对象
    int sleeptime;//线程睡眠时间
    
    private JSplitPane jsp,jsp1;

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
        this.num=new JTextField("1000",10);
        
        num.setEditable(true);//
        num.setHorizontalAlignment(JTextField.CENTER);
        cmdpanel.add(num);        
        //按钮
        button_output=new JButton("输出");
        cmdpanel.add(button_output);
        this.button_output.addActionListener(this);
        //表格
        this.tablemodel=new DefaultTableModel(1,6); 
        this.tablemodel1=new DefaultTableModel(1,6);
        JTable jtable=new JTable(this.tablemodel);
        JTable jtable1=new JTable(this.tablemodel1);
   
        //滚动窗格
        this.JSpane=new JScrollPane(jtable);
        this.JSpane1=new JScrollPane(jtable1);
        
        this.jsp=new JSplitPane(1,JSpane,jsp1=new JSplitPane(0,JSpane1,cmdpanel));
        this.getContentPane().add(jsp,"Center");
        
        
        jsp.setDividerLocation(300);
        this.setVisible(true);
        
        this.sleeptime=(int)(Math.random()*10);
        
        this.thread=new Thread(this);
        this.Jdialog=new MessageJDialog();

    }   
    //报错窗口
    private class MessageJDialog extends JDialog
    {
    	private JLabel jlabel;
    	private MessageJDialog()
    	{
    		super(SmithNumber.this,"输入错误信息",true);
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
    	suarray=Suarray(Integer.parseInt(this.num.getText()));
    	this.tablemodel.setColumnCount(1);
    	this.tablemodel.setRowCount(1); 	
    	String[] array={"","","","","",""};
    	int i=0,j=0;
   	   	this.tablemodel.setColumnCount(Integer.parseInt(this.col.getText()));
   	   	for(int num=3;num<Integer.parseInt(this.num.getText());num++)
   	   		{   	   			
   	   			{
   	   				if(SumOfDigits(num)==SumOfPrime(num))
   	   					{
   	   						this.tablemodel.setValueAt(num,i,j);
   	   						j++;
   	   						if(j>Integer.parseInt(this.col.getText())-1)
  	   							{		
  	   								this.tablemodel.addRow(array);
   	   								j=j%Integer.parseInt(this.col.getText());
   	   								i++;
   	   							}  
   	   						try {
								Thread.sleep(this.sleeptime);
							} catch (InterruptedException e) {
							
							}
   	   						
   	   						   	   					}  	
   	   				
   	   			}
   	   		}
   	    if(j==0)
			this.tablemodel.removeRow(i);

   	   	i=0;j=0;
   	   	this.tablemodel1.setColumnCount(Integer.parseInt(this.col.getText()));
   	   	this.tablemodel1.setRowCount(1); 	
   	  	for(int k=0;k<suarray.length;k++)
   	   	{  	   		
   	   		this.tablemodel1.setValueAt(suarray[k], i, j);
   	   		j++;
   	   		if(j>Integer.parseInt(this.col.getText())-1)
				{		
					this.tablemodel1.addRow(array);
					j=j%Integer.parseInt(this.col.getText());
					i++;
				}
   	   		try {
				Thread.sleep(this.sleeptime);
			} catch (InterruptedException e) {
				break;
			}
   	   	}
   	    if(j==0)
			this.tablemodel1.removeRow(i);
    	
   	   	
    }
    public void actionPerformed(ActionEvent event)
    {
    	 if(event.getSource()==this.button_output)
    	 {
    		 
    		if(this.thread.getState()!=Thread.State.NEW)
    		 this.thread=new Thread(this);
    			this.thread.start();
    		 
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
		for(int i=2;i<=num;i++)
		{
			int j=2;
			while(true)
			{
				if(i%j==0 && j!=i)
				{
					break;
				}
				if(i%j==0 && j==i)
				{
					count++;
					break;
				}
				j++;		
			}
		}
		int numl[]=new int[count]; 
		int count1=0;
		for(int a=2;a<=num;a++)
		{
			int b=2;
			while(true)
			{
				if(a%b==0 && b!=a)
				{
					break;
				}
				if(a%b==0 && b==a)
					{
						numl[count1]=a;
						count1++;
						break;
					}
				b++;		
			}
		}
		return numl;	
    }
    

    public static void main(String[] args)
    {
        new SmithNumber();
    }
}

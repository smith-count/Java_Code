package homework;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class SmithNumber extends JFrame implements ActionListener//,CaretListener
{
    private JTextField num,col;
    private JButton button_output;
    private DefaultTableModel tablemodel;
    private MessageJDialog Jdialog;
    public SmithNumber()
    {
        //框架设置       
    	super("smith数输出");
        this.setBounds(300,240,800,360);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);       
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
        cmdpanel.add(new JLabel("数字",JLabel.LEFT));//400
        this.num=new JTextField("400",10);       
        num.setEditable(true);//400可编辑
        num.setHorizontalAlignment(JTextField.CENTER);
        cmdpanel.add(num);        
        //按钮
        button_output=new JButton("输出");
        cmdpanel.add(button_output);
        this.button_output.addActionListener(this);
        //表格
        this.tablemodel=new DefaultTableModel(1,6); 
        JTable jtable=new JTable(this.tablemodel);
        this.getContentPane().add(jtable,"Center");      
        //CaretUpdate(null);
        this.setVisible(true);
        this.Jdialog=new MessageJDialog();
    }   
    //报错窗口
    private class MessageJDialog extends JDialog
    {
    	private JLabel jlabel;
    	private MessageJDialog()
    	{
    		super(SmithNumber.this,"提示",true);
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
    
    public void actionPerformed(ActionEvent event) 
    {
    	this.tablemodel.setRowCount(1);
    	try
    	{   	
    	this.tablemodel.setColumnCount(Integer.parseInt(this.col.getText()));
    	int x=(this.num.getText().equals(null)?0:Integer.parseInt(this.num.getText()));
    	if(x<=3)
   		{
   			this.Jdialog.show(x+"以内没有Smith数");
   		}    		
    	else{	
   			String[] array={"","","","","",""};
    			int i=0,j=0;
   		 		if(event.getSource()==this.button_output)
   		 		{	
   		 			for(int num=3;num<=Integer.parseInt(this.num.getText());num++)
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
   	   						}  	   					
   		 			}
   		 			}
   		 			if(j==0)
   		 				this.tablemodel.removeRow(i);
   		 	}	
   		}
    	}
    	catch(NumberFormatException ex)
    	{
    		this.Jdialog.show("输入字符串无法转换成整数");
    	}
    	catch(ArrayIndexOutOfBoundsException ex1)
    	{
    		this.Jdialog.show("列数无效");
    	}
    }
    //因数和
    public int SumOfPrime(int num)
    {
    	
    		final int n1=num;
    		int n=num;
    	    int sum=0;	        	    
    	    for(int i=2;i<n1;)    	    
    	    	{
    	    	while(true)
    	    	
    	    {
    	    	
    	    	if(n%i==0)
    	    		{
    	    			n=n/i;
    	    			sum=sum+SumOfDigits(i);
    	    			break;
    	    		}
    		    i++;
    		    if(i>=n1)
    		    	break;
    	    }
    	}
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
    public static void main(String[] args)
    {
        new SmithNumber();
    }
}
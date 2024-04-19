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
    private String name;					//�γ�
	private final double[] level={101,90,80,70,60,0};
    private JButton[] buttons;
	private JTabbedPane tab;   //ѡ�����ÿҳ��ʾһ�ſγ�
	private double[] scores = new double[10];
	private File file;							//�ļ�����
	private JFileChooser fchooser;				//�ļ�ѡ��
	private Thread thread;
	private TabPageJPanel tpj;
	
	public StudentGrade()
	{
		super("ѧ���ɼ��༶ͳ��");
		this.setBounds(300,240,600,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		//������
		JToolBar toolbar = new JToolBar();
		this.getContentPane().add(toolbar, "North");
		String[][] str ={{"ѧ��","�༶"},{"����","1"},{"�½�ҳ"}};
	    this.texts = new JTextField[str[0].length];
	    this.buttons=new JButton[str[2].length];
	     for (int i=0; i<this.texts.length; i++)  
	     {
	    	 toolbar.add(new JLabel(str[0][i]));
	    	 toolbar.add(this.texts[i]=new JTextField(str[1][i],6));
	     } 		 
	     toolbar.add(new JLabel("��"),4);
	     for (int i=0; i<this.buttons.length; i++)
	     {
	    	 this.buttons[i]= new JButton(str[2][i]);
	         toolbar.add(this.buttons[i]);
	         this.buttons[i].addActionListener(this);
	     }
		 this.getContentPane().add(this.tbj= new TabPageJPanel());
		 this.setVisible(true);
	}
	
	public int[] Number(double[] scores)      //�Է���  �ּ�
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
		 if(event.getActionCommand().equals("�½�ҳ"))
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
			String[] strs ={"ͳ��","��","����","ɾ��ҳ"};
			
			this.buttons=new JButton[strs.length];		 
		     for (int i=0; i<this.buttons.length; i++)
		     {
		    	 this.buttons[i]= new JButton(strs[i]);
		         toolbar.add(this.buttons[i]);
		         this.buttons[i].addActionListener(this);
		     }
			//�ɼ���
			String[] titles1 = {"����","ѧ��","�ɼ�"};
			this.tablemodel1 = new DefaultTableModel(titles1,1);
			JTable jtable1 = new JTable(this.tablemodel1);
			JScrollPane scrollPane=new JScrollPane(jtable1);
			this.tablemodel1.setRowCount(50);
			this.tablemodel1.setColumnCount(3);
			this.add(scrollPane,"West");
			//ͳ�Ʊ�
			String[] titles2= {"�ȼ�","����"};
			this.tablemodel2 = new DefaultTableModel(titles2,1);
			JTable jtable2 = new JTable(this.tablemodel2);
			JScrollPane scrollPane2=new JScrollPane(jtable2);
			this.tablemodel2.setRowCount(5);
			this.tablemodel2.setColumnCount(2);
			String[] levels={"����","����","�е�","����","������"};
			for(int row=0;row<5;row++)
			{
				this.tablemodel2.setValueAt(levels[row],row,0);
			}
			this.add(scrollPane2,"Center");
		    this.fchooser=new JFileChooser();
		    this.fchooser.setCurrentDirectory(new File("C:\\User"));
//		    this.fchooser.setFileFilter(new ExtensionFileFilter("����ļ�(*.xls)","xls"));
		}
		public int[] Number(double[] scores)      //�����Է����ּ�
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
			if(event.getSource() == this.buttons[0])//ͳ��
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
						else//�գ�������
							scores[No] =-1;
					}
					number=this.Number(scores);
					for(int row=0;row<5;row++)
						this.tablemodel2.setValueAt(number[row],row,1);
				}
				catch(NumberFormatException nfe)//�ɼ�Ϊ����
				{
					JOptionPane.showMessageDialog(this, "������0-100����");
				}
				catch(NullPointerException npe)//���֣�ѧ��Ϊ����Ϊ��
				{
					JOptionPane.showMessageDialog(this, "���֣�ѧ��Ϊ����Ϊ��");
				}
				

			}
		      else if (event.getActionCommand().equals("��")&&fchooser.showOpenDialog(this)==0)
	    	   		try
	        		{
	    	   			readFrom(fchooser.getSelectedFile(),this.tablemodel1);
	        		}
	        		catch(HeadlessException ex){}
		      else if (event.getActionCommand().equals("����")&&fchooser.showSaveDialog(this)==0)
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
	    		JOptionPane.showMessageDialog(this, filename+"�ļ�������");
	    	}
	    	catch(IOException ex)
	    	{
	    		JOptionPane.showMessageDialog(this,"��ȡ�ļ�ʱ���ݴ���");
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
	    			JOptionPane.showMessageDialog(this, "\""+filename+"\"�ļ�������");
	    		}
	    		catch(IOException ex)
	    		{
	    			JOptionPane.showMessageDialog(this, "д���ļ�ʱ���ݴ���");
	    		}
	    }
	}
	public static void main(String[] arg)
	{
		new StudentGrade();
	}
}

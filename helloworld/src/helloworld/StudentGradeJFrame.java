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
	private String filename="���꼶����-����-�ɼ�";
	private JTextField text_file;
	private JButton[] buttons;
	private JButton button_output;
	private DefaultTableModel[] tablemodels;
	private DefaultTableModel tablemodel;
	private JTabbedPane tab;   //ѡ�����ÿҳ��ʾһ�ſγ�
	private String[] subjects= {"����","��ѧ","Ӣ��"};	
	private String[] titles= {"ѧ��","�ɼ�"};
	private int[][] grade={{1,88},{2,85},{3,81},{4,89}};
	private final int[] level={101,90,80,70,60,0};

	public StudentGradeJFrame(int studentnumber)
	{
		//������
		super("ѧ���ɼ��༶ͳ��");
		this.setBounds(300,240,1000,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//���ģ��������������
		tablemodels=new DefaultTableModel[3];
		
		//����Ӧ����ģ�ͽ��д���
		JTable[] jtables=new JTable[3];
		for(int i=0;i<jtables.length;i++)
		{			
			tablemodels[i]=new DefaultTableModel(titles,studentnumber);
			jtables[i]=new JTable(tablemodels[i]);
		}
		
		//Ϊ���ĳɼ�һҳ�ı��ֵ
		for(int i=0;i<4;i++)
			for(int j=0;j<2;j++)
				tablemodels[0].setValueAt(grade[i][j], i, j);
		
		//���ð�ť���¼��������������ȵ�
		button_output=new JButton("����");
		this.getContentPane().add(button_output,"South");
		button_output.addActionListener(this);
		
		//λ�ڱ�������壬�����ļ��������룬���棬�򿪰�ť���
		JPanel cmdpanel=new JPanel();
		cmdpanel.add(new JLabel("�ļ���"));
		this.getContentPane().add(cmdpanel,"North");
		String[] str= {"��","����"};
		text_file=new JTextField(filename);
		cmdpanel.add(text_file);
		buttons=new JButton[str.length];
		for(int i=0;i<str.length;i++)
		{
			buttons[i]=new JButton(str[i]);
			buttons[i].addActionListener(this);
			cmdpanel.add(buttons[i]);
		}
		
		
		//ѡ���������
		tab=new JTabbedPane();	    
	     for (int i=0; i<subjects.length; i++)  
	     {
	    	 tab.addTab(subjects[i],new JScrollPane(jtables[i]));
	     } 		 	     
		 this.getContentPane().add(tab,"Center");
		 
		// ����������ã��������ݸ�ֵ
		String[] dengdi= {"��","��","��","����","������"};
		tablemodel=new DefaultTableModel(4,2);
		for(int i=0;i<4;i++)
			tablemodel.setValueAt(dengdi[i],i,0);
		JTable jtb=new JTable(tablemodel);
		this.getContentPane().add(new JScrollPane(jtb),"West");
		 
//���ÿɼ�
		 this.setVisible(true);
	}
	

		//�¼�������
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource()==buttons[1])//����
				{
					writeTo(this.text_file.getText(),tablemodels[0]);
				}
				if(event.getSource()==buttons[0])//��
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
			
			//�ļ��Ĵ�
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
		    		JOptionPane.showMessageDialog(this, filename+"�ļ�������");
		    	}
		    	catch(IOException ex)
		    	{
		    		JOptionPane.showMessageDialog(this,"��ȡ�ļ�ʱ���ݴ���");
		    	}
		    	
		    }
		    
            //�ļ��Ĵ�
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
		    			JOptionPane.showMessageDialog(this, "\""+filename+"\"�ļ�������");
		    		}
		    		catch(IOException ex)
		    		{
		    			JOptionPane.showMessageDialog(this, "д���ļ�ʱ���ݴ���");
		    		}
		    }
	


		    //���صȵ�����
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

			//Ϊ�ȵر��ֵ
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
//	String[] menustr= {"�ļ�"};
//	String[][] menuitemstr= {{"��","����"}};
//	JMenuBar menubar=new JMenuBar();
//	this.setJMenuBar(menubar);
//	//һ���˵�
//	this.menus=new JMenu[menustr.length];
//	//�����˵�
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

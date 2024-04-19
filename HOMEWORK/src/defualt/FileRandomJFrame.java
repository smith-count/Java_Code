package defualt;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//������������еĿ���࣬��Ӧ�����¼�
public class FileRandomJFrame extends JFrame implements ActionListener
{
    protected JPanel cmdpanel;                             //�������  //���ù�����
    protected JTextField text_filename, text_count;        //�ı���
    protected DefaultTableModel tablemodel;                //���ģ��
    
    public FileRandomJFrame(String filename)              //���췽����filenameָ���ļ���
    {
        super("��������У����������ļ�");
        this.setBounds(300,240,650,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //�����ڿ�ܱ������������壬������ӱ�ǩ���ı��С���ť�����
        this.cmdpanel = new JPanel();                      //������壬Ĭ�������֣�����
        this.getContentPane().add(this.cmdpanel, "North"); 
        this.cmdpanel.add(new JLabel("���������"));
        this.cmdpanel.add(this.text_count=new JTextField(10+"",5));
        this.text_count.addActionListener(this);
        String[] bstr={"����", "��", "����"};
        for(int i=0; i<bstr.length; i++) 
        {
            JButton button = new JButton(bstr[i]);
            this.cmdpanel.add(button);
            button.addActionListener(this);
        }
        this.cmdpanel.add(new JLabel("�ļ���"),3);
        this.cmdpanel.add(this.text_filename=new JTextField(filename,20),4);

        String[] title={"1","2","3","4","5","6","7","8","9","10"};
        this.tablemodel = new DefaultTableModel(title,0);  //���ģ�ͣ�0��10��
        JTable jtable = new JTable(this.tablemodel);       //�������ָ�����ģ��
        this.getContentPane().add(new JScrollPane(jtable));
        this.setVisible(true);
    }
    
//    public FileRandomJFrame()
//    {
//        this("");
//    }
    
    public void actionPerformed(ActionEvent event)         //�����¼���������������ť
    {
        //���������ɡ���ť�����ı����а��س�������this.tablemodel���ģ������������
        if(event.getActionCommand().equals("����") || event.getSource()==this.text_count)
            random(this.tablemodel, this.text_count);
        else
        {
            switch(event.getActionCommand())               //������ť������д�ļ�
            {
                case "��": readFrom(this.text_filename.getText(), this.tablemodel); break;
                case "����": writeTo(this.text_filename.getText(), this.tablemodel);
            }
        }
    }   

    //������������tablemodel���ģ���У�������text�ı���ָ�������tablemodelʣ�൥Ԫ
    protected void random(DefaultTableModel tablemodel, JTextField text)
    {
        try
        {   //�¾佫�ı������ַ���ת������������ʾ������������׳���ֵ��ʽ�쳣
            int n=Integer.parseInt(text.getText()), i=0;
            if(n<0)                                        //���if���̲�ûд
            {
                JOptionPane.showMessageDialog(this, "����n�����Ǹ�����"+n+"��");
                return;
            }
            int columns=tablemodel.getColumnCount();       //��ñ��ģ������
            int rows=(n%columns==0) ? n/columns : n/columns+1; //������û�ж�һ��
            tablemodel.setRowCount(rows);                  //���ñ��������ԭ�������ݻ���
            for(i=0; i<n; i++)                             //���ñ��ģ��ǰn��Ԫ��ֵΪ�����
                tablemodel.setValueAt((int)(Math.random()*100), i/columns, i%columns);//����Ϊֵ���кš��к�
            for(i=n; i/columns<rows && i%columns<columns; i++)  //�������һ��ʣ�൥Ԫ��ֵΪ��
                tablemodel.setValueAt(null, i/columns, i%columns);
        }
        catch(NumberFormatException ex)                    //���񲢴�����ֵ��ʽ�쳣
        {
            JOptionPane.showMessageDialog(this, "\""+text.getText()+"\"����ת��Ϊ������");
        }
    }
    
    //��ȡfilename�����ļ�����ӵ�tablemodel���ģ���С����ļ������ڣ�����ԡ�
    //tablemodel���ø�ֵ���ڷ��������޸��䵥Ԫ��ֵ��������ʵ�ʲ���
    protected void readFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//�ļ��ֽ�������
            DataInputStream datain=new DataInputStream(in);//�����ֽ�������������Դ���ļ��ֽ���
            tablemodel.setRowCount(1);                     //���ñ��ֻ��һ�У�ԭ�������ݻ���
            int i=0, j=0;
            while(true)                                    //��֪�ļ�����
            {
                try
                {
                    for(j=0; j<tablemodel.getColumnCount(); j++)  
                        //�¾�������ֽ��������ж�ȡһ��int���������õ�����(i,j)��Ԫ��
                        tablemodel.setValueAt(datain.readInt(), i, j);
                    i++;
                    tablemodel.addRow(new Integer[tablemodel.getColumnCount()]);//����1��//һ����ʱ�����һ����
                }
                catch(EOFException eof)                    //�������ֽ�����������ʱ�׳��ļ�β�쳣
                {   //�¾��ı�����ʾ��������
                    this.text_count.setText((i*tablemodel.getColumnCount()+j)+"");
                    while(j<tablemodel.getColumnCount())   //�����һ��ʣ�൥Ԫ������Ϊnull
                        tablemodel.setValueAt(null, i, j++);
                    break;                                 //�˳�while(true)ѭ��
                }
            }
            datain.close();                      //�ȹر�������
            in.close();                          //�ٹر��ļ���
        }
        catch(FileNotFoundException ex)          //���ļ������ڣ������
        {
            JOptionPane.showMessageDialog(this, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "��ȡ�ļ�ʱ���ݴ���");
        }
    }
    //��8��Լ���������ļ��쳣�������Ի��򣬸�֪����
    
    //��tablemodel���ģ������������д��filename�����ļ������Բ���ת�����������ַ���
    protected void writeTo(String filename, DefaultTableModel tablemodel)
    {
        try
        {   //�¾䴴���ļ��ֽ�����������ļ����ڣ�����д�����ļ�·����ȷ���ļ������ڣ��򴴽��ļ���
            //�����׳��ļ��������쳣
            OutputStream out = new FileOutputStream(filename);//,true);//��ӷ�ʽ����
            DataOutputStream dataout = new DataOutputStream(out); //�����ֽ�����������ļ��ֽ�����Ϊ����Դ
            int n=0;
            for(int i=0; i<tablemodel.getRowCount(); i++)         //��ñ��ģ������
            {
                for(int j=0; j<tablemodel.getColumnCount(); j++)  //��ñ��ģ������
                {   
                    Object obj=tablemodel.getValueAt(i,j); //��ñ��ָ����Ԫ��Ķ��󣬸������obj��������ʵ��
                    //���������Ԫ��������������д�룬��д��null
                    if(obj!=null && obj instanceof Integer)
                    {
                        dataout.writeInt(((Integer)obj).intValue());  //������д�������ֽ������
                        n++;
                    }
                    //���������Ԫ������������޸ĵ��ַ�������ת��������д�룬��д��null
                    else if(obj!=null && obj instanceof String && !obj.equals(""))
                    {
                        try
                        {
                            dataout.writeInt(Integer.parseInt((String)obj)); //����ת��������д����
                            n++;
                        }
                        catch(NumberFormatException ex) {}//���Բ���ת�����������ַ���
                    }
                }
            }
            dataout.close();                     //�ȹر�������
            out.close();                         //�ٹر��ļ���
            this.text_count.setText(n+"");       //�ı�����ʾд�����������
        }
        catch(FileNotFoundException ex)         //�ļ��������쳣�����ļ�·�������ļ�����null��""
        {
            JOptionPane.showMessageDialog(this, "\""+filename+"\"�ļ������ڡ�");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "д���ļ�ʱ���ݴ���");
        }
    }

    public static void main(String[] arg)
    {
//        new FileRandomJFrame("random.int");         //�������ļ�������ǰ�ļ��У������ļ���дʱ���� 
        new FileRandomJFrame("random.int");   //�ļ�·�����ļ�����ȷ 
//      new FileRandomJFrame("");                   //�ļ���Ϊ�մ����׳��ļ��������쳣
//        new FileRandomJFrame("E:/null/random.int"); //�ļ��в����ڣ��׳��ļ��������쳣
    }
}
package defualt;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.*;

//保存随机数序列的框架类，响应动作事件
public class FileRandomJFrame extends JFrame implements ActionListener
{
    protected JPanel cmdpanel;                             //命令面板  //不用工具栏
    protected JTextField text_filename, text_count;        //文本行
    protected DefaultTableModel tablemodel;                //表格模型
    
    public FileRandomJFrame(String filename)              //构造方法，filename指定文件名
    {
        super("随机数序列，保存整数文件");
        this.setBounds(300,240,650,200);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        //以下在框架北边添加命令面板，其上添加标签、文本行、按钮等组件
        this.cmdpanel = new JPanel();                      //命令面板，默认流布局，居中
        this.getContentPane().add(this.cmdpanel, "North"); 
        this.cmdpanel.add(new JLabel("随机数个数"));
        this.cmdpanel.add(this.text_count=new JTextField(10+"",5));
        this.text_count.addActionListener(this);
        String[] bstr={"生成", "打开", "保存"};
        for(int i=0; i<bstr.length; i++) 
        {
            JButton button = new JButton(bstr[i]);
            this.cmdpanel.add(button);
            button.addActionListener(this);
        }
        this.cmdpanel.add(new JLabel("文件名"),3);
        this.cmdpanel.add(this.text_filename=new JTextField(filename,20),4);

        String[] title={"1","2","3","4","5","6","7","8","9","10"};
        this.tablemodel = new DefaultTableModel(title,0);  //表格模型，0行10列
        JTable jtable = new JTable(this.tablemodel);       //创建表格，指定表格模型
        this.getContentPane().add(new JScrollPane(jtable));
        this.setVisible(true);
    }
    
//    public FileRandomJFrame()
//    {
//        this("");
//    }
    
    public void actionPerformed(ActionEvent event)         //动作事件处理方法，单击按钮
    {
        //单击“生成”按钮或在文本行中按回车键，在this.tablemodel表格模型中添加随机数
        if(event.getActionCommand().equals("生成") || event.getSource()==this.text_count)
            random(this.tablemodel, this.text_count);
        else
        {
            switch(event.getActionCommand())               //单击按钮，读或写文件
            {
                case "打开": readFrom(this.text_filename.getText(), this.tablemodel); break;
                case "保存": writeTo(this.text_filename.getText(), this.tablemodel);
            }
        }
    }   

    //添加随机整数到tablemodel表格模型中，个数由text文本行指定；清空tablemodel剩余单元
    protected void random(DefaultTableModel tablemodel, JTextField text)
    {
        try
        {   //下句将文本行中字符串转换成整数，表示随机数个数，抛出数值格式异常
            int n=Integer.parseInt(text.getText()), i=0;
            if(n<0)                                        //这个if，教材没写
            {
                JOptionPane.showMessageDialog(this, "行数n不能是负数："+n+"。");
                return;
            }
            int columns=tablemodel.getColumnCount();       //获得表格模型列数
            int rows=(n%columns==0) ? n/columns : n/columns+1; //行数，没有多一行
            tablemodel.setRowCount(rows);                  //设置表格行数，原来的数据还在
            for(i=0; i<n; i++)                             //设置表格模型前n个元素值为随机数
                tablemodel.setValueAt((int)(Math.random()*100), i/columns, i%columns);//参数为值、行号、列号
            for(i=n; i/columns<rows && i%columns<columns; i++)  //设置最后一行剩余单元格值为空
                tablemodel.setValueAt(null, i/columns, i%columns);
        }
        catch(NumberFormatException ex)                    //捕获并处理数值格式异常
        {
            JOptionPane.showMessageDialog(this, "\""+text.getText()+"\"不能转换为整数。");
        }
    }
    
    //读取filename整数文件，添加到tablemodel表格模型中。若文件不存在，则忽略。
    //tablemodel引用赋值，在方法体中修改其单元格值，作用于实际参数
    protected void readFrom(String filename, DefaultTableModel tablemodel)
    {
        try
        {
            InputStream in = new FileInputStream(filename);//文件字节输入流
            DataInputStream datain=new DataInputStream(in);//数据字节输入流，数据源是文件字节流
            tablemodel.setRowCount(1);                     //设置表格只有一行，原来的数据还在
            int i=0, j=0;
            while(true)                                    //不知文件长度
            {
                try
                {
                    for(j=0; j<tablemodel.getColumnCount(); j++)  
                        //下句从数据字节输入流中读取一个int整数，设置到表格的(i,j)单元格
                        tablemodel.setValueAt(datain.readInt(), i, j);
                    i++;
                    tablemodel.addRow(new Integer[tablemodel.getColumnCount()]);//表格加1行//一行满时，多加一空行
                }
                catch(EOFException eof)                    //当数据字节输入流结束时抛出文件尾异常
                {   //下句文本行显示整数个数
                    this.text_count.setText((i*tablemodel.getColumnCount()+j)+"");
                    while(j<tablemodel.getColumnCount())   //将最后一行剩余单元格设置为null
                        tablemodel.setValueAt(null, i, j++);
                    break;                                 //退出while(true)循环
                }
            }
            datain.close();                      //先关闭数据流
            in.close();                          //再关闭文件流
        }
        catch(FileNotFoundException ex)          //若文件不存在，则忽略
        {
            JOptionPane.showMessageDialog(this, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "读取文件时数据错误");
        }
    }
    //第8章约定，处理文件异常，弹出对话框，告知错误。
    
    //将tablemodel表格模型中所有整数写入filename整数文件，忽略不能转换成整数的字符串
    protected void writeTo(String filename, DefaultTableModel tablemodel)
    {
        try
        {   //下句创建文件字节输出流，若文件存在，则重写；若文件路径正确但文件不存在，则创建文件，
            //否则抛出文件不存在异常
            OutputStream out = new FileOutputStream(filename);//,true);//添加方式可行
            DataOutputStream dataout = new DataOutputStream(out); //数据字节输出流，以文件字节流作为数据源
            int n=0;
            for(int i=0; i<tablemodel.getRowCount(); i++)         //获得表格模型行数
            {
                for(int j=0; j<tablemodel.getColumnCount(); j++)  //获得表格模型列数
                {   
                    Object obj=tablemodel.getValueAt(i,j); //获得表格指定单元格的对象，父类对象obj引用子类实例
                    //以下若表格单元格中是整数，则写入，不写入null
                    if(obj!=null && obj instanceof Integer)
                    {
                        dataout.writeInt(((Integer)obj).intValue());  //将整数写入数据字节输出流
                        n++;
                    }
                    //以下若表格单元格中是输入或修改的字符串，则转换成整数写入，不写入null
                    else if(obj!=null && obj instanceof String && !obj.equals(""))
                    {
                        try
                        {
                            dataout.writeInt(Integer.parseInt((String)obj)); //将串转换成整数写入流
                            n++;
                        }
                        catch(NumberFormatException ex) {}//忽略不能转换成整数的字符串
                    }
                }
            }
            dataout.close();                     //先关闭数据流
            out.close();                         //再关闭文件流
            this.text_count.setText(n+"");       //文本行显示写入的整数个数
        }
        catch(FileNotFoundException ex)         //文件不存在异常，如文件路径错误、文件名是null或""
        {
            JOptionPane.showMessageDialog(this, "\""+filename+"\"文件不存在。");
        }
        catch(IOException ex)
        {
            JOptionPane.showMessageDialog(this, "写入文件时数据错误");
        }
    }

    public static void main(String[] arg)
    {
//        new FileRandomJFrame("random.int");         //参数是文件名，当前文件夹；若无文件，写时创建 
        new FileRandomJFrame("random.int");   //文件路径及文件名正确 
//      new FileRandomJFrame("");                   //文件名为空串，抛出文件不存在异常
//        new FileRandomJFrame("E:/null/random.int"); //文件夹不存在，抛出文件不存在异常
    }
}
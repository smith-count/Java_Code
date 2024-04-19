package homework;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.DefaultTableModel;

public class CalculaterJFrame extends JFrame 
{
    private JTextField text_num1,text_num2,text_result;
//    private MessageJDialog jdialog;

    public CalculaterJFrame()
    {
        super("Calculater");
        this.setBounds(300,240,500,100);
        this.setBackground(Color.lightGray);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.getContentPane().setLayout(new FlowLayout(FlowLayout.LEFT));

        this.text_num1=new JTextField("0",20);
        this.getContentPane().add(this.text_num1);
        this.text_num1.setHorizontalAlignment(JTextField.CENTER);
        this.text_num1.setEditable(true);




        this.text_num2=new JTextField("0",20);
        this.getContentPane().add(this.text_num2);
        this.text_num2.setHorizontalAlignment(JTextField.CENTER);
        this.text_num2.setEditable(true);

        this.text_result=new JTextField("0",20);
        this.getContentPane().add(this.text_result);
        this.text_result.setHorizontalAlignment(JTextField.CENTER);
        this.text_result.setEditable(false);
    }



    public static void main(String[] args)
    {
        new CalculaterJFrame();
    }


}

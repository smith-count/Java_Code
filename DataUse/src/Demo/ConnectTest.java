package Demo;

import java.sql.*;

public class ConnectTest {
	 
	    //MySQL 8.0 ���ϰ汾 - JDBC �����������ݿ� URL
	    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	    static final String DB_URL = "jdbc:mysql://localhost:3306/usemysql?useSSL=false&serverTimezone=UTC";
	 
	    // ���ݿ���û��������룬��Ҫ�����Լ�������
	    static final String USER = "root";
	    static final String PASS = "123456";
	 
	    public static void main(String[] args) {
	        Connection conn = null;
	        Statement stmt = null;
	        try{
	            // ע�� JDBC ����
	            Class.forName(JDBC_DRIVER);
	        
	            // ������
	            System.out.println("�������ݿ�...");
	            conn = DriverManager.getConnection(DB_URL,USER,PASS);
	        
	            // ִ�в�ѯ
	            System.out.println(" ʵ����Statement����...");
	            stmt = conn.createStatement();
	            //String sql;
	            //sql = "SELECT* FROM student";
	            ResultSet rs = stmt.executeQuery("SELECT * FROM student where name='scofield'");
	            
	            
	            //stmt.executeUpdate("insert into student values('scofield',45,89,100)");
//	            System.out.print("�����ɹ�");
	            while(rs.next())
	            	System.out.print("English:"+rs.getString(2)+" Math:"+rs.getString(3)+" Computer:"+rs.getString(4));
	            
	            System.out.print("\n��ѯ�ɹ�");
	            
	            // չ����������ݿ�
//	            while(rs.next())
//	            {               
//	                System.out.print("ID: " + rs.getString(1));	                
//	                System.out.print("\n");
//	            }
	            // ��ɺ�ر�
	            //rs.close();
	            stmt.close();
	            conn.close();
	        }catch(SQLException se){
	            // ���� JDBC ����
	            se.printStackTrace();
	        }catch(Exception e){
	            // ���� Class.forName ����
	            e.printStackTrace();
	        }finally{
	            // �ر���Դ
	            try{
	                if(stmt!=null) stmt.close();
	            }catch(SQLException se2){
	            }// ʲô������
	            try{
	                if(conn!=null) conn.close();
	            }catch(SQLException se){
	                se.printStackTrace();
	            }
	        }
	        //System.out.println("Goodbye!");
	    }
	}


package homework;

public class SumOfDigits 
{
	//���10000���ڸ�λ���ĺ�
	public static void main (String[] args)
    {
  	  int num=5766666;
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
      
      System.out.print(""+sum);
    }
   
}

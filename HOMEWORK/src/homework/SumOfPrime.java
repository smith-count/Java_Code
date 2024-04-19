package homework;

public class SumOfPrime 
{
	
	public static int SumOfDigits(int num)
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
		final int n1=9975;
		int n=9975;
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
	System.out.print(sum);
    }
}

package homework;

public class SmithArray
{
	public static int SumOfPrime(int num)

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
    for(int i=1;i<400;i++)
    {
    	if(SumOfDigits(i)==SumOfPrime(i))
    		System.out.print(" "+i);
    }
}

}
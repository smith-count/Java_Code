package print;

public class TEST 
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
	
	
	
	
	
	public static int SumOfPrime(int[] array,int num)
    {    	
		
	    	
	    	
	    	int n=num;
	   	    int sum=0,count=0;	    
	        while(true)
	        {
	        	int i=0;
	        	for(i=0;i<array.length;i++)
	        	{        		
	        		if(n%array[i]==0)
	        		{
	        			n=n/array[i];
	        			sum+=SumOfDigits(array[i]);
	        			count++;
	        			break;
	        		}
	        		
	        	}
	        	if(n==1)
	        	{
	        		break;
	        	}
	        } 
	        if(count==1)
	        	return sum=0;
	    	return sum;   
	    
    }
	

    

    	
    	public static void main(String[] args) 
    {
    		
    		int[] suarray=new int[60];
       	   	//求素数数组
    		suarray[0]=2;
       	   	int c=1;
       	   	for(int a=3;a<100;a+=2)
       	   	{
       	   		int Range=(int) Math.sqrt(a);
       	   		while(Range>0&& a%Range!=0)
       	   			Range-=2;
       	   		if(Range==1 || Range==0)
       	   			
       	   			{
       	   				suarray[c]=a;
       	   				c++;
       	   			}
       	   		
        	}
       	   	
       	   	
       	   	for(int i=2;i<50;i++)
       	   	{
       	   		if(SumOfDigits(i)==SumOfPrime(suarray,i))
       	   		System.out.print(i+" ");
       	   	}
    		
    		
    		
    		
    		
    		
    }
}

package print;

public class Case
{
	public static void main(String[] a)
	{
		int num=400;
		int count=0;
        int i=2;
        int[] SuArray=new int[400];//77
        SuArray[0]=2;
        for(int n=3;n<=400;n++)
        {
            i=2;
            while(i<n && n%i!=0)
                i++;
            if(i==n)
            {
                count++;
                SuArray[count]=n;
            }
        }
        int sum=0;
        num=5;
        
        for(int j=0;j<SuArray.length;j++)
        {	
        	if(num==SuArray[j])
        		sum=0;
        	    break;
        }	
        for(int a1=0;a1<9 ;a1++)
        {
        	for(int j=0;j<SuArray.length;j++)
        	{
        		if((num%(SuArray[j])==0))
        		{
        			sum=sum+SuArray[j];
        			num=num/(SuArray[j]);
        			break;
        		}
        	}
        	
        	if(num==1)
        		break;
        }
        
        int[] a1=new int[400];
        for(int q=1;q<a1.length;q++)
        	
        	for(int p=0;p<SuArray.length;p++)
        	
        	{
        		if(q==SuArray[p])
        			break;
        	}
          	
        
        
        		
      //  for(int c=0;c<100;c++)
        	System.out.print(sum);
        
	}

}

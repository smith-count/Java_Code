package Answer;

public class Test4
{
	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m=nums1.length;
        int n=nums2.length;
        int num[]=new int[n+m];
        int i=0;
        int j=0;
        int count=0;
        
        if(m==0)
        {
        	if(n%2==0)
            	return (num[n/2-1]+num[n/2])/2;
            else {
            	return num[n/2];}
        }
        	
        if(n==0)
       	{
        	if(m%2==0)
            	return (num[m/2-1]+num[m/2])/2;
            else {
            	return num[m/2];}	
       	}
        	
        while(count<m+n)
        {
        	if(i>=m)
        		{num[count]=nums2[j];
        		j++;
        		count++;
        		continue;
        		}
        	if(j>=n)
        	{
        		num[count]=nums1[i];
        		i++;
        		count++;
        		continue;
        	}
        	
        	if(nums1[i]<=nums2[j] && i<m &&j<n)
        		{
        			num[count]=nums1[i];
        			i++;
        		}
        	else {
        		num[count]=nums2[j];
        		j++;
        	}
        	
        	count++;
        }
        
        if((m+n)%2==0)
        	return (num[(m+n)/2-1]+num[(m+n)/2])/2;
        else {
        	return num[(m+n)/2];
        }

    }

}

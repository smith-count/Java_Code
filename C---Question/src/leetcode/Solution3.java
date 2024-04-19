package leetcode;

//class Solution3 {
//    public static int lengthOfLongestSubstring(String s)
//    {
//    	int maxlength=0;
//
////pwwkew    	
//    	int previousnumber=0;
//    	
//    	boolean find=false;
//    	
//    	String substring;
//    	for(int i=1;i<s.length();)
//    	{
//    		substring=s.substring(previousnumber,i);
//    		for(int j=0;j<substring.length();j++)
//    		{
//    			if(s.charAt(i)==substring.charAt(j))
//    			{   				    				
//    				find=true;
//    				break;
//    			}    				
//    		}
//    		
//    		
//    		
//    		if(find)
//    		{    			
//    			previousnumber=i;
//    			i++;
//    		}
//    		else
//    		{   			   			
//    			i++;   			
//    		} 
//    		
//    		if(maxlength<substring.length())
//				   maxlength=substring.length();
//    		
//    		find=false;
//    		
//    	}   	
//    	return maxlength;
//
//    }

class Solution3 {
    public int lengthOfLongestSubstring(String s) 
    {
        // ��¼�ַ���һ�γ��ֵ�λ��
        int[] last = new int[128];
        //�����������Ա��ֵΪ-1
        //�˴���֪Unicode���빲��128��
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        

        int res = 0;
        int start = 0; // ���ڿ�ʼλ��
        
        
        for(int i = 0; i < s.length(); i++)
         {
            int index = s.charAt(i);//index��ʾs��ǰ���ַ���Unicode����ֵ
            start = Math.max(start, last[index] + 1);//start�뵱ǰ�ַ��������е�ֵ���Ƚ�
            res   = Math.max(res, i - start + 1);//res���ȱȽ�
            last[index] = i;//��ʱ���ַ����ַ����е�λ�ø�����Ӧ����ֵ
        }

        return res;
    }
}
//pwwkew  
 
//    public static void main(String args[])
//    {
//    	
//    	System.out.println(lengthOfLongestSubstring("pwwkew"));
//    }
//}

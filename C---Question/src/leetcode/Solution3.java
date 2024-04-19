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
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        //将所有数组成员赋值为-1
        //此处须知Unicode编码共有128个
        for(int i = 0; i < 128; i++) {
            last[i] = -1;
        }
        

        int res = 0;
        int start = 0; // 窗口开始位置
        
        
        for(int i = 0; i < s.length(); i++)
         {
            int index = s.charAt(i);//index表示s当前的字符的Unicode编码值
            start = Math.max(start, last[index] + 1);//start与当前字符在数组中的值作比较
            res   = Math.max(res, i - start + 1);//res长度比较
            last[index] = i;//此时的字符在字符串中的位置赋给相应数组值
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

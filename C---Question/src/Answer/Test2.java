package Answer;

public class Test2
{
		public static void main(String[] args)
		{
			int num1=0,num2=0;
			boolean find=false;
			for(int i=0;i<args[0].length();i++)
				if(args[0].charAt(i)=='A' && find==false)
					
					{
						num1=1;
						find=true;
					}
				else
				{
					num2=i;
				}
			System.out.print(args[0].substring(num1+1,num2));
		}

}
package Answer;


	public class Test1
	{
			public static void main(String[] args)
			{
				int x=0;
				int y=0;
				int z=0;
				for(x=0;x<11;x++)
					for(y=0;y<50-5*x;y++)
						if(5*x+2*y<=50)
							{
								z=50-5*x-2*y;
								System.out.println("x:"+x+" y:"+y+" z:"+z);
							}
			}
	
	}


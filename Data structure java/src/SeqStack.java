public final class SeqStack<T> implements Stack<T>
{
	private SeqList<T> list;//顺序表
	public SeqStack(int length)//构造顺序表
	{
		this.list=new SeqList<T>(length);
	}
	
	public SeqStack()//线性表的默认构造方法
	{
		this(64);
	}
	
	public boolean isEmpty()//栈是否为空
	{
		return this.list.isEmpty();
	}
	
	public void push(T x)//插入元素（入栈）
	{
		this.list.insert(x);
	}
	
	public T peek()//返回栈顶元素未出栈
	{
		return this.list.get(list.size()-1);
	}
	
	public T pop() //返回栈顶元素出栈
	{
		return list.remove(list.size()-1);//顺序表删除值并且返回了值
	}
	
	public String toString()//返回所有元素的描述字符串
	{
		return this.list.toString();
	}
	
	public String toPreviousString()//反序输出栈元素
	{
		String str="自栈顶输出元素：";
		while(!(this.list.size()==0))
		{
			str=" "+this.pop().toString();
		}
		return str;
	}

}

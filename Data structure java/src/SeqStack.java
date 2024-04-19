public final class SeqStack<T> implements Stack<T>
{
	private SeqList<T> list;//˳���
	public SeqStack(int length)//����˳���
	{
		this.list=new SeqList<T>(length);
	}
	
	public SeqStack()//���Ա��Ĭ�Ϲ��췽��
	{
		this(64);
	}
	
	public boolean isEmpty()//ջ�Ƿ�Ϊ��
	{
		return this.list.isEmpty();
	}
	
	public void push(T x)//����Ԫ�أ���ջ��
	{
		this.list.insert(x);
	}
	
	public T peek()//����ջ��Ԫ��δ��ջ
	{
		return this.list.get(list.size()-1);
	}
	
	public T pop() //����ջ��Ԫ�س�ջ
	{
		return list.remove(list.size()-1);//˳���ɾ��ֵ���ҷ�����ֵ
	}
	
	public String toString()//��������Ԫ�ص������ַ���
	{
		return this.list.toString();
	}
	
	public String toPreviousString()//�������ջԪ��
	{
		String str="��ջ�����Ԫ�أ�";
		while(!(this.list.size()==0))
		{
			str=" "+this.pop().toString();
		}
		return str;
	}

}

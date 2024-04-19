public class BinaryNode<T>                      
{
    public T data;                               
    public BinaryNode<T> left, right;            

    //�����㣬dataָ��Ԫ�أ�left��right�ֱ�ָ�����Ӻ��Һ��ӽ��
    public BinaryNode(T data, BinaryNode<T> left, BinaryNode<T> right)
    {
        this.data = data;
        this.left = left;
        this.right = right;
    }
    public BinaryNode(T data)                    
    {
        this(data, null, null);
    }

    public String toString()                     
    {
        return this.data.toString();
    }

    public boolean isLeaf()                      
    {
        return this.left==null && this.right==null;
    }
    public boolean equals(BinaryNode<T> parent)
	{
		return equals(parent,this);
	}
	private boolean equals(BinaryNode<T> parent,BinaryNode<T> p)
	{
		if(parent==null&&p==null)
			return true;
		if(parent==null||p==null)
			return false;
			
		if(parent.data==p.data)
			return equals(parent.left,p.left)&&equals(parent.right,p.right);
		
		return false;
		
	}
	
}

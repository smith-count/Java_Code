public class BinaryNode<T>                      
{
    public T data;                               
    public BinaryNode<T> left, right;            

    //构造结点，data指定元素，left、right分别指向左孩子和右孩子结点
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

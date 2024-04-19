public class BinaryTree<T>  extends Object  
{
    public BinaryNode<T> root;                  

    public BinaryTree()                          
    {
        this.root=null;
    }
    public boolean isEmpty()                     
    {
        return this.root==null;
    }
    public String toString()           
    {
        return "先根次序遍历二叉树："+toString(this.root);
    }
    public String toString(BinaryNode<T> p)     
    {
        if(p==null)
            return "∧";                         
        return p.data.toString()+" " + toString(p.left) + toString(p.right);
    }
    public BinaryTree(T[] prelist)               
    {
        this.root = create(prelist);
    }
    private int i=0;
    private BinaryNode<T> create(T[] prelist)
    {
        BinaryNode<T> p = null;
        if(i<prelist.length)
        {
            T elem=prelist[i++];
            if(elem!=null)                       
            {
                p = new BinaryNode<T>(elem);     
                p.left = create(prelist);        
                p.right = create(prelist);       
            }
        }
        return p;
    }
    public void remove(BinaryNode<T> p, boolean left)
    {
        if(p!=null)
        {
            if(left)
            {
                System.out.println("删除子树："+toString(p.left));   
                p.left = null;
            }
            else
            {
                System.out.println("删除子树："+toString(p.right));  
                p.right = null;
            }
        }
    }
    public BinaryNode<T> search(BinaryTree<T> pattern,SeqStack<BinaryNode<T>> stack)//查找匹配
    {
    	
    	return search(pattern.root,this.root,stack);
    }
/*    private BinaryNode<T> search(BinaryNode<T> pattern,BinaryNode<T> p)
    {
    	if(p==null)
    		return null;
    	if(p.equals(pattern))
    		return p;
    	
    	else
    	{
    		BinaryNode<T> m = search(pattern,p.left);
    		if(m!=null)
    			return m;
    		BinaryNode<T> n =search(pattern,p.right);
    		if(n!=null)
    			return n;
    	}
    	return null;
    }
 */
	private BinaryNode<T> search(BinaryNode<T> pattern,BinaryNode<T> p,SeqStack<BinaryNode<T>> stack)
    {
    	if(p==null)
    		return null;
    	if(p.left==null)
    		;
    	else
    		if(p.left.data==pattern.data)
    			stack.push(p);
    	if(p.right==null)
    		;
    	else
    		if(p.right.data==pattern.data)
    			stack.push(p);
    	if(p.equals(pattern))
    		return p;
    	
    	else
    	{
    		BinaryNode<T> m = search(pattern,p.left,stack);
    		if(m!=null)
    			return m;
    		BinaryNode<T> n =search(pattern,p.right,stack);
    		if(n!=null)
    			return n;
    	}
    	return null;
    }

    
  
  
    
    public BinaryNode<T> parent(BinaryNode<T> node)//返回父母节点
    {   
    	if(node.equals(this.root))
    		return null;
    	return parent(node,this.root);     	
    }
    private BinaryNode<T> parent(BinaryNode<T> node1,BinaryNode<T> node2)
    {
    	if(node2==null)
    		return null;
    	if(node1.equals(node2.left)||node1.equals(node2.right))
    	{
    		return node2;
    	}
    	else
    	{
    		BinaryNode<T> l =parent(node1,node2.left);
    		if(l!=null) return l;
    		BinaryNode<T> r = parent(node1,node2.right);
    		
    		if(r!=null) return r;
    	}
    		
    	return null;
    		
    }
   public void removeAll(BinaryTree<T> pattern)
    {
    	BinaryNode<T> node=parent(pattern.root);
    	if(node!=null)
    	{
    		if(node.left.data.equals(pattern.root.data))
    			node.left=null;
    		else
    			node.right=null;
    		removeAll(pattern);
    	}
    }
    
    public static void main(String[] args) {
    	
    	String a1[] = {"A","B","D","G","E",null,null,"F","H",null,null,null,null,"C","E",null,null,"F","H"};
    	String a2[] = {"F","H"};
    	
        BinaryTree<String> bitree=new BinaryTree<String>(a1);
        BinaryTree<String> pattern=new BinaryTree<String>(a2);
        
        System.out.println("原树："+bitree.toString());
        
        BinaryTree<String> bitree2=new BinaryTree<String>();
       
        SeqStack<BinaryNode<String>> stack=new SeqStack<BinaryNode<String>>();
        bitree2.root=bitree.search(pattern,stack);
            
        System.out.println("匹配子树1："+bitree2.toString());
        
        bitree.removeAll(bitree2);
        System.out.println("删除后1："+bitree.toString());


        System.out.print(stack.pop());
        System.out.print(stack.pop());
        
       
    }
   
    
    
  
    
    
 

   
	

}

public class SinglyList<T> extends Object 
{

	public Node<T> head;                         
    public SinglyList()                          
    {
        this.head = new Node<T>();               
    }
    
    public SinglyList(T[] values)
    {
        this();                                  
        Node<T> rear = this.head;                
        for(int i=0; i<values.length; i++)       
        {
        	if(values[i]!=null)
            {
                rear.next = new Node<T>(values[i], null);  
                rear = rear.next;                
            }
        }
    }

   
    public boolean isEmpty()                     
    {
        return this.head.next==null;
    }

    public T get(int i)                          
    {
        Node<T> p=this.head.next;
        for(int j=0;  p!=null && j<i;  j++)     
            p = p.next;
        return (i>=0 && p!=null) ? p.data : null;
    }
   
    
    public void set(int i, T x)
    {
        if(x==null)
            throw new NullPointerException("x==null");     
        else
        {
            Node<T> p=this.head.next;
            for(int j=0;  p!=null && j<i;  j++)  
               p = p.next;
            if(i>=0 && p!=null)
               p.data = x;                       
            else throw new IndexOutOfBoundsException(i+"");
        }
    }
    
    public String toString()
    {
        String str="(";
        
        for(Node<T> p=this.head.next;  p!=null;  p=p.next) 
        {
            str += p.data.toString()+(p.next!=null?",":"");
        }
        return str+")";                          
    }
    public int size()                            
    {
        int i=0; 
        for(Node<T> p=this.head.next;  p!=null;  p=p.next) 
            i++;
        return i;
    }
    
    public Node<T> insert(int i, T x)
    {
        if(x==null)
            return null;               
        Node<T> front=this.head;                 
        for(int j=0;  front.next!=null && j<i;  j++)  
            front = front.next;
        front.next = new Node<T>(x, front.next); 
        return front.next;
    }
    public Node<T> insert(T x)                   
    { 
        return insert(Integer.MAX_VALUE, x);          
    }
    
    public T remove(int i)         
    {
        Node<T> front=this.head;                 
        for(int j=0;  front.next!=null && j<i;  j++)
            front = front.next;
        if(i>=0 && front.next!=null)             
        {
            T x = front.next.data;               
            front.next = front.next.next;
            return x;
        }
        return null;                                    
    }

    public void clear()                          
    {
        this.head.next = null;                   
    }
    
    
    
    public SinglyList(SinglyList<T> list)    //深拷贝    
    {
        this();                                  
        this.copy(list);
    }
    public void copy(SinglyList<T> list)         
    {
    	this.clear();                            
    	Node<T> rear=this.head;
        for(Node<T> p=list.head.next; p!=null; p=p.next) 
        {
            rear.next = new Node<T>(p.data, null);
            rear = rear.next;                    
        }
    }
    
    
    public void concat(SinglyList<T> list)
    {
    	Node<T> p=this.head;
    	while(p.next!=null)                   
            p = p.next;
        p.next = list.head.next;              
        list.head.next = null;                  
    }
    public void addAll(SinglyList<T> list)
    {
        this.concat(new SinglyList<T>(list));    
    }
    public SinglyList<T> union(SinglyList<T> list)
    {
        SinglyList<T> result = new SinglyList<T>(this);    
        result.addAll(list);
        return result;                           
    }
    public SinglyList<T> overlapping(SinglyList<T> list)
    {
    	SinglyList<T> result = new SinglyList<T>(this);
    	result.cross(list);
    	return result;  
    }
    public SinglyList<T> cross(SinglyList<T> list)
    {
 /*   	SinglyList<T> result = new SinglyList<T>(this);
    	Node<T> p=result.head.next;
    	for(Node<T> m=list.head.next;m!=null&&p!=null;m=m.next,p=p.next)
    	{
    		p.next=new Node<T>(m.data,p.next);
    		p=p.next;
    		
    	}
*/     	SinglyList<T> result=new SinglyList<T>();
     	Node<T> m=list.head.next;
     	Node<T> p=this.head.next;
  
     	for(Node<T> n=result.head;m!=null&&p!=null;m=m.next,p=p.next)
     	{
     		n.next=new Node<T>(p.data,null);
     		n=n.next;
     		n.next=new Node<T>(m.data,null);
     		n=n.next;
     	}

/*    	SinglyList<T> result = new SinglyList<T>(this);
    	int i=1;
    	
    	for(Node<T> n=list.head.next;n!=null;i++,n=n.next)
    	{
    		results.insert(i,n.data);
    		i++;
    	}*/
    	return result;
    }
 /*   public static void main(String args[])
    {
        String[] values={"A","B","C","D"};
        String[] valuesA={"G","H","I","J"};
       SinglyList<String> lista = new SinglyList<String>(values);
       SinglyList<String> listb = new SinglyList<String>(valuesA);
       
       System.out.println(lista.cross(listb).toString());
       
    }
    */
    public void replaceAll(SinglyList<T> pattern, SinglyList<T> list) 
	{
        Node<T> front =this.head;
        Node<T> p=this.head.next;
        Node<T> q=pattern.head.next;
        if (this.size() < pattern.size())
            throw new UnsupportedOperationException("模式串长度超过文本长度！");
            while(p!=null)
        {
              while(p!=null&&q!=null&&q.data==p.data)
                {
                    p=p.next;
                    q=q.next;
                }
                if(q==null)
                {  //找到与匹配串相同的串
                    SinglyList<T> list1=new SinglyList<T>(list);
                    front.next=list1.head.next;
                    Node<T> rear=list1.head;
                    while (rear.next!=null) 
                    {
                        rear=rear.next;
                    }
                    rear.next=p;//用list1串代替找到的串，p指向list1串尾后一个结点（下次匹配开始的结点）
                    front=rear;//front指向list1的尾结点（记录p的前一结点）
                }
                else
                {
                	front=front.next;  //本次匹配没找到，front、p都向后移动一个结点准备新一轮匹配
                    p=front.next;
                }
                q = pattern.head.next; //不论是否找到，下次大匹配q都从匹配串的第0个开始
        }
}

public static void main(String args[])
	{
		Integer [] s1={1,2,1,3,4,5,6,7};
		Integer [] s2={1,2,1,3,4,1,2,1,5};
		Integer [] s3={1,2,1,2,1,5,6,7};
		Integer [] s4={1,2,1};
		Integer [] s5={8,9};
		Integer [] s6={1,2,1,3,4,5,6,7};
		Integer [] s7={1,2,3,0};
		SinglyList<Integer> list1=new SinglyList<Integer>(s1);
		SinglyList<Integer> list2=new SinglyList<Integer>(s2);
		SinglyList<Integer> list3=new SinglyList<Integer>(s3);
		SinglyList<Integer> list4=new SinglyList<Integer>(s4);
		SinglyList<Integer> list5=new SinglyList<Integer>(s5);
		SinglyList<Integer> list6=new SinglyList<Integer>(s6);
		SinglyList<Integer> list7=new SinglyList<Integer>(s7);
		list1.replaceAll(list4, list5);
		list2.replaceAll(list4, list5);
		list3.replaceAll(list4, list5);
		list6.replaceAll(list4, list7);
		System.out.println(list1.toString());
		System.out.println(list2.toString());
		System.out.println(list3.toString());
		System.out.println(list6.toString());
	
	}
	
}

    

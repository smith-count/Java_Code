
public class SinglyListReverse 
{
	
    public static <T> void reverse(SinglyList<T> list)
    {
        Node<T> p=list.head.next;      
        Node<T> front=null;
        while(p!=null)
        {
            Node<T> m = p.next;               
            p.next = front;                      
            front = p;
            p = m;                            
        }
        list.head.next = front;                  
    }
  
    public static void main(String args[])
    {
        String[] values={"A","B","C","D","E","F"};
        SinglyList<String> lista = new SinglyList<String>(values);
        System.out.print("lista = "+lista.toString());
        SinglyListReverse.reverse(lista);
        System.out.println("£¬Äæ×ªºó "+lista.toString());
        
//       SinglyList<String> listb = createReverse(values);
 //       System.out.print("listb = "+listb.toString());
//        System.out.println("£¬lista.equals(listb)? "+lista.equals(listb));        
    }
}



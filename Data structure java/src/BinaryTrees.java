public class BinaryTrees
{    
    private static int i=0;
    
    public static BinaryTree<String> createByGenList(String genlist)
    {
        BinaryTree<String> bitree = new BinaryTree<String>();
        i=0;
        bitree.root = create(genlist);          
        return bitree;
    }    
   
    
    private static BinaryNode<String> create(String genlist)
    {
        BinaryNode<String> p = null;
        if(i<genlist.length())
        {
            char ch=genlist.charAt(i);

            if(ch=='^')                          
            {   i++;
                return null;
            }
//            p = new BinaryNode<String>(ch+"");             //创建叶子结点
            p = new BinaryNode<String>(element(genlist,"(,)"));//创建叶子结点，element()返回元素，以(,)分割
            if(i<genlist.length() && genlist.charAt(i)=='(')
            {
                i++;                             
                p.left = create(genlist);        
                i++;                             
                p.right = create(genlist);       
                i++;                             
            }
        }
        return p;                                
    }    
    
    
    private static String element(String genlist, String split)
    {
        char ch=' ';
        int end=i;
        while(end<genlist.length() && split.indexOf(genlist.charAt(end))==-1)
            end++;
        String str = genlist.substring(i, end);  
        i=end;                                   

        return str;        
    }
    
   
   
}

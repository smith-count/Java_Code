public final class MyString implements Comparable<MyString>, java.io.Serializable
{
    private final char[] value;                  //字符数组，私有最终变量，只能赋值一次

    //（1） 构造字符串
    public MyString()                            //构造空串""，串长度为0
    {
        this.value = new char[0];
    }
    
    public MyString(java.lang.String s)          //由字符串常量构造串
    {
        this.value = new char[s.length()];       //申请字符数组并复制s串的所有字符
        for(int i=0;  i<this.value.length;  i++)
            this.value[i] = s.charAt(i);
    }
    
    //以value数组从i开始的n个字符构造串，i≥0，n≥0，i+n≤value.length。
    //若i或n指定序号越界，则抛出字符串序号越界异常
    public MyString(char[] value, int i, int n)
    {
        if(i>=0 && n>=0 && i+n<=value.length)
        {
            this.value = new char[n];            //申请字符数组并复制所有字符
            for(int j=0;  j<n;  j++)
                this.value[j] = value[i+j];
//        java.lang.System.arraycopy(value,0,this.value,0,value.length);  //复制数组，功能for语句
//        this.value = java.util.Arrays.copyOf(value,value.length);       //复制数组，包括申请数组空间
        }
        else
            throw new StringIndexOutOfBoundsException("i="+i+"，n="+n+"，i+n="+(i+n));
    }
    public MyString(char[] value)                //以字符数组构造串
    {
        this(value, 0, value.length);
    }
    public MyString(MyString s)                  //拷贝构造方法，深拷贝
    {
        this(s.value);
    }
    
    public int length()                          //返回串长度，即字符数组容量
    {
        return this.value.length;
    }
    public boolean isEmpty() 
    {
        return value.length == 0;
    }
    public java.lang.String toString() 
    {
        return new String(this.value);           //java.lang.String实现为 return this;
    }
    
    //返回第i个字符，0≤i<length()。若i越界，则抛出字符串序号越界异常
    public char charAt(int i)
    {
        if(i>=0 && i<this.value.length)
            return this.value[i]; 
        throw new StringIndexOutOfBoundsException(i);
    }
    
    //（2） 求子串
    //返回序号从begin至end-1的子串，0≤begin<length()，0≤end≤length()，begin<end；
    ////否则抛出字符串序号越界异常
    public MyString substring(int begin, int end)
    {
        if(begin==0 && end==this.value.length) 
            return this;
        return new MyString(this.value, begin, end-begin); //以字符数组构造串对象，若begin、end越界则抛出异常
    }
    public MyString substring(int begin)         //返回序号从begin至串尾的子串
    {
        return substring(begin, this.value.length);
    }
    
    //（3） 连接串
    //返回this与s串连接生成的串。若s==null或""，则返回深拷贝的this
    public MyString concat(MyString s)                   
    {
        if(s==null || s.equals(""))  
            s = new MyString(this.value);        //深拷贝this
        char[] buffer = new char[this.value.length+s.length()];
        int i;
        for(i=0;  i<this.value.length;  i++)     //复制当前串
            buffer[i] = this.value[i];
        for(int j=0;  j<s.value.length;  j++)    //复制指定串s
            buffer[i+j] = s.value[j];
        return new MyString(buffer);             //以字符数组构造串对象
    }
    //说明：2020年3月30日
    //若s==null，java.lang.String类插入"null"，但是，此含义实际应用中逻辑上不通，故不采用。
    
    //（4） 比较串相等
    //第4版教材省略方法体，课件写
    //比较this与obj引用的串是否相等，覆盖Object类的equals(obj)方法
    public boolean equals(Object obj)
    {
        if(this==obj)
            return true;
        if(obj instanceof MyString)
        {
            MyString s=(MyString)obj;
            if(this.value.length==s.value.length)
            {
                for(int i=0;  i<this.value.length;  i++)
                    if(this.value[i]!=s.value[i])
                        return false;
                return true;
            }
        }
        return false;
    }    

    //（5） 比较串大小
    //比较this与str串的大小，返回两者差值，实现Comparable<>接口
    public int compareTo(MyString s)
    {
        for(int i=0; i<this.value.length && i<s.value.length; i++)
            if(this.value[i]!=s.value[i])
                return this.value[i] - s.value[i];       //返回两串第一个不同字符的差值
        return this.value.length - s.value.length;       //前缀子串，返回两串长度的差值
    }
    //【例3.1】  使用String串的求子串和连接串操作，实现串的插入、删除功能。
    
    //【思考题3-1】
/*    public MyString trim()                       //返回删除所有空格后的字符串。使用字符数组
    {
        MyString result = new MyString("");
        for (int i=0; i<this.length(); i++)
            if (this.charAt(i)!=' ')
                result = result.concat(this.substring(i, i+1));  
        return result;
    }//可行，效率低，改进如下。*/
    public MyString trim()                       //返回删除所有空格后的字符串。使用字符数组
    {
        char[] buffer = new char[this.value.length];       //声明字符数组作为临时变量
        int n=0;
        for(int i=0;  i<this.value.length;  i++) //复制this串一次
            if(this.value[i]!=' ')
                buffer[n++]=this.value[i];
        return new MyString(buffer, 0, n);       //以buffer数组从0开始的n个字符构造，复制buffer一次
    }
    
    //【思考题3-1习题解答】
/*    public int compareToIgnoreCase(MyString str) //比较两个串大小，忽略字母大小写
    {
        for(int i=0;  i<this.value.length && i<str.value.length;  i++)
        {
            int c1=this.value[i], c2=str.value[i];
            if(c1>='a' && c1<='z') 
                c1 -= 'a'-'A';
            if(c2>='a' && c2<='z') 
                c2 -= 'a'-'A';
            if(c1!=c2)
                return this.value[i]-str.value[i];
        }
        return this.value.length-str.value.length;
    }    
*/
    //§3.3   串的模式匹配
    //§3.3.1  Brute-Force算法
    //1.  Brute-Force算法描述与实现
    
    //在this串（目标串）中查找首个与模式串pattern匹配的子串并返回序号，匹配失败时返回-1
    public int indexOf(MyString pattern)
    {
        return this.indexOf(pattern, 0);
    }
    
    //返回this串（目标串）从begin开始首个与模式串pattern匹配的子串序号，匹配失败时返回-1。
    //0≤begin<this.length()。对begin容错，若begin<0，则从0开始；若begin序号越界，则查找不成功。
    //若pattern==null，则抛出空对象异常
    public int indexOf(MyString pattern, int begin)
    {
        int n=this.length(), m=pattern.length();
        if(begin<0)                              //对begin容错，若begin<0，则从0开始
            begin = 0;
        if(n==0 || n<m || begin>=n)              //若目标串空、较短或begin越界，则不需比较
            return -1;
        int i=begin, j=0;                        //i、j分别为目标串和模式串当前字符下标
        int count=0;                             //记载比较次数
        while(i<n && j<m)
        {
            count++;
            if(this.charAt(i)==pattern.charAt(j))//若当前两字符相等，则继续比较后续字符
            {
                System.out.print("t"+i+"=p"+j+"，");
                i++;
                j++;
            }
            else                                 //否则i、j回溯，进行下次匹配
            {
                System.out.println("t"+i+"!=p"+j+"，");
                i=i-j+1;                         //目标串下标i，退回到下个待匹配子串序号
                j=0;                             //模式串下标j，退回到0
                if(i>n-m)                        //若目标串剩余子串的长度<m，则不再比较
                    break;
            }
        }
        System.out.println("\tBF.count="+count);
        return  j==m ? i-m : -1;                 //若匹配成功，则返回匹配子串序号；否则返回-1
    }
    //@author：Yeheya，2019年9月28日 
    
    //《数据结构与算法（Java版）（第5版）试题库》，作者：叶核亚，2015年2月5日，JDK 8.25
    //以下【实验3-1】MyString类增加成员方法
    public int indexOf(char ch)                  //返回ch字符在this串中首次出现的序号
    {
        return indexOf(ch,0);
    }    
    public int indexOf(char ch, int begin)       //返回ch从begin开始首次出现的序号
    {
        if(begin<0)                              //序号容错
            begin = 0;
        for(int i=begin; i<this.value.length; i++)
            if(this.value[i]==ch)
                return i;
        return -1;
    }    
    
    public MyString toLowerCase()                //将串中的大写字母全部转换成对应的小写字母
    {
        char temp[]=new char[this.value.length];
        for(int i=0; i<this.value.length; i++)
            if(this.value[i]>='A' && this.value[i]<='Z')   //大写字母
                temp[i]=(char)(this.value[i]+'a'-'A');     //转换成对应小写字母
        return new MyString(temp);
    }
    
    public MyString toUpperCase()                //将串中的小写字母全部转换成对应的大写字母
    {
        char temp[]=new char[this.value.length];
        for(int i=0;  i<this.value.length;  i++)
            if(this.value[i]>='a' && this.value[i]<='z')   //小写字母
                temp[i]=(char)(this.value[i]-('a'-'A'));   //转换成对应大写字母
        return new MyString(temp); 
    }    
    
    public char[] toCharArray()                  //返回字符数组
    {
        char[] temp = new char[this.value.length];
        for(int i=0; i<temp.length; i++)         //复制数组
        	temp[i] = this.value[i];
        return temp;
    }
    
    public boolean startsWith(MyString prefix)   //判断prefix是否前缀子串
    {
/*        if (this.value.length<prefix.value.length)
            return false;
        for (int i=0; i<prefix.value.length; i++)
            if (this.value[i]!=prefix.value[i])
                return false;
        return true;*/
        return this.indexOf(prefix)==0;//??没试
    }
    public boolean endsWith(MyString suffix)     //判断suffix是否后缀子串
    {
/*        int j=suffix.value.length-1;
        for (int i=this.value.length-1; i>=0 && j>=0; i--,j--)
            if (this.value[i]!=suffix.value[j])
                return false;
        return j==-1;*/
        return this.indexOf(suffix)==this.length()-suffix.length();//??没试
    }
    
    public boolean equalsIgnoreCase(MyString str)//比较this串与str串是否相等，忽略字母大小写
    {
        if(this==str)
            return true;
        if(this.value.length!=str.value.length)
            return false;
        for(int i=0;  i<this.value.length;  i++)
        {
            char c1=this.value[i], c2=str.value[i];
            if(c1>='a' && c1<='z') 
                c1 -= 'a'-'A';
            if(c2>='a' && c2<='z') 
                c2 -= 'a'-'A';
            if(c1!=c2)                          
                return false;
        }
        return true;
    }    
    
    public MyString replaceFirst(MyString pattern, MyString s)
    {
        int i=this.indexOf(pattern), j, k;
        if(i==-1)
            return this;
        int n=this.length(), m=pattern.length();
        char[] buffer=new char[n-m+s.length()];
        for(j=0; j<i; j++)                       //复制this.substring(0,i)
            buffer[j]=this.charAt(j);
        for(j=0; j<s.length(); j++)              //复制s串
            buffer[i+j]=s.charAt(j);
        for(k=i+m; k<n; k++,j++)                 //复制this.substring(i+m)
            buffer[i+j]=this.charAt(k);
        return new MyString(buffer);
    }
    
   
    public MyString replaceAll(MyString pattern, MyString s)
    {
        MyString temp = new MyString(this);      //拷贝构造方法，复制当前串
        int i=this.indexOf(pattern,0);
        while(i!=-1)
        {
            temp = temp.substring(0,i).concat(s).concat(temp.substring(i+pattern.length()));
            i=temp.indexOf(pattern, i+s.length());//从下一个字符开始再次查找匹配子串
//            i=temp.indexOf(pattern, i+1);      //错【习题3-9】改错
        }
        return temp;
    }
    public int compareToIgnoreCase(MyString str) //比较两个串大小，忽略字母大小写
    {
        for(int i=0;  i<this.value.length && i<str.value.length;  i++)
        {
            int c1=this.value[i], c2=str.value[i];
            if(c1>='a' && c1<='z') 
                c1 -= 'a'-'A';
            if(c2>='a' && c2<='z') 
                c2 -= 'a'-'A';
            if(c1!=c2)
                return this.value[i]-str.value[i];
        }
        return this.value.length-str.value.length;
    }    
    public static void main(String args[])
    {
        MyString m = new MyString("abcdef");
        MyString p = new MyString("abzdef");
        System.out.println(m.compareToIgnoreCase(p));
    }
    
}    

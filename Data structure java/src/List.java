
//《数据结构与算法（Java版）（第5版）》，作者：叶核亚，2016年2月19日
//§2.1   线性表抽象数据类型

//ADT List<T>                          //线性表抽象数据类型，T表示数据元素的数据类型
// 线性表接口，描述线性表抽象数据类型，T表示数据元素的数据类型， 方法修饰符默认public abstract 
public interface List<T>
{
    
    boolean isEmpty();                 // 判断是否空，若为空，则返回true
    int size();                        // 返回元素个数（长度）
    T search(T key);                   // 查找并返回首个与key相等元素；若查找不成功，则返回null
    T remove(T key);                   // 查找并删除首个与key相等元素，返回被删除元素
    void clear();                      // 删除所有元素
    String toString();                 // 返回所有元素的描述字符串
    boolean equals(Object obj);        // 比较this与obj引用线性表的所有元素是否对应相等
    
    // 线性表增加以下方法，参数i表示元素序号，指定操作位置
    T get(int i);                      // 返回第i个元素
    void set(int i, T x);              // 设置第i个元素为x   ////不要返回值
    int insert(int i, T x);            // 插入x作为第i个元素
    int insert(T x);                   // 在最后插入x元素
    T remove(int i);                   // 删除第i个元素，返回被删除元素
}
/*
说明如下。
（1）List<T>声明以下，强调重点。后续章可不声明。
    String toString();                           //返回所有元素的描述字符串
    boolean equals(Object obj);                  //比较this与obj引用线性表的所有元素是否对应相等

（2）java.util.List<T>声明以下方法，ADT修改为search()，更适合两种存储结构。
    public int indexOf(T key)

（3）ADT没有声明迭代函数，参数无法统一，只有迭代器能。

（4）List<T>中不能声明以下集合运算：
    boolean addAll(List<T> list);                //集合并运算
因为，顺序表和单链表类分别实现如下，遍历方式不同，不能实现上述参数。
    public boolean addAll(SeqList<T> list)
    public boolean addAll(SinglyList<T> list)
教材没有给出其他集合运算，作为实验题了。

（5）所以，第2章无法声明 extends Set<T>，因为无法说明集合运算。 
//不能继承Set<T>集合接口（见例1.1），增加指定元素序号i的方法
    java.util.List<T>继承java.util.Collection<T>，因为有迭代器。

（6）只能用ADT，无法声明接口，因为以下方法声明不同，没有声明add()，需要的返回值类型不同。
    int insert(int i, T x);                      //插入x作为第i个元素
    int insert(T x);                             //在线性表最后插入x元素
    //不用声明以下，查找已包括该功能
    boolean contains(T key);           //判断是否包含关键字为key元素

*/
//@author：Yeheya，2016-2-19，2019年6月23日
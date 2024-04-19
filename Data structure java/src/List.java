
//�����ݽṹ���㷨��Java�棩����5�棩�������ߣ�Ҷ���ǣ�2016��2��19��
//��2.1   ���Ա������������

//ADT List<T>                          //���Ա�����������ͣ�T��ʾ����Ԫ�ص���������
// ���Ա�ӿڣ��������Ա�����������ͣ�T��ʾ����Ԫ�ص��������ͣ� �������η�Ĭ��public abstract 
public interface List<T>
{
    
    boolean isEmpty();                 // �ж��Ƿ�գ���Ϊ�գ��򷵻�true
    int size();                        // ����Ԫ�ظ��������ȣ�
    T search(T key);                   // ���Ҳ������׸���key���Ԫ�أ������Ҳ��ɹ����򷵻�null
    T remove(T key);                   // ���Ҳ�ɾ���׸���key���Ԫ�أ����ر�ɾ��Ԫ��
    void clear();                      // ɾ������Ԫ��
    String toString();                 // ��������Ԫ�ص������ַ���
    boolean equals(Object obj);        // �Ƚ�this��obj�������Ա������Ԫ���Ƿ��Ӧ���
    
    // ���Ա��������·���������i��ʾԪ����ţ�ָ������λ��
    T get(int i);                      // ���ص�i��Ԫ��
    void set(int i, T x);              // ���õ�i��Ԫ��Ϊx   ////��Ҫ����ֵ
    int insert(int i, T x);            // ����x��Ϊ��i��Ԫ��
    int insert(T x);                   // ��������xԪ��
    T remove(int i);                   // ɾ����i��Ԫ�أ����ر�ɾ��Ԫ��
}
/*
˵�����¡�
��1��List<T>�������£�ǿ���ص㡣�����¿ɲ�������
    String toString();                           //��������Ԫ�ص������ַ���
    boolean equals(Object obj);                  //�Ƚ�this��obj�������Ա������Ԫ���Ƿ��Ӧ���

��2��java.util.List<T>�������·�����ADT�޸�Ϊsearch()�����ʺ����ִ洢�ṹ��
    public int indexOf(T key)

��3��ADTû���������������������޷�ͳһ��ֻ�е������ܡ�

��4��List<T>�в����������¼������㣺
    boolean addAll(List<T> list);                //���ϲ�����
��Ϊ��˳���͵�������ֱ�ʵ�����£�������ʽ��ͬ������ʵ������������
    public boolean addAll(SeqList<T> list)
    public boolean addAll(SinglyList<T> list)
�̲�û�и��������������㣬��Ϊʵ�����ˡ�

��5�����ԣ���2���޷����� extends Set<T>����Ϊ�޷�˵���������㡣 
//���ܼ̳�Set<T>���Ͻӿڣ�����1.1��������ָ��Ԫ�����i�ķ���
    java.util.List<T>�̳�java.util.Collection<T>����Ϊ�е�������

��6��ֻ����ADT���޷������ӿڣ���Ϊ���·���������ͬ��û������add()����Ҫ�ķ���ֵ���Ͳ�ͬ��
    int insert(int i, T x);                      //����x��Ϊ��i��Ԫ��
    int insert(T x);                             //�����Ա�������xԪ��
    //�����������£������Ѱ����ù���
    boolean contains(T key);           //�ж��Ƿ�����ؼ���ΪkeyԪ��

*/
//@author��Yeheya��2016-2-19��2019��6��23��
package homework;

public class change
{
    public static String money(double n)
    {

        //����λ��������
        String company="��ǧ��ʰԪ�Ƿ�";
        String num="Ҽ��������½��ƾ�";
        int num1=(int)(n*100);
        String result="";
        if(num1%10==0 && num1%100==0)
        {
            if(num1==10)
                result="ʰԪ��";
            else
            {num1/=100;
                for(int i=company.length()-3;i>=0 && num1>0;i--,num1/=10)
                {
                    result = ""+num.charAt(num1%10-1)+company.charAt(i)+result;
                }
                result=result+"��";
            }
        }
        return result;

    }


    public static void main(String[] args)
    {
        double n=1129.00;
        String var;
        var=money(n);
        System.out.print(var);
    }
}

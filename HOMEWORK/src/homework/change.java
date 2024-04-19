package homework;

public class change
{
    public static String money(double n)
    {

        //所有位都是整数
        String company="万千佰拾元角分";
        String num="壹贰叁肆伍陆柒捌玖";
        int num1=(int)(n*100);
        String result="";
        if(num1%10==0 && num1%100==0)
        {
            if(num1==10)
                result="拾元整";
            else
            {num1/=100;
                for(int i=company.length()-3;i>=0 && num1>0;i--,num1/=10)
                {
                    result = ""+num.charAt(num1%10-1)+company.charAt(i)+result;
                }
                result=result+"整";
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

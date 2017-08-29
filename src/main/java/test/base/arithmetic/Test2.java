package test.base.arithmetic;

import org.junit.Test;

import java.util.Scanner;

/**
 * 描述:大数据乘法
 *
 * @author: mylover
 * @Time: 28/08/2017.
 */
public class Test2 {

    @Test
    public void test1() {

        String str1 = "23456789009877666555544444";
        String str2 = "346587436598437594375943875943875";

        int len1 = str1.length();
        int len2 = str2.length();

        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        // 高低位对调
        covertdata(s1, len1);
        covertdata(s2, len2);

        System.out.println("乘数："+str1);
        System.out.println("乘数："+str2);
        multiply(s1, len1, s2, len2);

    }

    public static void covertdata(char data[], int len) {
        //高低位对调
        for (int i = 0; i < len / 2; i++) {
            data[i] += data[len - 1 - i];
            data[len - 1 - i] = (char) (data[i] - data[len - 1 - i]);
            data[i] = (char) (data[i] - data[len - 1 - i]);
        }
    }

    public static void multiply(char a[], int alen, char b[], int blen) {
        // 两数乘积位数不会超过乘数位数和+ 3位
        int csize = alen + blen + 3;
        // 开辟乘积数组
        int[] c = new int[csize];
        // 乘积数组填充0
        for (int ii = 0; ii < csize; ii++) {
            c[ii] = 0;
        }
        // 对齐逐位相乘
        for (int j = 0; j < blen; j++) {
            for (int i = 0; i < alen; i++) {
                c[i + j] +=  Integer.parseInt(String.valueOf(a[i]))* Integer.parseInt(String.valueOf(b[j]));
            }
        }
        int m = 0;
        // 进位处理
        for (m = 0; m < csize; m++) {
            int carry = c[m] / 10;
            c[m] = c[m] % 10;
            if (carry > 0)
                c[m + 1] += carry;
        }
        // 找到最高位
        for (m = csize - 1; m >= 0;) {
            if (c[m] > 0)
                break;
            m--;
        }
        // 由最高位开始打印乘积
        System.out.print("乘积：");
        for (int n = 0; n <= m; n++) {
            System.out.print(c[m - n]);
        }
        System.out.println("");
    }


    @Test
    public void test2() {
        Scanner in = new Scanner(System.in);
        Test2 multiply = new Test2();
        System.out.println("请输入第一个大数：");
        while(in.hasNext())
        {
            Init();//初始化,数组置0
            String strNum1 = in.next();
            System.out.println("请输入第二个大数：");
            String strNum2 = in.next();
            System.out.println(strNum1+"*"+strNum2+" = "+multiply.getMulRes(strNum1,strNum2));
            System.out.println("请输入第一个大数：");
        }
    }


    /**
     * 实现两个大数相乘
     * @param args
     */
    public static final int MAX_LEN = 10000000;
    public static int[] tempArray1 = new int[MAX_LEN];
    public static int[] tempArray2 = new int[MAX_LEN];//这两个数组用来暂时存储局部结果

    //初始化数组函数
    public static void Init() {
        for(int i=0;i<MAX_LEN;i++)
        {
            tempArray1[i] = 0;
            tempArray2[i] = 0;
        }
    }

    //实现大数相乘的函数
    public static String mul(String num1,String num2)
    {
        String result = "";
        int len1 = num1.length();
        int len2 = num2.length();
        int tempNum1 = 0,tempNum2 = 0;//num1和num2里拆出来相乘的单个数
        int pre = 0;//进位，初始化为0
        int mulRes = 0;//乘积
        for(int i=0;i<len2;i++)
        {
            int loc_begin = 0;//往数组里存放一轮计算结果的开始位置
            tempNum2 = num2.charAt(i)-'0';
            for(int j=0;j<len1;j++)
            {
                tempNum1 = num1.charAt(j)-'0';
                mulRes = tempNum1*tempNum2+pre;
                if(i==0)
                {
                    if(j==len1-1){//一轮计算结束
                        tempArray1[loc_begin] = mulRes;
                        pre = 0;//一轮存储完毕，进位置0
                    }
                    else//一轮计算没有结束
                    {
                        pre = mulRes/10;//进位
                        tempArray1[loc_begin] = mulRes%10;//进位后保存结果

                    }
                    loc_begin++;
                }
                else
                {
                    if(j==len1-1){//一轮计算结束
                        tempArray2[loc_begin] = mulRes;
                        pre = 0;//一轮存储完毕，进位置0
                    }
                    else//一轮计算没有结束
                    {
                        pre = mulRes/10;//进位
                        tempArray2[loc_begin] = mulRes%10;//进位后保存结果
                    }
                    loc_begin++;
                }
            }
            if(i>0)
            {

                posSum(i,loc_begin-1);//错位求和，结果存放在tempArray1中,i是错位数
            }
        }
        boolean boo = true;
        for(int k=tempArray1.length-1;k>=0;k--)
        {

            if(tempArray1[k]==0 && boo)
            {
                continue;
            }
            else
            {
                boo = false;
                result = result+tempArray1[k];
            }
        }
        if(result=="")
            result = "0";
        return result;
    }
    //错位求和
    public static void posSum(int n,int len)
    {
        int pre = 0;
        int sum = 0;
        int i=0;
        int j=0;
        while(true)
        {
            if(i>=n)
            {
                sum = tempArray1[i] + tempArray2[j] + pre;
                if(j<len)
                {
                    tempArray1[i] = sum%10;
                    pre = sum/10;
                }
                if(j==len)
                {
                    tempArray1[i] = sum;
                    break;
                }
                j++;
            }
            i++;
        }
    }
    public String getMulRes(String num1,String num2)
    {
        boolean boo = true;//标记结果是否是正数，初始化假设是正数
        String result = null;//存放两个大数相乘的结果。
        //判断两个大数均为负数
        if(num1.charAt(0)=='-' && num2.charAt(0)=='-')
        {
            num1 = (String)num1.subSequence(1, num1.length());
            num2 = (String)num2.subSequence(1, num2.length());
        }
        else if(num1.charAt(0)=='-' || num2.charAt(0)=='-')//其中一个为负数
        {
            boo = false;//一正一负，结果为负数。
            if(num1.charAt(0)=='-')
            {
                num1 = (String)num1.subSequence(1, num1.length());
            }
            else if(num2.charAt(0)=='-')
            {
                num2 = (String)num2.subSequence(1, num2.length());
            }
        }
        num1 = reverse(num1);
        num2 = reverse(num2);
        if(num1.length()<=num2.length())
            result = mul(num2,num1);
        else
            result = mul(num1,num2);
        if(!boo&&!result.equals("0"))//如果结果为负数，则在前面加‘-’号
            result = "-"+result;
        return result;
    }
    //字符串取反,如：(123-->321),方便之后求积
    public static String reverse(String str)
    {
        char[] strarray = str.toCharArray();
        StringBuffer sbf = new StringBuffer();
        for (int i = strarray.length - 1; i >= 0; i--)
            sbf.append(strarray[i]);
        return sbf.toString();
    }

}

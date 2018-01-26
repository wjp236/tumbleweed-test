package com.tumbleweed.test.base.arithmetic;

import org.junit.Test;

import java.util.*;
import java.util.Map.Entry;

/**
 * 描述:测试
 *
 * @author: mylover
 * @Time: 25/12/2017.
 */
public class WeightRandomTest {


    @Test
    public void test1 (){
        int a = 0;
        int b = 0;
        int c = 0;
        int d =0;
        int e = 0;
        int f = 0;

        int i = 10;
        for (int j = 0; j < 10000000; j++) {
            switch (i = doRamdon()) {
                case 0:
                    a++;
                    break;
                case 1:
                    b++;
                    break;
                case 2:
                    c++;
                    break;
                case 3:
                    d++;
                    break;
                case 4:
                    e++;
                    break;
                case 5:
                    f++;
                    break;
                default:
                    System.out.println(">>>>>>>>>>>>" + i);
                    break;
            }
        }

        System.out.println("a : " + a + "     b : " + b + "     c ：" + c+ "     d ：" + d+ "    e ：" + e+ "    f  ：" + f);
        System.out.println(a+b+c+d+e+f);

    }

    private static int doRamdon() {
        double[] ds = new double[] {1.0 , 50.0 , 4.0 , 15.0 , 10.0 , 20.0  };
        double sum = getSum(ds);
        double last = 0;
        for (int i = 0; i < ds.length; i++) {
            sum = sum- last;
            double random = Math.random();
            if (random <= ds[i] / sum) {
                return i;
            }
            last = ds[i];
        }

        return 5;
    }

    private static double getSum(double[] weight) {
        double sum = 0;
        for (double d : weight) {
            sum += d;
        }
        return sum;
    }

    @Test
    public void test2() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 20);
        map.put("B", 10);
        map.put("C", 40);
        map.put("D", 30);

        //获取权重总和
        Integer sum = map.values().parallelStream().reduce(Integer::sum).get();
        //测试500W次
        Integer total = 5000000;

        //第一种方式
        int a = 0, b = 0, c = 0, d = 0;
        for (int i = 0; i < total; i++) {
            Integer random = new Random().nextInt(sum);
            for (String str : map.keySet()) {
                int weight = map.get(str);
                if (random > weight) {
                    random -= weight;
                } else {
                    switch (str) {
                        case "A":
                            a++;
                            break;
                        case "B":
                            b++;
                            break;
                        case "C":
                            c++;
                            break;
                        case "D":
                            d++;
                            break;
                    }
                    break;
                }
            }
        }
        System.out.println("第一种方式：");
        System.out.println("A:" + a + "  占比：" + ((float) a / total));
        System.out.println("B:" + b + "  占比：" + ((float) b / total));
        System.out.println("C:" + c + "  占比：" + ((float) c / total));
        System.out.println("D:" + d + "  占比：" + ((float) d / total));
    }

    @Test
    public void test3() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 20);
        map.put("B", 10);
        map.put("C", 40);
        map.put("D", 30);
        //获取权重总和
        Integer sum = map.values().parallelStream().reduce(Integer::sum).get();
        //测试500W次
        Integer total = 5000000;

        List<String> list = new ArrayList<>();
        map.keySet().forEach(key -> {
            int value = map.get(key);
            for (int i = 0; i < value; i++) {
                list.add(key);
            }
        });
        Collections.shuffle(list);
        int A = 0, B = 0, C = 0, D = 0;
        for (int i = 0; i < total; i++) {
            String str = list.get(new Random().nextInt(sum));
            switch (str) {
                case "A":
                    A++;
                    break;
                case "B":
                    B++;
                    break;
                case "C":
                    C++;
                    break;
                case "D":
                    D++;
                    break;
            }
        }
        System.out.println("\n第二种方式：");
        System.out.println("A:" + A + "  占比：" + ((float) A / total));
        System.out.println("B:" + B + "  占比：" + ((float) B / total));
        System.out.println("C:" + C + "  占比：" + ((float) C / total));
        System.out.println("D:" + D + "  占比：" + ((float) D / total));
    }

    static Map<Integer , Integer > ds = new HashMap<Integer , Integer> () ;


    @Test
    public void test4() {

        ds.put(50, 0);
        ds.put(1, 0);
        ds.put(4, 0);
        ds.put(15, 0);
        ds.put(10, 0);
        ds.put(20, 0);


        System.out.println(ds);

        for (int i = 0; i < 10000000; i++) {
            int random = org.apache.commons.lang.math.RandomUtils.nextInt(100);
            random++;
            for (int key : ds.keySet()) {
                if(getRamdon(random, key)){
                    ds.put(key, ds.get(key)+1 );
                    break;
                }else {
                    random -= key;
                }
            }
        }

        System.out.println(ds);


        int sum=0;
        for (Iterator iterator = ds.entrySet().iterator(); iterator.hasNext();) {
            Entry<Integer, Integer> entry = (Entry<Integer, Integer>) iterator.next();
            sum=sum+entry.getValue();
        }

        System.out.println(sum);
    }

    private static boolean getRamdon(int random ,  int randomNum) {
        int belance = randomNum - random;
        if(belance >= 0) {
            return true;
        }
        return false;
    }

}

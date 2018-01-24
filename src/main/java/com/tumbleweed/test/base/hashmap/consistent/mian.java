package com.tumbleweed.test.base.hashmap.consistent;

/**
 * 描述:
 *
 * @author: mylover
 * @Time: 11/01/2018.
 */
public class mian {

    public static void main(String[] args) {
        Cluster cluster = createCluster();
        cluster.put("asdf","123");
        cluster.put("ghjkl","456");
        cluster.put("qwert","789");
        cluster.put("yuiop","123");
        cluster.addServer(new Server("lanzhou2"));
        String[] str = new String[]{"asdf","ghjkl","qwert","yuiop"};
        find(cluster, str);
    }

    public static Cluster createCluster(){
        Cluster c = new Cluster();
        c.addServer(new Server("beijing"));
        c.addServer(new Server("jining"));
        c.addServer(new Server("lanzhou"));
        c.addServer(new Server("linyi"));
        c.addServer(new Server("hangzhou"));
        return c;
    }

    public static void find(Cluster c, String[] str){
        for (String s:str){
            if(c.get(s) != null && !c.get(s).isEmpty()){
                System.out.println("命中"+c.get(s));
            }else {
                System.out.println("缓存失效");
            }
        }
    }


}

package com.ysh.maven.mavendemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BalanceTest {

    private static List<String>serverList=new ArrayList<String>();

    private static  Map<String,Integer>serverMap=new HashMap<String,Integer>();

    private  static final Logger log= LoggerFactory.getLogger(BalanceTest.class);

    static {
        serverList.add("192.169.1.2");
        serverList.add("192.169.1.3");
        serverList.add("192.169.1.4");
        serverList.add("192.169.1.5");
    }

    static {
        serverMap.put("192.169.1.2",2);
        serverMap.put("192.169.1.3",2);
        serverMap.put("192.169.1.4",2);
        serverMap.put("192.169.1.5",4);
    }

    private static Integer index=0;
    /**
     * 随机路由算法
     * @return
     */
    public  static String random(){
        //复制遍历用的集合，防止操作中集合有变更
        List<String>tempList=new ArrayList<String>(serverList.size());
        tempList.addAll(serverList);
        //随机访问
        Integer randomInt=new Random().nextInt(tempList.size());
        return  tempList.get(randomInt);
    }

    /**
     *模拟轮询随机访问路由
     * @return
     */
    private static  String randomOneByOne(){
        List<String>tempList=new ArrayList<String>(serverList.size());
        tempList.addAll(serverList);
        String server="";
        synchronized (index){
            index++;
            if (index==tempList.size()){
                index=0;
            }
            server=tempList.get(index);
        }
        return  server;
    }

    /**
     * 模拟加权轮询路由访问算法
     * @return
     */
    public static String oneByoneWithWeight(){
        Map<String,Integer>tempMap=new HashMap<String,Integer>();
        List<String>tempList=new ArrayList<String>();
        tempMap.putAll(serverMap);
        for (String key:serverMap.keySet()){
            for (int i = 0; i <serverMap.get(key) ; i++) {
                tempList.add(key);
            }
        }
        synchronized (index){
            index++;
            if (index==tempList.size()){
                index=0;
            }
            return  tempList.get(index);
        }
    }

    /**
     * 模拟加权路由随机访问算法
     * @return
     */
    public static  String randomWithWeight(){
        Map<String,Integer>tempMap=new HashMap<String,Integer>();
        tempMap.putAll(serverMap);
        List<String>tempList=new ArrayList<String>();
        for (String key:serverMap.keySet()){
            for (int i = 0; i <serverMap.get(key) ; i++) {
                tempList.add(key);
            }
        }
        Integer randomInt=new Random().nextInt(tempList.size());
        return  tempList.get(randomInt);
    }

    //ip hash 路由算法
    public static  String ipHash(String ip){
        List<String>tempList=new ArrayList<String>(serverList.size());
        tempList.addAll(serverList);
        //哈希计算请求的服务器
        int index=ip.hashCode()%serverList.size();
        return tempList.get(Math.abs(index));
    }
    public static void main(String[] args) {
        Map<String,Integer>serverMap=new HashMap<String,Integer>();

        for (int i = 0; i <200000 ; i++) {
            //情况一：模拟随机访问
            //  String server=random();

            //情况二：模拟轮询访问
            String server=randomOneByOne();
          Integer count=serverMap.get(server);
            if (count == null) {
                count=1;
            }else {
                count++;
            }
            serverMap.put(server,count);
        }
        //路由总体结果
        for (Map.Entry<String,Integer>entry:
             serverMap.entrySet()) {
            log.info("IP :"+ entry.getKey()+", 次数： "+ entry.getValue());
        }
    }
}

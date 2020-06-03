package com.ysh.maven.mavendemo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class BalanceTest {

    private static List<String>serverList=new ArrayList<String>();

    private  static final Logger log= LoggerFactory.getLogger(BalanceTest.class);

    static {
        serverList.add("192.169.1.2");
        serverList.add("192.169.1.3");
        serverList.add("192.169.1.4");
        serverList.add("192.169.1.5");
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
     *
     * @return
     */
    private static  String randomOneByOne(){
        List<String>tempList=new ArrayList<String>(serverList.size());
        tempList.addAll(tempList);
        String server="";
        synchronized (index){
            index++;
            if (index==tempList.size()){
                index=0;
            }
            server=serverList.get(index);
        }
        return  server;
    }

    public static void main(String[] args) {
        Map<String,Integer>serverMap=new HashMap<String,Integer>();

        for (int i = 0; i <200000 ; i++) {
          String server=random();
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

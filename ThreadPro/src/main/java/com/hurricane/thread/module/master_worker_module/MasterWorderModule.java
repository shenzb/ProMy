package com.hurricane.thread.module.master_worker_module;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Set;

/**
 * Created by zhenbiao.shen on 2016/6/15.
 */
public class MasterWorderModule {

    public static void main(String[] args) {

        try {
            long start = System.currentTimeMillis();
            Master master = new Master(new PlusWorker(), 10);
            for (int i = 1; i <= 10000; i++) {
                master.submit((long) i);

            }
            master.execute();
            Map<String, Object> resultMap = master.getResultMap();
            long result = 0;
            while (resultMap.size() > 0 || !master.isComplete()) {
                Set<String> resultKeySet = resultMap.keySet();
                String key = "";
                for (String str : resultKeySet) {
                    key = str;
                    break;
                }
                if (StringUtils.isNotEmpty(key)) {
                    result += (Long) resultMap.get(key);
                }
                if (StringUtils.isNotEmpty(key)) {
                    resultMap.remove(key);
                }
            }
            System.out.println("result:" + result);
            System.out.println("time:" + (System.currentTimeMillis() - start));
            long start2 = System.currentTimeMillis();
            long reslut2 = 0;
            for (int j = 1; j <= 10000; j++) {
                reslut2 += (j * j * j);
                Thread.sleep(20);
            }
            System.out.println("result2:" + reslut2);
            System.out.println("time2:" + (System.currentTimeMillis() - start2));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

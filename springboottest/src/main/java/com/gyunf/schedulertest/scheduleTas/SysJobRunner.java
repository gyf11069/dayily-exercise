package com.gyunf.schedulertest.scheduleTas;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author gyf
 * @date 2023-11-13 16:10
 */
@Service
public class SysJobRunner implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SysJobRunner.class);

//    @Autowired
//    private ISysJobRepository sysJobRepository;

    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @Override
    public void run(String... args) {
        if (args != null && args.length > 0) {
            // 初始加载数据库里状态为正常的定时任务
//        List<SysJobPO> jobList = sysJobRepository.getSysJobListByStatus(SysJobStatus.NORMAL.ordinal());
            List<Map<String,Object>> jobList = new ArrayList<>();
            Map<String,Object> map1 = new HashMap<>();
            map1.put("beanName","gyunftest1");
            map1.put("corn","0/1 * * * * ?");
            if ("1".equals(args[0])){
                jobList.add(map1);
            }

            Map<String, Object> map2 = new HashMap<>();
            map2.put("beanName","gyunftest2");
            map2.put("corn","0/2 * * * * ?");
            if ("2".equals(args[0])){
                jobList.add(map2);
            }
            Map<String, Object> map3 = new HashMap<>();
            map3.put("beanName","gyunftest3");
            map3.put("corn","0/3 * * * * ?");
            if ("3".equals(args[0])){
                jobList.add(map3);
            }
            Map<String, Object> map4 = new HashMap<>();
            map4.put("beanName","gyunftest4");
            map4.put("corn","0/4 * * * * ?");
            if ("4".equals(args[0])){
                jobList.add(map4);
            }
            Map<String, Object> map5 = new HashMap<>();
            map5.put("beanName","gyunftest5");
            map5.put("corn","0/5 * * * * ?");
            if ("5".equals(args[0])){
                jobList.add(map5);
            }
            Map<String, Object> map6 = new HashMap<>();
            map6.put("beanName","gyunftest6");
            map6.put("corn","0/6 * * * * ?");
            if ("6".equals(args[0])){
                jobList.add(map6);
            }
            if (!CollectionUtils.isEmpty(jobList)) {
                for (Map<String,Object> job : jobList) {
                    SchedulingRunnable task = new SchedulingRunnable("testController", "addTask", args[0]);
                    /*先清除当前任务*/
                    cronTaskRegistrar.removeCronTask(task);
                    /*再添加新任务*/
                    cronTaskRegistrar.addCronTask(task, job.get("corn").toString());
                }

                logger.info("定时任务已加载完毕...");
            }
        }

    }
}

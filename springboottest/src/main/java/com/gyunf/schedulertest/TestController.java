package com.gyunf.schedulertest;

import com.gyunf.schedulertest.scheduleTas.CronTaskRegistrar;
import com.gyunf.schedulertest.scheduleTas.SchedulingRunnable;
import com.gyunf.schedulertest.scheduleTas.SysJobRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.TriggerTask;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ScheduledFuture;

/**
 * @author gyf
 * @date 2023-11-13 15:31
 */
@RestController
public class TestController {

//    @Autowired
//    ScheduledConfig scheduledConfig;
    @Autowired
    private SysJobRunner sysJobRunner;
    @Autowired
    private CronTaskRegistrar cronTaskRegistrar;

    @GetMapping("/test")
    public void test(String id) throws NoSuchFieldException {
//        Map<String, ScheduledFuture<?>> taskRegistrar = (Map<String, ScheduledFuture<?>>) Utils.getProperty(scheduledConfig, "taskFutures");
//        System.out.println(taskRegistrar.size());
//        scheduledConfig.addTask(id,triggerTask(id,"0/1 * * * * ?"));
        sysJobRunner.run(id);
    }


    @GetMapping("/cancle")
    public void cancleTask(String id) throws NoSuchFieldException {
//        Map<String, ScheduledFuture<?>> taskRegistrar = (Map<String, ScheduledFuture<?>>) Utils.getProperty(scheduledConfig, "taskFutures");
//        System.out.println(taskRegistrar.size());
//        scheduledConfig.cancelTriggerTask(id);
        SchedulingRunnable task = new SchedulingRunnable("testController", "addTask", id);
        cronTaskRegistrar.removeCronTask(task);
    }

    @GetMapping("/addTask")
    public void addTask(String id) throws NoSuchFieldException {
        System.out.println("添加任务，任务id：" + id);
        
    }
}

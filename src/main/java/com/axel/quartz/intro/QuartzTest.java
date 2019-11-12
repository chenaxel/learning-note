package com.axel.quartz.intro;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/10/24
 */
@Slf4j
public class QuartzTest {

	public static void main(String[] args) throws SchedulerException {
		Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
				.withIdentity("helloJob", "group")
				.build();

		Trigger trigger = TriggerBuilder.newTrigger()
				.withIdentity("trigger1", "group1")
				.startNow()
				.forJob(jobDetail)
				.build();

		Trigger cronTrigger = TriggerBuilder
				.newTrigger()
				.withIdentity("trigger2", "group1")
				.forJob(jobDetail)
				.withSchedule(CronScheduleBuilder.cronSchedule("1 0/1 * * * ? *"))
				.build();

		scheduler.scheduleJob(jobDetail, trigger);
		scheduler.scheduleJob(cronTrigger);
		scheduler.start();
	}
}

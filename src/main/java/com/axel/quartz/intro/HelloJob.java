package com.axel.quartz.intro;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * des
 *
 * @author chenzhaohui
 * @date 2019/10/24
 */
@Slf4j
public class HelloJob implements Job {
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		log.info("hello job start");
	}
}

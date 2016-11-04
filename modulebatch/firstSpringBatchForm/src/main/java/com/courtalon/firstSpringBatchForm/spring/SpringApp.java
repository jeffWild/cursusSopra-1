package com.courtalon.firstSpringBatchForm.spring;

import java.util.*;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.courtalon.firstSpringBatchForm.beans.*;

public class SpringApp {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("vente-job-batch.xml");
		
		// pour lancer un traitement batch
		// on récupere le launcher de la tache
		JobLauncher jobLauncher = ctx.getBean("jobLauncher", JobLauncher.class);
		
		// et la tache a executer
		Job job = ctx.getBean("myFirstJob", Job.class);
		
		
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Resultat: " + execution.getStatus());
		} catch (JobExecutionAlreadyRunningException e) {e.printStackTrace();}
		catch (JobRestartException e) {	e.printStackTrace();}
		catch (JobInstanceAlreadyCompleteException e) {e.printStackTrace();	}
		catch (JobParametersInvalidException e) {e.printStackTrace();}
		
		System.out.println("done...");
	}





}

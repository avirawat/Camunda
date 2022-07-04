package com.camunda.training;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RejectionNotificationDelegate implements JavaDelegate {

	  private static final Logger LOG = LoggerFactory.getLogger(RejectionNotificationDelegate.class);

	  @Override
	  public void execute(DelegateExecution execution) throws Exception {
	    LOG.info("execute {} of process instance {}", execution.getCurrentActivityName(), execution.getProcessInstanceId());
	  }

	}

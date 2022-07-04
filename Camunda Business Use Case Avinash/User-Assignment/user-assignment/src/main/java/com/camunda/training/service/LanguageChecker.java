package com.camunda.training.service;

import org.camunda.bpm.engine.delegate.BpmnError; 
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service
public class LanguageChecker implements JavaDelegate {

	/**
	 * @author AvinashRavat
	 * @param  execution
	 * @return This Service is checking if languages are duplicate or not
	 */
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		try {
			String language = (String) execution.getVariable("languageType");
			String languageList = (String) execution.getVariable("languageList");
			if (languageList.contains(language)) {
				throw new RuntimeException("dup_language_error");
			}

			languageList = languageList + "," + language;
			execution.setVariable("languageList", languageList);
		} catch (Exception e) {
			throw new BpmnError("dup_language_error");
		}

	}

}

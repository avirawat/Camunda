package com.camunda.training.model;

import javax.persistence.Entity; 
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetail {
	
	@Id
	@GeneratedValue(generator = "requestID", strategy = GenerationType.AUTO) //Auto ID Generation
	@SequenceGenerator(name = "requestID", sequenceName = "requestID_seq", initialValue = 100, allocationSize = 1)
	private Integer requestID;
	
	private String requesterName;
	private String requestStatus;
	private String requestDueDate;
	
	public RequestDetail(String requesterName, String requestStatus, String requestDueDate) {
		super();
		this.requesterName = requesterName;
		this.requestStatus = requestStatus;
		this.requestDueDate = requestDueDate;
	}
	
	

}

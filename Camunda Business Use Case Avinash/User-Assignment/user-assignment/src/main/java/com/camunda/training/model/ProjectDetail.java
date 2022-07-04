package com.camunda.training.model;

import java.time.LocalDate; 

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
public class ProjectDetail {

	@Id
	@GeneratedValue(generator = "id", strategy = GenerationType.AUTO) //Auto ID Generation
	@SequenceGenerator(name = "id", sequenceName = "id_seq", initialValue = 100, allocationSize = 1)
	private Integer id;
	
	private String projectName;
	private String episodeTitle;
	private String priority;
	private String companyName;
	private String territoryTitle;
	private String description;
	private LocalDate projectDueDate;
	
	public ProjectDetail(String projectName, String episodeTitle, String priority, String companyName,
			String territoryTitle, String description, LocalDate projectDueDate) {
		super();
		this.projectName = projectName;
		this.episodeTitle = episodeTitle;
		this.priority = priority;
		this.companyName = companyName;
		this.territoryTitle = territoryTitle;
		this.description = description;
		this.projectDueDate = projectDueDate;
	}
	

}
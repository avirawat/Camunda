package com.camunda.training.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.camunda.training.model.ProjectDetail;

@Repository
public interface ProjectDetailDAOInterface extends JpaRepository<ProjectDetail,Integer> {

}

package com.qa.TDL.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.TDL.persistance.domain.TasksDomain;

@Repository
public interface TaskRepo extends JpaRepository<TasksDomain,Long> {

}

package com.qa.springtdl.services;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.springtdl.persistance.domain.TasksDomain;

@Repository
public interface TaskRepo extends JpaRepository<TasksDomain,Long> {

}

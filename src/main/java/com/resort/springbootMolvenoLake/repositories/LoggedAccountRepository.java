package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.LoggedAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;

@Repository
public interface LoggedAccountRepository extends CrudRepository<LoggedAccount, Long> {

}
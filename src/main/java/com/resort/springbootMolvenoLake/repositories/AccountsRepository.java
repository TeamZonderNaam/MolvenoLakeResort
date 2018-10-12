package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Long> {
}
package com.resort.springbootMolvenoLake.repositories;

import com.resort.springbootMolvenoLake.models.MenuItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Integer> {
}

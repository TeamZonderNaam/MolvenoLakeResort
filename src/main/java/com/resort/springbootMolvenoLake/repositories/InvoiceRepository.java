package com.resort.springbootMolvenoLake.repositories;


import com.resort.springbootMolvenoLake.models.Invoice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long> {
}

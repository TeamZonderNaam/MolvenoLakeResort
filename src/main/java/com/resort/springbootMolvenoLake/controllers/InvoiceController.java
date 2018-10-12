package com.resort.springbootMolvenoLake.controllers;


import com.resort.springbootMolvenoLake.models.Invoice;
import com.resort.springbootMolvenoLake.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/invoice/")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @RequestMapping(method = RequestMethod.GET)
    public Iterable<Invoice> getAll() {
        return invoiceRepository.findAll();
    }
}

package com.resort.springbootMolvenoLake.controllers;

import com.resort.springbootMolvenoLake.models.Supplier;
import com.resort.springbootMolvenoLake.repositories.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@Controller("suppliercontroller")
@RequestMapping("/supplier")
public class SupplierController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("title", "Dashboard");
        return "data/supplier/index";
    }
}

//@RestController
//@RequestMapping("hotel/suppliers/")
//public class SupplierController {
//
//    @Autowired
//    private SupplierRepository supplierRepository;
//
//    // GET: Returns all suppliers
//    @RequestMapping(method = RequestMethod.GET)
//    public Iterable<Supplier> getAll() {
//        return supplierRepository.findAll();
//    }
//
//    // POST: that saves supplier in DB
//    @RequestMapping(method = RequestMethod.POST)
//    public Supplier create(@RequestBody Supplier supplier) {
//        return supplierRepository.save(supplier);
//    }
//
//    // GET: request that returns one supplier
//    @RequestMapping(value = "{id}", method = RequestMethod.GET)
//    public Optional<Supplier> findById(@PathVariable long id) {
//        Optional<Supplier> result = supplierRepository.findById(id);
//        return result;
//    }
//
//    // UPDATE: updates supplier in DB
//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
//    public Supplier updateById(@PathVariable long id, @RequestBody Supplier update) {
//        if(supplierRepository.existsById(id)){
//            update.setId(id);
//            return supplierRepository.save(update);
//        } else {
//            throw new RuntimeException();
//        }
//    }
//
//    // DELETE: deletes supplier in DB
//    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
//    public void deleteById(@PathVariable long id) {
//        this.supplierRepository.deleteById(id);
//    }
//}

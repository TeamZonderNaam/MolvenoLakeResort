//package com.resort.springbootMolvenoLake.controllers;
//
//
//import com.resort.springbootMolvenoLake.models.LoggedAccount;
//import com.resort.springbootMolvenoLake.repositories.LoggedAccountRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.ArrayList;
//import java.util.Optional;
//import java.util.Scanner;
//
////@RequestMapping("hotel/accounts/")
//@RestController
//public class LoggedAccountController{
//
//    //@Autowired
//    private LoggedAccountRepository loggedAccountRepo;
//
//    @Autowired
//    public LoggedAccountController(LoggedAccountRepository loggedAccountRepo){ //Creates an account right of the bat.
//        this.loggedAccountRepo = loggedAccountRepo;
//        LoggedAccount account = new LoggedAccount();
//        account.setId(1);
//        account.setEmail("david.mijdam@capgemini.com");
//        account.setPassword("password");
//        account.setFirstName("David");
//        account.setLastName("Mijdam");
//        loggedAccountRepo.save(account);
//    }
//
//    //------CRUD commands------
//
//    @RequestMapping(value = "hotel/logged/", method = RequestMethod.POST)
//    public LoggedAccount createLogged(@RequestBody LoggedAccount account){
//
//        return loggedAccountRepo.save(account);
//    }
//
//    @RequestMapping(value = "hotel/logged/", method = RequestMethod.GET)
//    public Iterable<LoggedAccount> getAllLogged(){
//        return loggedAccountRepo.findAll();
//    }
//
//    @RequestMapping(value = "hotel/logged/{id}", method = RequestMethod.GET)
//    public Optional<LoggedAccount> findLoggedById(@PathVariable long id) {
//        Optional<LoggedAccount> result = loggedAccountRepo.findById(id);
//        return result;
//    }
//
//    @DeleteMapping("hotel/logged/{id}")
//    public void deleteLoggedAccount(@PathVariable Long id) {
//        loggedAccountRepo.deleteById(id);
//    }
//
//    @RequestMapping(value = "hotel/logged/{id}", method = RequestMethod.PUT)
//    public LoggedAccount updateLoggedById(@PathVariable long id, @RequestBody LoggedAccount update) {
//        if(loggedAccountRepo.existsById(id)){
//            update.setId(id);
//            return loggedAccountRepo.save(update);
//        } else {
//            throw new RuntimeException();
//        }
//    }
//}

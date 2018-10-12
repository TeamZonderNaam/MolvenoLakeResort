//package com.resort.springbootMolvenoLake.controllers;
//
//
//import com.resort.springbootMolvenoLake.models.Account;
//import com.resort.springbootMolvenoLake.repositories.AccountsRepository;
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
//public class AccountsController{
//
//    //@Autowired
//    private AccountsRepository accountRepository;
//
//    public AccountsController(AccountsRepository accountRepository){ //Creates an account right of the bat.
//        this.accountRepository = accountRepository;
//        Account account = new Account();
//        account.setEmail("david.mijdam@capgemini.com");
//        account.setPassword("password");
//        account.setFirstName("David");
//        account.setLastName("Mijdam");
//        accountRepository.save(account);
//    }
//
//    //------CRUD commands------
//
//    @RequestMapping(value = "hotel/accounts/", method = RequestMethod.POST)
//    public Account create(@RequestBody Account account){
//
//        return accountRepository.save(account);
//    }
//
//    @RequestMapping(value = "hotel/accounts/", method = RequestMethod.GET)
//    public Iterable<Account> getAll(){
//        return accountRepository.findAll();
//    }
//
//    @RequestMapping(value = "hotel/accounts/{id}", method = RequestMethod.GET)
//    public Optional<Account> findById(@PathVariable long id) {
//        Optional<Account> result = accountRepository.findById(id);
//        return result;
//    }
//
//    @DeleteMapping("hotel/accounts/{id}")
//    public void deleteAccount(@PathVariable Long id) {
//        accountRepository.deleteById(id);
//    }
//
//    @RequestMapping(value = "hotel/accounts/{id}", method = RequestMethod.PUT)
//    public Account updateById(@PathVariable long id, @RequestBody Account update) {
//        if(accountRepository.existsById(id)){
//            update.setId(id);
//            return accountRepository.save(update);
//        } else {
//            throw new RuntimeException();
//        }
//    }
//}

package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.models.Account;
import com.resort.springbootMolvenoLake.repositories.AccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("account_api_controller")
//@RequestMapping("api/account")
public class AccountController {

        @Autowired
        private AccountsRepository accountRepository;

        public AccountController(AccountsRepository accountRepository){ //Creates an account right of the bat.
            this.accountRepository = accountRepository;
            Account account = new Account();
            account.setEmail("david.mijdam@capgemini.com");
            account.setPassword("password");
            account.setFirstName("David");
            account.setLastName("Mijdam");
            account.setRole("Staff");
            accountRepository.save(account);

            Account account2 = new Account();
            account2.setEmail("guest@gmail.com");
            account2.setPassword("guest");
            account2.setFirstName("John");
            account2.setLastName("Doe");
            account2.setRole("Guest");
            accountRepository.save(account2);
        }

        //------CRUD commands------

        @RequestMapping(value = "api/account/", method = RequestMethod.POST)
        public Account create(@RequestBody Account account){

            return accountRepository.save(account);
        }

        @RequestMapping(value = "api/account/", method = RequestMethod.GET)
        public Iterable<Account> getAll(){
            return accountRepository.findAll();
        }

        @RequestMapping(value = "api/account/{id}", method = RequestMethod.GET)
        public Optional<Account> findById(@PathVariable long id) {
            Optional<Account> result = accountRepository.findById(id);
            return result;
        }

        @DeleteMapping("api/account/{id}")
        public void deleteAccount(@PathVariable Long id) {
            accountRepository.deleteById(id);
        }

        @RequestMapping(value = "api/account/{id}", method = RequestMethod.PUT)
        public Account updateById(@PathVariable long id, @RequestBody Account update) {
            if(accountRepository.existsById(id)){
                update.setId(id);
                return accountRepository.save(update);
            } else {
                throw new RuntimeException();
            }
        }

//    @Autowired
//    private AccountsRepository accountRepository;
//
//    //------CRUD commands------
//
//    @RequestMapping(method = RequestMethod.POST)
//    public Account create(@RequestBody Account account){
//
//        return accountRepository.save(account);
//    }
//
//    @RequestMapping(method = RequestMethod.GET)
//    public Iterable<Account> getAll(){
//        return accountRepository.findAll();
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.GET)
//    public Optional<Account> findById(@PathVariable long id) {
//        Optional<Account> result = accountRepository.findById(id);
//        return result;
//    }
//
//    @DeleteMapping("{id}")
//    public void deleteAccount(@PathVariable Long id) {
//        accountRepository.deleteById(id);
//    }
//
//    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
//    public Account updateById(@PathVariable long id, @RequestBody Account update) {
//        if(accountRepository.existsById(id)){
//            update.setId(id);
//            return accountRepository.save(update);
//        } else {
//            throw new RuntimeException();
//        }
//    }
}

package com.resort.springbootMolvenoLake.controllers.api;

import com.resort.springbootMolvenoLake.models.LoggedAccount;
import com.resort.springbootMolvenoLake.repositories.LoggedAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("login_api_controller")
public class LoginController {
        //@Autowired
        private LoggedAccountRepository loggedAccountRepo;

        @Autowired
        public LoginController(LoggedAccountRepository loggedAccountRepo){ //Creates an account right of the bat.
            this.loggedAccountRepo = loggedAccountRepo;
            LoggedAccount account = new LoggedAccount();
            account.setId(1);
            account.setEmail("");
            account.setPassword("");
            account.setFirstName("");
            account.setLastName("");
            account.setRole("");
            loggedAccountRepo.save(account);
        }

        //------CRUD commands------

        @RequestMapping(value = "/api/logged/", method = RequestMethod.POST)
        public LoggedAccount createLogged(@RequestBody LoggedAccount account){

            return loggedAccountRepo.save(account);
        }

        @RequestMapping(value = "/api/logged/", method = RequestMethod.GET)
        public Iterable<LoggedAccount> getAllLogged(){
            return loggedAccountRepo.findAll();
        }

        @RequestMapping(value = "/api/logged/{id}", method = RequestMethod.GET)
        public Optional<LoggedAccount> findLoggedById(@PathVariable long id) {
            Optional<LoggedAccount> result = loggedAccountRepo.findById(id);
            return result;
        }

        @DeleteMapping("/api/logged/{id}")
        public void deleteLoggedAccount(@PathVariable Long id) {
            loggedAccountRepo.deleteById(id);
        }

        @RequestMapping(value = "/api/logged/{id}", method = RequestMethod.PUT)
        public LoggedAccount updateLoggedById(@PathVariable long id, @RequestBody LoggedAccount update) {
            if(loggedAccountRepo.existsById(id)){
                update.setId(id);
                return loggedAccountRepo.save(update);
            } else {
                throw new RuntimeException();
            }
        }

}

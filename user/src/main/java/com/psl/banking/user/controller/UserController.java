package com.psl.banking.user.controller;

import com.psl.banking.user.FeignClients.ChequeProxy;
import com.psl.banking.user.entity.User;
import com.psl.banking.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);


    //Method to get User by its User-Id
    @GetMapping("/user-detail/{id}")
    public User getUser(@PathVariable Long id) {
        LOGGER.info("Inside getUser function of User controller");
        return userService.getUser(id);
    }

    //Method to add new User to Database
    @PostMapping("/create-user")
    public User createUser(@RequestBody @Valid User user) {
        LOGGER.info("Inside createUser function of  User controller");
        return userService.createUser(user);
    }

    //Method to update user details
    @PutMapping("/update-user-detail/{id}")
    public User updateUser(@RequestBody User user, @PathVariable Long id) {
        LOGGER.info("Inside updateUser function of User controller");
        return userService.updateUser(user, id);
    }

    //Method to delete User by User-Id
    @DeleteMapping("/delete-user/{id}")
    public String deleteUser(@PathVariable Long id) {
        LOGGER.info("Inside deleteUser function of User controller");
        userService.deleteUser(id);
        return "" + id + " Successfully Deleted";
    }

    //Method api to save new cheque for existing user for withdrawal and return processing status with reason
    //user Cheque settlement service api's
    @PostMapping("/{userId}/save-cheque")
    public ChequeProxy saveCheque(@RequestBody @Valid ChequeProxy cheque,@PathVariable long userId) {
        LOGGER.info("Inside save Cheque function of User controller");
        return userService.addCheque(cheque,userId);
    }

    //expose get api to list all invalid cheques for a given user for reposting
    @GetMapping("/{userId}/cheque/invalid")
    public List<ChequeProxy> getInvalidCheque(@PathVariable Long userId) {
        LOGGER.info("Inside get Invalid Cheques for a user function of User controller");
        return userService.getInvalidChequesByUserId(userId);
    }

    //expose get api to list invalid cheques for each bank over past month for reposting
    @GetMapping("/cheque/invalid-cheques-all")
    public List<ChequeProxy> getAllInvalidCheque() {
        LOGGER.info("Inside getallInvalidCheque function of User controller");
        return userService.getAllInvalidCheques();
    }
}

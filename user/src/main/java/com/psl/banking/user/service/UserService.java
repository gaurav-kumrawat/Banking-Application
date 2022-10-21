package com.psl.banking.user.service;


import com.psl.banking.user.FeignClients.ChequeFeignClient;
import com.psl.banking.user.FeignClients.ChequeProxy;
import com.psl.banking.user.exception.UserNotFoundException;
import com.psl.banking.user.entity.User;
import com.psl.banking.user.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ChequeFeignClient chequeFeignClient;

    @Autowired
    PasswordEncoder passwordEncoder;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    //Method to get User by its User-Id
    public User getUser(long id) {
        LOGGER.info("Inside getUser function of service class");
        User user= userRepository.findByUserId(id);
        if(user!=null){
            return user;
        }else{
            throw new UserNotFoundException("user not found with id : "+id);
        }
    }

    //Method to add new User to Database
    public User createUser(User user) {
        LOGGER.info("Inside createUser function of service class");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    //Method to update user details
    public User updateUser(User user, Long id) {
        LOGGER.info("Inside updateUser function of service class");
        User temp = userRepository.findByUserId(id);
        if(temp==null){
            throw new UserNotFoundException("user not found with id : "+id);
        }
        temp.setEmailId(user.getEmailId());
        temp.setPassword(user.getPassword());
        userRepository.save(temp);
        return temp;
    }

    //Method to delete User by User-Id
    public void deleteUser(Long id) {
        LOGGER.info("Inside deleteUser function of service class");
        userRepository.deleteById(id);
    }

    //Method to save Cheque
    public ChequeProxy addCheque(ChequeProxy cheque,long userId){
        LOGGER.info("Inside add Cheques for a user function of User Service class");
        ChequeProxy cp= chequeFeignClient.addCheque(cheque,userId);
        if(cp!=null){
            return cp;
        }else{
            throw new RuntimeException();
        }
    }

    //Method to list all invalid cheques for a given user for reposting
    public List<ChequeProxy> getInvalidChequesByUserId(long userid){
        LOGGER.info("Inside get Invalid Cheques for a user function of User Service class");
        return chequeFeignClient.getInvalidChequesByUserId(userid);
    }

    //Method to list all invalid cheques for reposting
    public List<ChequeProxy> getAllInvalidCheques(){
        LOGGER.info("Inside getallInvalidCheque function of User Service class");
        return chequeFeignClient.getInvalidCheques();
    }

}

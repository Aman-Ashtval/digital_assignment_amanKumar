package com.example.userdata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.userdata.model.*;
import com.example.userdata.repository.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UserJpaService implements UserRepository {

    @Autowired
    private UserJpaRepository userJpaRepository;

    @Autowired
    private ManagerJpaRepository managerJpaRepository;

    @Override
    public ArrayList<User> getAllUsers(){
        List<User> usersList = userJpaRepository.findAll();
        return new ArrayList<>(usersList);
    }

    @Override
    public User getUserById(int userId){
        try{
            User user = userJpaRepository.findById(userId).get();
            return user;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid user Id");
        }
    }

    @Override
    public User addUser(User user){
        try{
            // user name validation
            if(user.getUserName() == null){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "user name should not be null");
            }

            // user mobile number validation
            String mobileNumber = user.getMobileNumber();
            String prefix1 = "0";
            String prefix2 = "+91";
            if((mobileNumber.substring(0, 1).equals(prefix1) == false) && (mobileNumber.substring(0,3).equals(prefix2) == false)){
                if(mobileNumber.length() != 10) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enter valid mobile number");
                else{
                    mobileNumber = "+91" + mobileNumber;
                    user.setMobileNumber(mobileNumber);
                }
            }else if(mobileNumber.substring(0, 1).equals(prefix1) && mobileNumber.substring(1).length() != 10){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enter valid mobile number");
            }else if(mobileNumber.substring(0,3).equals(prefix2) && mobileNumber.substring(3).length() != 10){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "enter valid mobile number");
            }

            // user pan validation
            String userPan = user.getPan();
            String newPan = "";
            for(int i=0; i<userPan.length(); i++){
                if(Character.isLowerCase(userPan.charAt(i))){
                    newPan += Character.toUpperCase(userPan.charAt(i));
                }else{
                    newPan += userPan.charAt(i);
                }
            }
            user.setPan(newPan);

            // user manager id validation
            int managerId = user.getManager().getManagerId();
            Manager manager = managerJpaRepository.findById(managerId).get();
            user.setManager(manager);
            return userJpaRepository.save(user);
        }catch(NoSuchElementException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid manager Id");
        }
    }
}

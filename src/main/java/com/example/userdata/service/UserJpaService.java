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

    @Override
    public ArrayList<User> getAllUsers(){
        List<User> usersList = userJpaRepository.findAll();
        return new ArrayList<>(usersList);
    }

    @Override
    public ArrayList<User> getUserById(int userId){
        try{
            User user = userJpaRepository.findById(userId).get();
            List<User> users = new ArrayList<>();
            users.add(user);
            return new ArrayList<>(users);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "invalid user Id");
        }
    }

    @Override
    public ArrayList<User> getUserBymobileNumber(String mobileNumber){
            User user = userJpaRepository.findByMobileNumber(mobileNumber);
            if(user == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not exists");
            List<User> users = new ArrayList<>();
            users.add(user);
            return new ArrayList<>(users);
    }

    @Override
    public ArrayList<User> getUserByManager(Manager manager){
        try{
            Manager existManager = managerJpaRepository.findById(manager.getManagerId()).get();
            List<User> users = userJpaRepository.findByManager(existManager);
            return new ArrayList<>(users);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "wrong manager id");
        }
    }

    @Override
    public void deleteUserByUserId(int userId){
        try{
            User user = userJpaRepository.findById(userId).get();
            userJpaRepository.deleteById(userId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not exists");
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "user deleted successfully");
    }

    @Override
    public void deleteUserByMObileNumber(String mobileNumber){
        try{
            User user = userJpaRepository.findByMobileNumber(mobileNumber);
            userJpaRepository.deleteById(user.getUserId());
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "user not exists");
        }
        throw new ResponseStatusException(HttpStatus.NO_CONTENT, "user deleted successfully");
    }


}

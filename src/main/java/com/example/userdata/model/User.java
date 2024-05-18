package com.example.userdata.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import jakarta.persistence.*;
import com.example.userdata.model.Manager;


@Entity
@Table(name="\"user\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int userId;

    @Column(name = "name")
    private String userName;

    @Column(name = "mobile")
    private String mobileNumber;

    @Column(name = "pan")
    private String pan;


    @Column(name = "created_at")
    private String createdAt = getCurrentTimeStamp();

    @Column(name = "updated_at")
    private String updatedAt;

    @Column(name = "isactive")
    private boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "managerid")
    private Manager manager;

    public User() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getPan() {
        return pan;
    }

    public void setPan(String pan) {
        this.pan = pan;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public static String getCurrentTimeStamp(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentDateTime.format(formatter);
    }
}

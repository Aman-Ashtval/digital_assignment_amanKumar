package com.example.userdata.model;

import jakarta.persistence.*;

@Entity
@Table(name="\"manager\"")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int managerId;

    @Column(name = "name")
    private String managerName;

    @Column(name="department")
    private String department;

    public Manager(){}

    public Manager(String managerName, String department){
        this.managerName = managerName;
        this.department = department;
    }

    public int getManagerId(){
        return managerId;
    }

    public void setManagerId(int managerId){
        this.managerId = managerId;
    }

    public String getManagerName(){
        return managerName;
    }

    public void setManagerName(String managerName){
        this.managerName = managerName;
    }

    public String getDepartment(){
        return department;
    }

    public void setDepartment(String department){
        this.department = department;
    }
}
